package com.lovecws.mumu.common.redis;

import redis.clients.jedis.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis分片
 * @author ganliang
 */
public class JedisClientShardImpl implements JedisClient{

	private ShardedJedisPool jedisSharded;
	public void setJedisSharded(ShardedJedisPool jedisSharded) {
		this.jedisSharded = jedisSharded;
	}
	
	/********************************* common ************************************/
	public void close(ShardedJedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}
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
	public long expire(String key, int seconds) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.expire(key, seconds);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long expire(byte[] key, int seconds) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.expire(key, seconds);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long ttl(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.ttl(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long ttl(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.ttl(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long del(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.del(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long del(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.del(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean exists(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.exists(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean exists(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.exists(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String rename(String oldkey, String newkey) {
		throw new UnsupportedOperationException();
	}
	@Override
	public String rename(byte[] oldkey, byte[] newkey) {
		throw new UnsupportedOperationException();
	}
	@Override
	public Long renamenx(String oldkey, String newkey) {
		throw new UnsupportedOperationException();
	}
	@Override
	public Long renamenx(byte[] oldkey, byte[] newkey) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String type(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.type(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String type(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.type(key);
		}finally{
			close(jedis);
		}
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
		return jedisSharded.getResource();
	}
	
	@Override
	public JedisCluster getClusterJedis(){
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Pipeline getPipeline() {
		throw new UnsupportedOperationException();
	}
	@Override
	public ShardedJedisPipeline getShardedJedisPipeline() {
		ShardedJedis jedis = jedisSharded.getResource();
		return jedis.pipelined();
	}

	
	/********************************* String ************************************/
	@Override
	public String set(String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.set(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String set(byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.set(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String set(String key, String value, int expire) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			value = jedis.set(key, value);
			if(expire>0){
				jedis.expire(key, expire);
			}
			return value;
		}finally{
			close(jedis);
		}
	}

	@Override
	public String set(byte[] key, byte[] value, int expire) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			String val = jedis.set(key, value);
			if(expire>0){
				jedis.expire(key, expire);
			}
			return val;
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long setnx(String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setnx(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long setnx(byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setnx(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String setex(String key, int second, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setex(key,second, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String setex(byte[] key, int second, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setex(key,second, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String mset(String... keysvalues) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String mset(byte[]... keysvalues) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long setrange(String key, int offset, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setrange(key,offset, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long setrange(byte[] key, int offset, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try {
			return jedis.setrange(key,offset, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String get(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.get(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] get(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.get(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long incr(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.incr(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long incr(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.incr(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long incrby(String key, int incrby) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.incrBy(key, incrby);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long incrby(byte[] key, int incrby) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.incrBy(key, incrby);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long decr(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.decr(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long decr(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.decr(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long decrby(String key, int decrby) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.decrBy(key, decrby);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long decrby(byte[] key, int decrby) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.decrBy(key, decrby);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String getset(String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.getSet(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] getset(byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.getSet(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String getrange(String key, int start, int end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.getrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] getrange(byte[] key, int start, int end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.getrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<String> mget(String... key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<byte[]> mget(byte[]... key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long append(String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.append(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long append(byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.append(key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long strlen(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.strlen(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long strlen(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.strlen(key);
		}finally{
			close(jedis);
		}
	}

	
	/*************************************************hash(散列)******************************************************/
	@Override
	public long hset(String hkey, String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hset(hkey, key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hset(byte[] hkey, byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hset(hkey, key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hsetnx(String hkey, String key, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hsetnx(hkey, key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hsetnx(byte[] hkey, byte[] key, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hsetnx(hkey, key, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String hmset(String hkey, Map<String, String> hashMap) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hmset(hkey, hashMap);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String hmset(byte[] hkey, Map<byte[], byte[]> hashMap) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hmset(hkey, hashMap);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String hget(String hkey, String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hget(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] hget(byte[] hkey, byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hget(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<String> hmget(String hkey, String... key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hmget(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<byte[]> hmget(byte[] hkey, byte[]... key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hmget(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hdel(String hkey, String... key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hdel(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hdel(byte[] hkey, byte[]... key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hdel(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hincrby(String hkey, String key, int increment) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hincrBy(hkey, key, increment);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hincrby(byte[] hkey, byte[] key, int increment) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hincrBy(hkey, key, increment);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean hexists(String hkey, String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hexists(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean hexists(byte[] hkey, byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hexists(hkey, key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hlen(String hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hlen(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public long hlen(byte[] hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hlen(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> hkeys(String hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hkeys(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<byte[]> hkeys(byte[] hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hkeys(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<String> hvals(String hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hvals(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<byte[]> hvals(byte[] hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return (List<byte[]>) jedis.hvals(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Map<String, String> hgetall(String hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hgetAll(hkey);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Map<byte[], byte[]> hgetall(byte[] hkey) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.hgetAll(hkey);
		}finally{
			close(jedis);
		}
	}

	/*************************************************list(列表)******************************************************/
	@Override
	public Long lpush(String key, String... values) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lpush(key,values);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long lpush(byte[] key, byte[]... values) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lpush(key,values);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long rpush(String key, String... values) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.rpush(key,values);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long rpush(byte[] key, byte[]... values) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.rpush(key,values);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long linsert(String key, String pivot, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long linsert(byte[] key, byte[] pivot, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.linsert(key, BinaryClient.LIST_POSITION.BEFORE, pivot, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String lset(String key, int index, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lset(key,index, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String lset(byte[] key, int index, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lset(key,index, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long lrem(String key, long index, String value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lrem(key,index, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long lrem(byte[] key, long index, byte[] value) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lrem(key,index, value);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String ltrim(String key, int start, int end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.ltrim(key,start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String ltrim(byte[] key, int start, int end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.ltrim(key,start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String lpop(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lpop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] lpop(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lpop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String rpop(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.rpop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] rpop(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.rpop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String rpoplpush(String srckey, String dstkey) {
		throw new UnsupportedOperationException();
	}

	@Override
	public byte[] rpoplpush(byte[] srckey, byte[] dstkey) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String lindex(String key, int index) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lindex(key, index);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] lindex(byte[] key, int index) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lindex(key, index);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long llen(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.llen(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long llen(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.llen(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<String> lrange(String key, long begin, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lrange(key, begin, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public List<byte[]> lrange(byte[] key, long begin, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.lrange(key, begin, end);
		}finally{
			close(jedis);
		}
	}

	/*************************************************set(集合)******************************************************/
	@Override
	public Long sadd(String key, String... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.sadd(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long sadd(byte[] key, byte[]... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.sadd(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long srem(String key, String... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.srem(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long srem(byte[] key, byte[]... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.srem(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String spop(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.spop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] spop(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.spop(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> sdiff(String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<byte[]> sdiff(byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sdiffstore(String dstkey, String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sdiffstore(byte[] dstkey, byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> sinter(String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<byte[]> sinter(byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sinterstore(String dstkey, String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sinterstore(byte[] dstkey, byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<String> sunion(String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<byte[]> sunion(byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sunionstore(String dstkey, String... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long sunionstore(byte[] dstkey, byte[]... keys) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long smove(String srckey, String dstkey, String member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long smove(byte[] srckey, byte[] dstkey, byte[] member) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Long scard(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.scard(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long scard(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.scard(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean sismember(String key, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.sismember(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Boolean sismember(byte[] key, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.sismember(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public String srandmember(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.srandmember(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public byte[] srandmember(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.srandmember(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> smembers(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.smembers(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<byte[]> smembers(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.smembers(key);
		}finally{
			close(jedis);
		}
	}

	
	/*************************************************zset(有序集合)******************************************************/
	@Override
	public Long zadd(String key, double score, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zadd(key, score, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zadd(byte[] key, double score, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zadd(key, score, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrem(String key, String... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrem(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrem(byte[] key, byte[]... members) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrem(key, members);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Double zincrby(String key, double score, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zincrby(key, score, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Double zincrby(byte[] key, double score, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zincrby(key, score, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrank(String key, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrank(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrank(byte[] key, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrank(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrevrank(String key, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrevrank(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zrevrank(byte[] key, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrevrank(key, member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrevrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<byte[]> zrevrange(byte[] key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrevrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> zrangebyscore(String key, double min, double max) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrangeByScore(key, min, max);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<byte[]> zrangebyscore(byte[] key, double min, double max) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrangeByScore(key, min, max);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zcount(String key, double min, double max) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zcount(key, min, max);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zcount(byte[] key, double min, double max) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zcount(key, min, max);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zcard(String key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zcard(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zcard(byte[] key) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zcard(key);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Double zscore(String key, String member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zscore(key,member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Double zscore(byte[] key, byte[] member) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zscore(key,member);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zremrangebyrank(String key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zremrangeByRank(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zremrangebyrank(byte[] key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zremrangeByRank(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zremrangebyscore(String key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zremrangeByScore(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Long zremrangebyscore(byte[] key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zremrangeByScore(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

	@Override
	public Set<byte[]> zrange(byte[] key, long start, long end) {
		ShardedJedis jedis = jedisSharded.getResource();
		try{
			return jedis.zrange(key, start, end);
		}finally{
			close(jedis);
		}
	}

}
