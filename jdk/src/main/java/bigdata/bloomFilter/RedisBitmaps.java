package bigdata.bloomFilter;

import com.google.common.math.LongMath;
import com.google.common.primitives.Longs;
import redis.clients.jedis.Pipeline;

import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.stream.LongStream;

public class RedisBitmaps {

    private final String BASE_KEY;
    private static final String CURSOR = "cursor";

    private JedisUtils jedisUtils;
    private long bitSize;

    RedisBitmaps(String baseKey, long bits) {
        BASE_KEY = baseKey;
        this.jedisUtils = new JedisUtils();
        this.bitSize = LongMath.divide(bits, 64, RoundingMode.CEILING) * Long.SIZE;//位數組的長度，相當於n個long的長度
        if (bitCount() == 0) {
            jedisUtils.execute((jedis -> jedis.setbit(currentKey(), bitSize - 1, false)));
        }

    }

    boolean get(long[] offsets) {
        for (long i = 0; i < cursor() + 1; i++) {
            final long cursor = i;
            //只要有一個cursor對應的bitmap中，offsets全部命中，則表示可能存在
            boolean match = Arrays.stream(offsets).boxed()
                    .map(offset -> jedisUtils.execute(jedis -> jedis.getbit(genkey(cursor), offset)))
                    .allMatch(b -> b);
            if (match)
                return true;
        }
        return false;
    }

    boolean get(final long offset) {
        return jedisUtils.execute(jedis -> jedis.getbit(RedisBitmaps.this.currentKey(), offset));
    }

    boolean set(long[] offsets) {
        if (cursor() > 0 && get(offsets)) {
            return false;
        }
        boolean bitsChanged = false;
        for (long offset : offsets)
            bitsChanged |= set(offset);
        return bitsChanged;
    }

    boolean set(long offset) {
        if (!get(offset)) {
            jedisUtils.execute(jedis -> jedis.setbit(currentKey(), offset, true));
            return true;
        }
        return false;
    }

    long bitCount() {
        return jedisUtils.execute(jedis -> jedis.bitcount(currentKey()));
    }

    long bitSize() {
        return this.bitSize;
    }

    private String currentKey() {
        return genkey(cursor());
    }

    private String genkey(long cursor) {
        return BASE_KEY + "-" + cursor;
    }

    private Long cursor() {
        String cursor = jedisUtils.execute(jedis -> jedis.get(CURSOR));
        return cursor == null ? 0 : Longs.tryParse(cursor);
    }

    void ensureCapacityInternal() {
        if (bitCount() * 2 > bitSize())
            grow();
    }

    void grow() {
        Long cursor = jedisUtils.execute(jedis -> jedis.incr(CURSOR));
        jedisUtils.execute((jedis -> jedis.setbit(genkey(cursor), bitSize - 1, false)));
    }

    void reset() {
        String[] keys = LongStream.range(0, cursor() + 1).boxed().map(this::genkey).toArray(String[]::new);
        jedisUtils.execute(jedis -> jedis.del(keys));
        jedisUtils.execute(jedis -> jedis.set(CURSOR, "0"));
        jedisUtils.execute(jedis -> jedis.setbit(currentKey(), bitSize - 1, false));
    }

    private PipelineExecutor apply(PipelineExecutor executor) {
        return executor;
    }

    /**
     * 将key存入redis bitmap
     */
    public boolean put(long[] indexs) {
        //这里使用了Redis管道来降低过滤器运行当中访问Redis次数 降低Redis并发量
        try {
            jedisUtils.pipeline(pipeline -> {
                for (long index : indexs) {
                    pipeline.setbit(currentKey(), index, true);
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    /**
     * 判断keys是否存在于集合where中
     */
    public boolean isExist(long[] indexs) {
        //这里使用了Redis管道来降低过滤器运行当中访问Redis次数 降低Redis并发量

        List<Object> objects = jedisUtils.pipelineExec((Pipeline pipeline) -> {
            for (long index : indexs) {
                pipeline.getbit(currentKey(), index);
            }
        });
        return !objects.contains(false);
    }

    public boolean putAll(AtomicLongArray data) {
        //这里使用了Redis管道来降低过滤器运行当中访问Redis次数 降低Redis并发量
        try {
            jedisUtils.pipeline(pipeline -> {
                for (int i = 0; i < data.length(); i++) {
                    pipeline.setbit(currentKey(), data.get(i), true);
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}