package arithDemo.bloomFilter;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @Author xlj
 * @Date 2018/9/15 16:24
 */
enum RedisBloomFilterStrategies implements RedisBloomFilter.Strategy {

    REDIS_BLOOM_FILTER_STRATEGIES {
        @Override
        public boolean put(String string, int numHashFunctions, RedisBitmaps bits) {
            boolean bitsChanged;
            long[] offsets = getLongs(string, numHashFunctions, bits);
            bitsChanged = bits.put(offsets);
//            bits.ensureCapacityInternal();//自動擴容 （动态添加是需要这个功能）
            return bitsChanged;
        }

        @Override
        public boolean putAll(AtomicLongArray data, RedisBitmaps bits) {
            return bits.putAll(data);
        }


        @Override
        public boolean mightContain(String object, int numHashFunctions, RedisBitmaps bits) {
            long[] offsets = getLongs(object, numHashFunctions, bits);
            return bits.isExist(offsets);
        }

        private long[] getLongs(String string, int numHashFunctions, RedisBitmaps bits) {
            long bitSize = bits.bitSize();
            long hash64 = Hashing.murmur3_128().hashString(string, StandardCharsets.UTF_8).asLong();
            int hash1 = (int) hash64;
            int hash2 = (int) (hash64 >>> 32);
            long[] offsets = new long[numHashFunctions];
            for (int i = 0; i < numHashFunctions; ++i) {
                int combinedHash = hash1 + i * hash2;
                if (combinedHash < 0) {
                    combinedHash = ~combinedHash;
                }
                offsets[i] = (long) combinedHash % bitSize;
            }
            return offsets;
        }
    }


}
