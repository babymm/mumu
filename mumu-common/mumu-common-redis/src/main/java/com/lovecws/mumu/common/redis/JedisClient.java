package com.lovecws.mumu.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.Transaction;

/**
 * redis
 * @author ganliang
 */
public interface JedisClient {
	/********************************* common ************************************/
	/**
	 * 根据表达式获取key集合
	 * @param pattern 表达式
	 * @return
	 */
	Set<String> keys(String pattern);
	/**
	 * 根据表达式获取key集合
	 * @param pattern 表达式
	 * @return
	 */
	Set<byte[]> keys(byte[] pattern);

	/**
	 * 刷新数据
	 */
	void flushDB();

	/**
	 * 获取redis库缓存大小
	 * @return
	 */
	Long dbSize();
	
	/**
	 * 设置有效期
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(String key, int second);
	/**
	 * 设置有效期
	 * @param key
	 * @param second
	 * @return
	 */
	long expire(byte[] key, int second);

	/**
	 * 获取有效期
	 * @param key
	 * @return
	 */
	long ttl(String key);
	/**
	 * 获取有效期
	 * @param key
	 * @return
	 */
	long ttl(byte[] key);

	/**
	 * 删除缓存
	 * @param key 
	 * @return
	 */
	long del(String key);
	/**
	 * 删除缓存
	 * @param key 
	 * @return
	 */
	long del(byte[] key);
	/**
	 * 删除缓存
	 * @param key 
	 * @return
	 */
	Boolean exists(String key);
	/**
	 * 判断是否存在
	 * @param key 
	 * @return
	 */
	Boolean exists(byte[] key);
	/**
	 * 重名名
	 * @param key 
	 * @return
	 */
	String rename(String oldkey, String newkey);
	/**
	 * 重名名
	 * @param key 
	 * @return
	 */
	String rename(byte[] oldkey, byte[] newkey);
	
	/**
	 * 重名名
	 * @param key 
	 * @return
	 */
	Long renamenx(String oldkey, String newkey);
	/**
	 * 重名名
	 * @param key 
	 * @return
	 */
	Long renamenx(byte[] oldkey, byte[] newkey);
	/**
	 * 获取键类型
	 * @param key 
	 * @return
	 */
	String type(String key);
	
	/**
	 * 获取键类型
	 * @param key 
	 * @return
	 */
	String type(byte[] key);
	
	/**
	 * 获取redis事务
	 * @return
	 */
	Transaction multi();
	
	/**
	 * 获取redis
	 * @return
	 */
	Jedis getJedis();
	/**
	 * 获取分片redis
	 * @return
	 */
	ShardedJedis getShardedJedis();
	/**
	 * 获取集群redis
	 * @return
	 */
	JedisCluster getClusterJedis();
	
	/**
	 * 获取jedis管道
	 * @return
	 */
	Pipeline getPipeline();
	
	/**
	 * 获取 分片管道
	 * @return
	 */
	ShardedJedisPipeline getShardedJedisPipeline();

	/********************************* String ************************************/
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @return
	 */
	String set(String key, String value);
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @return
	 */
	String set(byte[] key, byte[] value);

	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @param expire 缓存时间
	 * @return
	 */
	String set(String key, String value, int expire);
	
	/**
	 * 设置缓存
	 * @param key
	 * @param value
	 * @param expire 缓存时间
	 * @return
	 */
	String set(byte[] key, byte[] value, int expire);

	/**
	 * 不存在时 才设置成功；存在则失败
	 * @param key
	 * @param value
	 * @return
	 */
	Long setnx(String key, String value);
	
	/**
	 * 不存在时 才设置成功；存在则失败
	 * @param key
	 * @param value
	 * @return
	 */
	Long setnx(byte[] key, byte[] value);
	
	/**
	 * 存在时  设置成功；否则失败
	 * @param key 缓存key
	 * @param second 缓存时间
	 * @param value 值
	 * @return
	 */
	String setex(String key, int second, String value);
	
	
	/**
	 * 存在时  设置成功；否则失败
	 * @param key 缓存key
	 * @param second 缓存时间
	 * @param value 值
	 * @return
	 */
	String setex(byte[] key, int second, byte[] value);
	
	/**
	 * 存在时  设置成功；否则失败
	 * @param keysvalues 缓存泛型
	 * @return
	 */
	String mset(String... keysvalues);
	
	/**
	 * 存在时  设置成功；否则失败
	 * @param keysvalues 缓存泛型
	 * @return
	 */
	String mset(byte[]... keysvalues);
	
	/**
	 * 字符串替换
	 * @param key 缓存key
	 * @param offset 开始索引
	 * @param value 值
	 * @return
	 */
	Long setrange(String key, int offset, String value);
	
	/**
	 * 字符串替换
	 * @param key 缓存key
	 * @param offset 开始索引
	 * @param value 值
	 * @return
	 */
	Long setrange(byte[] key, int offset, byte[] value);
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	String get(String key);
	
	/**
	 * 获取缓存
	 * @param key
	 * @return
	 */
	byte[] get(byte[] key);

	/**
	 * 获取自增值
	 * @param key
	 * @return
	 */
	long incr(String key);
	
	/**
	 * 获取自增值
	 * @param key
	 * @return
	 */
	long incr(byte[] key);
	
	/**
	 * 一次增量 incrby
	 * @param key
	 * @param incrby
	 * @return
	 */
	long incrby(String key, int incrby);
	
	/**
	 * 一次增量 incrby
	 * @param key
	 * @param incrby
	 * @return
	 */
	long incrby(byte[] key, int incrby);
	
	/**
	 * 字符串自减
	 * @param key
	 * @return
	 */
	long decr(String key);
	
	/**
	 * 字符串自减
	 * @param key
	 * @return
	 */
	long decr(byte[] key);
	
	/**
	 * 自减 decrby
	 * @param key
	 * @param decrby
	 * @return
	 */
	long decrby(String key, int decrby);
	
	/**
	 * 自减 decrby
	 * @param key
	 * @param decrby
	 * @return
	 */
	long decrby(byte[] key, int decrby);
	
	/**
	 * 先获取在设置
	 * @param key
	 * @return
	 */
	String getset(String key, String value);
	
	/**
	 * 先获取在设置
	 * @param key
	 * @return
	 */
	byte[] getset(byte[] key, byte[] value);
	
	/**
	 * 获取字符串从开始到结束
	 * @param key
	 * @return
	 */
	String getrange(String key, int start, int end);
	
	/**
	 * 获取字符串从开始到结束
	 * @param key
	 * @return
	 */
	byte[] getrange(byte[] key, int start, int end);
	
	/**
	 * 批量获取
	 * @param key
	 * @return
	 */
	List<String> mget(String... key);
	
	/**
	 * 批量获取
	 * @param key
	 * @return
	 */
	List<byte[]> mget(byte[]... key);
	
	/**
	 * 字符串追加
	 * @param key
	 * @param value
	 * @return
	 */
	Long append(String key, String value);
	
	/**
	 * 字符串追加
	 * @param key
	 * @param value
	 * @return
	 */
	Long append(byte[] key, byte[] value);
	
	/**
	 * 获取字符串长度
	 * @param key
	 * @return
	 */
	Long strlen(String key);
	
	/**
	 * 获取字符串长度
	 * @param key
	 * @return
	 */
	Long strlen(byte[] key);
	

	/*************************************************hash(散列)******************************************************/
	/**
	 * 哈希 设置缓存
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hset(String hkey, String key, String value);
	
	/**
	 * 哈希 设置缓存
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hset(byte[] hkey, byte[] key, byte[] value);
	
	/**
	 * key 不存在，则先创建。 如果 field 已经存在，返回 0，
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hsetnx(String hkey, String key, String value);
	
	/**
	 * key 不存在，则先创建。 如果 field 已经存在，返回 0，
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	long hsetnx(byte[] hkey, byte[] key, byte[] value);
	
	/**
	 * key 不存在，则先创建。 如果 field 已经存在，返回 0，
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	String hmset(String hkey, Map<String, String> hashMap);
	
	/**
	 * key 不存在，则先创建。 如果 field 已经存在，返回 0，
	 * @param hkey
	 * @param key
	 * @param value
	 * @return
	 */
	String hmset(byte[] hkey, Map<byte[], byte[]> hashMap);
	
	/**
	 * 哈希 获取缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	String hget(String hkey, String key);
	
	/**
	 * 哈希 获取缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	byte[] hget(byte[] hkey, byte[] key);
	
	/**
	 * 哈希 获取缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	List<String> hmget(String hkey, String... key);
	
	/**
	 * 哈希 获取缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	List<byte[]> hmget(byte[] hkey, byte[]... key);

	/**
	 * 删除哈希 缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	long hdel(String hkey, String... key);
	
	/**
	 * 删除哈希 缓存
	 * @param hkey
	 * @param key
	 * @return
	 */
	long hdel(byte[] hkey, byte[]... key);
	
	/**
	 * 哈希值自增
	 * @param hkey
	 * @param key
	 * @param increment
	 * @return
	 */
	long hincrby(String hkey, String key, int increment);
	/**
	 * 哈希值自增
	 * @param hkey
	 * @param key
	 * @param increment
	 * @return
	 */
	long hincrby(byte[] hkey, byte[] key, int increment);
	
	/**
	 * 键值是否存在
	 * @param hkey
	 * @param key
	 * @return
	 */
	Boolean hexists(String hkey, String key);
	
	/**
	 * 键值是否存在
	 * @param hkey
	 * @param key
	 * @return
	 */
	Boolean hexists(byte[] hkey, byte[] key);
	
	/**
	 * 键值是否存在
	 * @param hkey
	 * @return
	 */
	long hlen(String hkey);
	
	/**
	 * 键值是否存在
	 * @param hkey
	 * @return
	 */
	long hlen(byte[] hkey);
	
	/**
	 * 获取哈希所有的键
	 * @param hkey
	 * @return
	 */
	Set<String> hkeys(String hkey);
	/**
	 * 获取哈希所有的键
	 * @param hkey
	 * @return
	 */
	Set<byte[]> hkeys(byte[] hkey);
	/**
	 * 获取哈希所有的值
	 * @param hkey
	 * @return
	 */
	List<String> hvals(String hkey);
	/**
	 * 获取哈希所有的值
	 * @param hkey
	 * @return
	 */
	List<byte[]> hvals(byte[] hkey);
	/**
	 * 获取哈希所有的值
	 * @param hkey
	 * @return
	 */
	Map<String, String> hgetall(String hkey);
	
	/**
	 * 获取哈希所有的值
	 * @param hkey
	 * @return
	 */
	Map<byte[], byte[]> hgetall(byte[] hkey);
	
	/*************************************************list(列表)******************************************************/
	/**
	 * 列表左边添加值
	 * @param key
	 * @param value
	 * @return
	 */
	Long lpush(String key, String... values);
	/**
	 * 列表左边添加值
	 * @param key
	 * @param value
	 * @return
	 */
	Long lpush(byte[] key, byte[]... values);
	
	/**
	 * 列表右边添加值
	 * @param key
	 * @param value
	 * @return
	 */
	Long rpush(String key, String... values);
	/**
	 * 列表右边添加值
	 * @param key
	 * @param value
	 * @return
	 */
	Long rpush(byte[] key, byte[]... values);
	
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param value
	 * @return
	 */
	Long linsert(String key, String pivot, String value);
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param value
	 * @return
	 */
	Long linsert(byte[] key, byte[] pivot, byte[] value);
	
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	String lset(String key, int index, String value);
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	String lset(byte[] key, int index, byte[] value);
	
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	Long lrem(String key, long index, String value);
	/**
	 * 在 key 对应 list 的特定位置之前或之后添加字符串元素
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	Long lrem(byte[] key, long index, byte[] value);
	
	/**
	 * 保留指定 key 的值范围内的数据
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	String ltrim(String key, int start, int end);
	/**
	 * 保留指定 key 的值范围内的数据
	 * @param key
	 * @param index 角标
	 * @param value 
	 * @return
	 */
	String ltrim(byte[] key, int start, int end);
	
	/**
	 * 从左边删除
	 * @param key
	 * @return
	 */
	String lpop(String key);
	
	/**
	 * 从左边删除
	 * @param key
	 * @return
	 */
	byte[] lpop(byte[] key);
	
	/**
	 * 从右边删除
	 * @param key
	 * @return
	 */
	String rpop(String key);
	
	/**
	 * 从右边删除
	 * @param key
	 * @return
	 */
	byte[] rpop(byte[] key);
	
	/**
	 * 从第一个 list 的尾部移除元素并添加到第二个 list 的头部,最后返回被移除的元素值，整个操 作是原子的.如果第一个 list 是空或者不存在返回 nil
	 * @param key
	 * @return
	 */
	String rpoplpush(String srckey, String dstkey);
	
	/**
	 * 从第一个 list 的尾部移除元素并添加到第二个 list 的头部,最后返回被移除的元素值，整个操 作是原子的.如果第一个 list 是空或者不存在返回 nil
	 * @param key
	 * @return
	 */
	byte[] rpoplpush(byte[] srckey, byte[] dstkey);
	
	/**
	 * 返回名称为 key 的 list 中 index 位置的元素
	 * @param key
	 * @return
	 */
	String lindex(String key, int index);
	
	/**
	 * 返回名称为 key 的 list 中 index 位置的元素
	 * @param key
	 * @return
	 */
	byte[] lindex(byte[] key, int index);
	
	/**
	 * 返回名称为 key 的 list长度
	 * @param key
	 * @return
	 */
	Long llen(String key);
	
	/**
	 * 返回名称为 key 的 list长度
	 * @param key
	 * @return
	 */
	Long llen(byte[] key);
	
	/**
	 * 返回名称为 key 的 list
	 * @param key
	 * @return
	 */
	List<String> lrange(String key, long begin, long end);
	
	
	/**
	 * 返回名称为 key 的 list
	 * @param key
	 * @return
	 */
	List<byte[]> lrange(byte[] key, long begin, long end);
	

	
	/*************************************************set(集合)******************************************************/
	/**
	 * 添加
	 * @param key
	 * @param members
	 * @return
	 */
	Long sadd(String key, String... members);
	/**
	 * 添加
	 * @param key
	 * @param members
	 * @return
	 */
	Long sadd(byte[] key, byte[]... members);
	/**
	 * 删除
	 * @param key
	 * @param members
	 * @return
	 */
	Long srem(String key, String... members);
	/**
	 * 删除
	 * @param key
	 * @param members
	 * @return
	 */
	Long srem(byte[] key, byte[]... members);
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	String spop(String key);
	/**
	 * 删除
	 * @param key
	 * @return
	 */
	byte[] spop(byte[] key);
	
	/**
	 * 比较两个集合
	 * @param keys
	 * @param key2
	 * @return
	 */
	Set<String> sdiff(String... keys);
	
	/**
	 * 比较两个集合
	 * @param keys
	 * @param key2
	 * @return
	 */
	Set<byte[]> sdiff(byte[]... keys);
	
	/**
	 * 比较两个集合并且将结果存储到key
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sdiffstore(String dstkey, String... keys);
	
	/**
	 * 比较两个集合并且将结果存储到key
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sdiffstore(byte[] dstkey, byte[]... keys);
	
	/**
	 * 返回所有给定 key 的交集
	 * @param keys
	 * @return
	 */
	Set<String> sinter(String... keys);
	
	/**
	 * 返回所有给定 key 的交集
	 * @param keys
	 * @return
	 */
	Set<byte[]> sinter(byte[]... keys);
	
	/**
	 * 返回所有给定 key 的交集，并将结果存为另一个 key
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sinterstore(String dstkey, String... keys);
	/**
	 * 返回所有给定 key 的交集，并将结果存为另一个 key
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sinterstore(byte[] dstkey, byte[]... keys);
	
	/**
	 * 返回所有给定 key 的并集
	 * @param keys
	 * @return
	 */
	Set<String> sunion(String... keys);
	/**
	 * 返回所有给定 key 的并集
	 * @param keys
	 * @return
	 */
	Set<byte[]> sunion(byte[]... keys);
	/**
	 * 返回所有给定 key 的并集
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sunionstore(String dstkey, String... keys);
	/**
	 * 返回所有给定 key 的并集
	 * @param dstkey
	 * @param keys
	 * @return
	 */
	Long sunionstore(byte[] dstkey, byte[]... keys);
	
	/**
	 * 从第一个 key 对应的 set 中移除 member 并添加到第二个对应 set 中
	 * @param key
	 * @param members
	 * @return
	 */
	Long smove(String srckey, String dstkey, String member);
	/**
	 * 从第一个 key 对应的 set 中移除 member 并添加到第二个对应 set 中
	 * @param key
	 * @param members
	 * @return
	 */
	Long smove(byte[] srckey, byte[] dstkey, byte[] member);
	
	/**
	 * 返回名称为 key 的 set 的元素个数
	 * @param key
	 * @return
	 */
	Long scard(String key);
	/**
	 * 返回名称为 key 的 set 的元素个数
	 * @param key
	 * @return
	 */
	Long scard(byte[] key);
	
	/**
	 * 返回名称为 key 的 set 的元素个数
	 * @param key
	 * @param member
	 * @return
	 */
	Boolean sismember(String key, String member);
	/**
	 * 返回名称为 key 的 set 的元素个数
	 * @param key
	 * @param member
	 * @return
	 */
	Boolean sismember(byte[] key, byte[] member);
	
	/**
	 * 随机返回名称为 key 的 set 的一个元素，但是不删除元素
	 * @param key
	 * @return
	 */
	String srandmember(String key);
	
	/**
	 * 随机返回名称为 key 的 set 的一个元素，但是不删除元素
	 * @param key
	 * @return
	 */
	byte[] srandmember(byte[] key);
	
	/**
	 * 随机返回名称为 key 的 set
	 * @param key
	 * @return
	 */
	Set<String> smembers(String key);
	
	/**
	 * 随机返回名称为 key 的 set
	 * @param key
	 * @return
	 */
	Set<byte[]> smembers(byte[] key);
	
	
	/*************************************************zset(有序集合)******************************************************/
	/**
	 * 添加
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Long zadd(String key, double score, String member);
	/**
	 * 添加
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Long zadd(byte[] key, double score, byte[] member);
	
	/**
	 * 移除
	 * @param key
	 * @param members
	 * @return
	 */
	Long zrem(String key, String... members);
	/**
	 * 移除
	 * @param key
	 * @param members
	 * @return
	 */
	Long zrem(byte[] key, byte[]... members);
	
	/**
	 * 自增
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Double zincrby(String key, double score, String member);
	/**
	 * 自增
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 */
	Double zincrby(byte[] key, double score, byte[] member);
	
	/**
	 * 返回名称为 key 的 zset 中 member 元素的排名(按 score 从小到大排序)即下标
	 * @param key
	 * @param member
	 * @return
	 */
	Long zrank(String key, String member);
	/**
	 * 返回名称为 key 的 zset 中 member 元素的排名(按 score 从小到大排序)即下标
	 * @param key
	 * @param member
	 * @return
	 */
	Long zrank(byte[] key, byte[] member);
	
	/**
	 * 返回名称为 key 的 zset 中 member 元素的排名(按 score 从大到小排序)即下标
	 * @param key
	 * @param member
	 * @return
	 */
	Long zrevrank(String key, String member);
	/**
	 * 返回名称为 key 的 zset 中 member 元素的排名(按 score 从大到小排序)即下标
	 * @param key
	 * @param member
	 * @return
	 */
	Long zrevrank(byte[] key, byte[] member);
	
	/**
	 * 返回名称为 key 的 zset（ 按 score 从大到小排序）中的 index 从 start 到 end 的所有元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> zrevrange(String key, long start, long end);
	/**
	 * 返回名称为 key 的 zset（ 按 score 从大到小排序）中的 index 从 start 到 end 的所有元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<byte[]> zrevrange(byte[] key, long start, long end);
	
	/**
	 * 返回集合中 score 在给定区间的元素
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Set<String> zrangebyscore(String key, double min, double max);
	
	/**
	 * 返回集合中 score 在给定区间的元素
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Set<byte[]> zrangebyscore(byte[] key, double min, double max);
	
	/**
	 * 返回集合中 score 在给定区间的数量
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Long zcount(String key, double min, double max);
	/**
	 * 返回集合中 score 在给定区间的数量
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 */
	Long zcount(byte[] key, double min, double max);
	
	/**
	 * 返回集合中元素个数
	 * @param key
	 * @return
	 */
	Long zcard(String key);
	/**
	 * 返回集合中元素个数
	 * @param key
	 * @return
	 */
	Long zcard(byte[] key);
	
	/**
	 * 返回给定元素对应的 score
	 * @param key
	 * @param member
	 * @return
	 */
	Double zscore(String key, String member);
	/**
	 * 返回给定元素对应的 score
	 * @param key
	 * @param member
	 * @return
	 */
	Double zscore(byte[] key, byte[] member);
	
	/**
	 * 删除集合中排名在给定区间的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Long zremrangebyrank(String key, long start, long end);
	
	/**
	 * 删除集合中排名在给定区间的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Long zremrangebyrank(byte[] key, long start, long end);
	
	/**
	 * 删除集合中 score 在给定区间的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Long zremrangebyscore(String key, long start, long end);
	/**
	 * 删除集合中 score 在给定区间的元素
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Long zremrangebyscore(byte[] key, long start, long end);
	
	/**
	 * 获取集合
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<String> zrange(String key, long start, long end);
	/**
	 * 获取集合
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	Set<byte[]> zrange(byte[] key, long start, long end);
}
