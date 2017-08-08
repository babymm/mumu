package com.lovecws.mumu.common.redis;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis 集群
 * @author ganliang
 */
public class JedisClientClusterImpl implements JedisClient {

	private JedisCluster jedisCluster;

	public void setJedisCluster(JedisCluster jedisCluster) {
		this.jedisCluster = jedisCluster;
	}

	/********************************* common ************************************/
	@Override
	public Set<String> keys(String pattern) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<byte[]> keys(byte[] pattern) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void flushDB() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long dbSize() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}
	
	@Override
	public long expire(byte[] key, int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public long ttl(String key) {
		return jedisCluster.ttl(key);
	}
	
	@Override
	public long ttl(byte[] key) {
		return jedisCluster.ttl(key);
	}

	@Override
	public long del(String key) {
		return jedisCluster.del(key);
	}
	
	@Override
	public long del(byte[] key) {
		return jedisCluster.del(key);
	}
	
	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}
	@Override
	public Boolean exists(byte[] key) {
		return jedisCluster.exists(key);
	}
	@Override
	public String rename(String oldkey,String newkey) {
		return jedisCluster.rename(oldkey, newkey);
	}
	@Override
	public String rename(byte[] oldkey,byte[] newkey) {
		return jedisCluster.rename(oldkey, newkey);
	}
	@Override
	public Long renamenx(String oldkey,String newkey) {
		return jedisCluster.renamenx(oldkey, newkey);
	}
	@Override
	public Long renamenx(byte[] oldkey,byte[] newkey) {
		return jedisCluster.renamenx(oldkey, newkey);
	}
	@Override
	public String type(String key) {
		return jedisCluster.type(key);
	}
	@Override
	public String type(byte[] key) {
		return jedisCluster.type(key);
	}
	@Override
	public Transaction multi() {
		throw new UnsupportedOperationException();
	}
	@Override
	public Jedis getJedis() {
		throw new UnsupportedOperationException();
	}
	@Override
	public ShardedJedis getShardedJedis() {
		throw new UnsupportedOperationException();
	}
	@Override
	public JedisCluster getClusterJedis(){
		return jedisCluster;
	}
	
	@Override
	public Pipeline getPipeline() {
		throw new UnsupportedOperationException();
	}
	@Override
	public ShardedJedisPipeline getShardedJedisPipeline() {
		throw new UnsupportedOperationException();
	}
	/********************************* String ************************************/
	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String set(byte[] key, byte[] value) {
		return jedisCluster.set(key, value);
	}

	@Override
	public String set(String key, String value, int expire) {
		value = jedisCluster.set(key, value);
		if (expire != 0) {
			jedisCluster.expire(key, expire);
		}
		return value;
	}
	@Override
	public String set(byte[] key, byte[] value, int expire) {
		String val = jedisCluster.set(key, value);
		if (expire != 0) {
			jedisCluster.expire(key, expire);
		}
		return val;
	}

	@Override
	public Long setnx(String key, String value) {
		return jedisCluster.setnx(key, value);
	}
	
	@Override
	public Long setnx(byte[] key, byte[] value) {
		return jedisCluster.setnx(key, value);
	}

	@Override
	public String setex(String key, int second, String value) {
		return jedisCluster.setex(key, second, value);
	}
	
	@Override
	public String setex(byte[] key, int second, byte[] value) {
		return jedisCluster.setex(key, second, value);
	}

	@Override
	public String mset(String... keysvalues) {
		return jedisCluster.mset(keysvalues);
	}
	
	@Override
	public String mset(byte[]... keysvalues) {
		return jedisCluster.mset(keysvalues);
	}

	@Override
	public Long setrange(String key, int offset, String value) {
		return jedisCluster.setrange(key, offset, value);
	}
	
	@Override
	public Long setrange(byte[] key, int offset, byte[] value) {
		return jedisCluster.setrange(key, offset, value);
	}

	@Override
	public String get(String key) {
		return jedisCluster.get(key);
	}
	
	@Override
	public byte[] get(byte[] key) {
		return jedisCluster.get(key);
	}

	@Override
	public long incr(String key) {
		return jedisCluster.incr(key);
	}
	@Override
	public long incr(byte[] key) {
		return jedisCluster.incr(key);
	}

	@Override
	public long incrby(String key, int incrby) {
		return jedisCluster.incrBy(key, incrby);
	}
	
	@Override
	public long incrby(byte[] key, int incrby) {
		return jedisCluster.incrBy(key, incrby);
	}

	@Override
	public long decr(String key) {
		return jedisCluster.decr(key);
	}
	
	@Override
	public long decr(byte[] key) {
		return jedisCluster.decr(key);
	}

	@Override
	public long decrby(String key, int decrby) {
		return jedisCluster.decrBy(key, decrby);
	}
	
	@Override
	public long decrby(byte[] key, int decrby) {
		return jedisCluster.decrBy(key, decrby);
	}

	@Override
	public String getset(String key, String value) {
		return jedisCluster.getSet(key, value);
	}
	
	@Override
	public byte[] getset(byte[] key, byte[] value) {
		return jedisCluster.getSet(key, value);
	}

	@Override
	public String getrange(String key, int start, int end) {
		return jedisCluster.getrange(key, start, end);
	}
	
	@Override
	public byte[] getrange(byte[] key, int start, int end) {
		return jedisCluster.getrange(key, start, end);
	}

	@Override
	public List<String> mget(String... key) {
		return jedisCluster.mget(key);
	}
	
	@Override
	public List<byte[]> mget(byte[]... key) {
		return jedisCluster.mget(key);
	}

	@Override
	public Long append(String key, String value) {
		return jedisCluster.append(key, value);
	}
	
	@Override
	public Long append(byte[] key, byte[] value) {
		return jedisCluster.append(key, value);
	}

	@Override
	public Long strlen(String key) {
		return jedisCluster.strlen(key);
	}
	@Override
	public Long strlen(byte[] key) {
		return jedisCluster.strlen(key);
	}

	
	/*************************************************hash(散列)******************************************************/
	@Override
	public long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}
	@Override
	public long hset(byte[] hkey, byte[] key, byte[] value) {
		return jedisCluster.hset(hkey, key, value);
	}
	
	@Override
	public long hsetnx(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}
	@Override
	public long hsetnx(byte[] hkey, byte[] key, byte[] value) {
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public String hmset(String hkey, Map<String, String> hashMap) {
		return jedisCluster.hmset(hkey, hashMap);
	}
	
	@Override
	public String hmset(byte[] hkey, Map<byte[], byte[]> hashMap) {
		return jedisCluster.hmset(hkey, hashMap);
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}
	@Override
	public byte[] hget(byte[] hkey, byte[] key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public List<String> hmget(String hkey, String... keys) {
		return jedisCluster.hmget(hkey, keys);
	}
	
	@Override
	public List<byte[]> hmget(byte[] hkey, byte[]... keys) {
		return jedisCluster.hmget(hkey, keys);
	}

	@Override
	public long hdel(String hkey, String... keys) {
		return jedisCluster.hdel(hkey, keys);
	}
	@Override
	public long hdel(byte[] hkey, byte[]... keys) {
		return jedisCluster.hdel(hkey, keys);
	}

	@Override
	public long hincrby(String hkey, String key, int increment) {
		return jedisCluster.hincrBy(hkey, key, increment);
	}
	
	@Override
	public long hincrby(byte[] hkey, byte[] key, int increment) {
		return jedisCluster.hincrBy(hkey, key, increment);
	}

	@Override
	public Boolean hexists(String hkey, String key) {
		return jedisCluster.hexists(hkey, key);
	}
	
	@Override
	public Boolean hexists(byte[] hkey, byte[] key) {
		return jedisCluster.hexists(hkey, key);
	}

	@Override
	public long hlen(String hkey) {
		return jedisCluster.hlen(hkey);
	}
	
	@Override
	public long hlen(byte[] hkey) {
		return jedisCluster.hlen(hkey);
	}

	@Override
	public Set<String> hkeys(String hkey) {
		return jedisCluster.hkeys(hkey);
	}
	@Override
	public Set<byte[]> hkeys(byte[] hkey) {
		return jedisCluster.hkeys(hkey);
	}

	@Override
	public List<String> hvals(String hkey) {
		return jedisCluster.hvals(hkey);
	}
	@Override
	public List<byte[]> hvals(byte[] hkey) {
		return (List<byte[]>) jedisCluster.hvals(hkey);
	}

	@Override
	public Map<String, String> hgetall(String hkey) {
		return jedisCluster.hgetAll(hkey);
	}
	
	@Override
	public Map<byte[], byte[]> hgetall(byte[] hkey) {
		return jedisCluster.hgetAll(hkey);
	}

	
	/*************************************************list(列表)******************************************************/
	@Override
	public Long lpush(String key, String... values) {
		return jedisCluster.lpush(key, values);
	}
	@Override
	public Long lpush(byte[] key, byte[]... values) {
		return jedisCluster.lpush(key, values);
	}

	@Override
	public Long rpush(String key, String... values) {
		return jedisCluster.rpush(key, values);
	}
	@Override
	public Long rpush(byte[] key, byte[]... values) {
		return jedisCluster.rpush(key, values);
	}

	@Override
	public Long linsert(String key, String pivot, String value) {
		return jedisCluster.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
	}
	@Override
	public Long linsert(byte[] key, byte[] pivot, byte[] value) {
		return jedisCluster.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
	}

	@Override
	public String lset(String key, int index, String value) {
		return jedisCluster.lset(key, index, value);
	}
	@Override
	public String lset(byte[] key, int index, byte[] value) {
		return jedisCluster.lset(key, index, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		return jedisCluster.lrem(key, count, value);
	}
	@Override
	public Long lrem(byte[] key, long count, byte[] value) {
		return jedisCluster.lrem(key, count, value);
	}

	@Override
	public String ltrim(String key, int start, int end) {
		return jedisCluster.ltrim(key, start, end);
	}
	@Override
	public String ltrim(byte[] key, int start, int end) {
		return jedisCluster.ltrim(key, start, end);
	}

	@Override
	public String lpop(String key) {
		return jedisCluster.lpop(key);
	}
	@Override
	public byte[] lpop(byte[] key) {
		return jedisCluster.lpop(key);
	}

	@Override
	public String rpop(String key) {
		return jedisCluster.rpop(key);
	}
	@Override
	public byte[] rpop(byte[] key) {
		return jedisCluster.rpop(key);
	}

	@Override
	public String rpoplpush(String srckey, String dstkey) {
		return jedisCluster.rpoplpush(srckey, dstkey);
	}
	@Override
	public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
		return jedisCluster.rpoplpush(srckey, dstkey);
	}

	@Override
	public String lindex(String key, int index) {
		return jedisCluster.lindex(key, index);
	}
	@Override
	public byte[] lindex(byte[] key, int index) {
		return jedisCluster.lindex(key, index);
	}

	@Override
	public Long llen(String key) {
		return jedisCluster.llen(key);
	}
	@Override
	public Long llen(byte[] key) {
		return jedisCluster.llen(key);
	}

	@Override
	public List<String> lrange(String key, long start, long end) {
		return jedisCluster.lrange(key, start, end);
	}
	@Override
	public List<byte[]> lrange(byte[] key, long start, long end) {
		return jedisCluster.lrange(key, start, end);
	}

	
	/*************************************************set(集合)******************************************************/
	@Override
	public Long sadd(String key, String... members) {
		return jedisCluster.sadd(key, members);
	}
	@Override
	public Long sadd(byte[] key, byte[]... members) {
		return jedisCluster.sadd(key, members);
	}

	@Override
	public Long srem(String key, String... members) {
		return jedisCluster.srem(key, members);
	}
	@Override
	public Long srem(byte[] key, byte[]... members) {
		return jedisCluster.srem(key, members);
	}

	@Override
	public String spop(String key) {
		return jedisCluster.spop(key);
	}
	@Override
	public byte[] spop(byte[] key) {
		return jedisCluster.spop(key);
	}

	@Override
	public Set<String> sdiff(String... keys) {
		return jedisCluster.sdiff(keys);
	}
	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		return jedisCluster.sdiff(keys);
	}

	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		return jedisCluster.sdiffstore(dstkey, keys);
	}
	@Override
	public Long sdiffstore(byte[] dstkey, byte[]... keys) {
		return jedisCluster.sdiffstore(dstkey, keys);
	}

	@Override
	public Set<String> sinter(String... keys) {
		return jedisCluster.sinter(keys);
	}
	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		return jedisCluster.sinter(keys);
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		return jedisCluster.sinterstore(dstkey, keys);
	}
	@Override
	public Long sinterstore(byte[] dstkey, byte[]... keys) {
		return jedisCluster.sinterstore(dstkey, keys);
	}

	@Override
	public Set<String> sunion(String... keys) {
		return jedisCluster.sunion(keys);
	}
	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		return jedisCluster.sunion(keys);
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		return jedisCluster.sunionstore(dstkey, keys);
	}
	@Override
	public Long sunionstore(byte[] dstkey, byte[]... keys) {
		return jedisCluster.sunionstore(dstkey, keys);
	}

	@Override
	public Long smove(String srckey,String dstkey, String member) {
		return jedisCluster.smove(srckey, dstkey, member);
	}
	@Override
	public Long smove(byte[] srckey,byte[] dstkey, byte[] member) {
		return jedisCluster.smove(srckey, dstkey, member);
	}

	@Override
	public Long scard(String key) {
		return jedisCluster.scard(key);
	}
	@Override
	public Long scard(byte[] key) {
		return jedisCluster.scard(key);
	}

	@Override
	public Boolean sismember(String key, String member) {
		return jedisCluster.sismember(key, member);
	}
	@Override
	public Boolean sismember(byte[] key, byte[] member) {
		return jedisCluster.sismember(key, member);
	}

	@Override
	public String srandmember(String key) {
		return jedisCluster.srandmember(key);
	}
	@Override
	public byte[] srandmember(byte[] key) {
		return jedisCluster.srandmember(key);
	}

	@Override
	public Set<String> smembers(String key) {
		return jedisCluster.smembers(key);
	}
	@Override
	public Set<byte[]> smembers(byte[] key) {
		return jedisCluster.smembers(key);
	}

	
	/*************************************************zset(有序集合)******************************************************/
	@Override
	public Long zadd(String key, double score, String member) {
		return jedisCluster.zadd(key, score, member);
	}
	@Override
	public Long zadd(byte[] key, double score, byte[] member) {
		return jedisCluster.zadd(key, score, member);
	}

	@Override
	public Long zrem(String key, String... members) {
		return jedisCluster.zrem(key, members);
	}
	@Override
	public Long zrem(byte[] key, byte[]... members) {
		return jedisCluster.zrem(key, members);
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		return jedisCluster.zincrby(key, score, member);
	}
	@Override
	public Double zincrby(byte[] key, double score, byte[] member) {
		return jedisCluster.zincrby(key, score, member);
	}

	@Override
	public Long zrank(String key, String member) {
		return jedisCluster.zrank(key, member);
	}
	@Override
	public Long zrank(byte[] key, byte[] member) {
		return jedisCluster.zrank(key, member);
	}

	@Override
	public Long zrevrank(String key, String member) {
		return jedisCluster.zrevrank(key, member);
	}
	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		return jedisCluster.zrevrank(key, member);
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		return jedisCluster.zrevrange(key, start, end);
	}
	@Override
	public Set<byte[]> zrevrange(byte[] key, long start, long end) {
		return jedisCluster.zrevrange(key, start, end);
	}

	@Override
	public Set<String> zrangebyscore(String key, double min, double max) {
		return jedisCluster.zrangeByScore(key, min, max);
	}
	@Override
	public Set<byte[]> zrangebyscore(byte[] key, double min, double max) {
		return jedisCluster.zrangeByScore(key, min, max);
	}

	@Override
	public Long zcount(String key, double min, double max) {
		return jedisCluster.zcount(key, min, max);
	}
	@Override
	public Long zcount(byte[] key, double min, double max) {
		return jedisCluster.zcount(key, min, max);
	}

	@Override
	public Long zcard(String key) {
		return jedisCluster.zcard(key);
	}
	@Override
	public Long zcard(byte[] key) {
		return jedisCluster.zcard(key);
	}

	@Override
	public Double zscore(String key, String member) {
		return jedisCluster.zscore(key, member);
	}
	@Override
	public Double zscore(byte[] key, byte[] member) {
		return jedisCluster.zscore(key, member);
	}

	@Override
	public Long zremrangebyrank(String key, long start, long end) {
		return jedisCluster.zremrangeByRank(key, start, end);
	}
	@Override
	public Long zremrangebyrank(byte[] key, long start, long end) {
		return jedisCluster.zremrangeByRank(key, start, end);
	}

	@Override
	public Long zremrangebyscore(String key, long start, long end) {
		return jedisCluster.zremrangeByScore(key, start, end);
	}
	@Override
	public Long zremrangebyscore(byte[] key, long start, long end) {
		return jedisCluster.zremrangeByScore(key, start, end);
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		return jedisCluster.zrange(key, start, end);
	}
	@Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
		return jedisCluster.zrange(key, start, end);
	}

}
