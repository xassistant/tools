package arithDemo.bloomFilter;


import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @Author xlj
 * @Date 2018/9/15 16:10
 */
public class RedisBloomFilter {
    private final RedisBloomFilter.Strategy strategy = RedisBloomFilterStrategies.REDIS_BLOOM_FILTER_STRATEGIES;
    private final RedisBitmaps bits;
    private final int numHashFunctions;

    public RedisBloomFilter(RedisBitmaps bits, int numHashFunctions) {
        this.bits = bits;
        this.numHashFunctions = numHashFunctions;
    }

    public static RedisBloomFilter create(String redisBaseKey, int expectedInsertions) {
        return create(redisBaseKey, expectedInsertions, 0.03);
    }

    public static RedisBloomFilter create(String redisBaseKey, int expectedInsertions, double fpp) {
        Preconditions.checkArgument(expectedInsertions >= 0L, "Expected insertions (%s) must be >= 0", expectedInsertions);
        Preconditions.checkArgument(fpp > 0.0D, "False positive probability (%s) must be > 0.0", fpp);
        Preconditions.checkArgument(fpp < 1.0D, "False positive probability (%s) must be < 1.0", fpp);
        if (expectedInsertions == 0) {
            expectedInsertions = 1;
        }

        long numBits = optimalNumOfBits(expectedInsertions, fpp);
        int numHashFunctions = optimalNumOfHashFunctions(expectedInsertions, numBits);

        try {
            return new RedisBloomFilter(new RedisBitmaps(redisBaseKey, numBits), numHashFunctions);
        } catch (IllegalArgumentException var10) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + numBits + " bits", var10);
        }
    }

    public boolean mightContain(String object) {
        return this.strategy.mightContain(object, this.numHashFunctions, this.bits);
    }

    public boolean put(String object) {
        return this.strategy.put(object, this.numHashFunctions, this.bits);
    }

    public boolean putAll(AtomicLongArray data) {
        return this.strategy.putAll(data, this.bits);
    }

    public void resetBitmap() {
        this.bits.reset();
    }

    @VisibleForTesting
    static int optimalNumOfHashFunctions(long n, long m) {
        return Math.max(1, (int) Math.round((double) m / (double) n * Math.log(2.0D)));
    }


    @VisibleForTesting
    static long optimalNumOfBits(long n, double p) {
        if (p == 0.0D) {
            p = 4.9E-324D;
        }

        return (long) ((double) (-n) * Math.log(p) / (Math.log(2.0D) * Math.log(2.0D)));
    }

    interface Strategy extends Serializable {
        boolean put(String string, int numHashFunctions, RedisBitmaps bits);

        boolean putAll(AtomicLongArray data, RedisBitmaps bits);

        boolean mightContain(String object, int numHashFunctions, RedisBitmaps bits);
    }
}
