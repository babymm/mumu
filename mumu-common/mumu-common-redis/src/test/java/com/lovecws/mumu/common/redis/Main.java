package com.lovecws.mumu.common.redis;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Set;

/**
 * Hredis测试
 */
public class Main {

	/**
	 * 单机模式
	 */
	public static void standardAlone() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-jedis.xml");
		applicationContext.start();

		JedisClientStandaloneImpl jedisClientStandaloneImpl = applicationContext
				.getBean(JedisClientStandaloneImpl.class);
		jedisClientStandaloneImpl.set("userName", "甘亮");

		jedisClientStandaloneImpl.del("userName");
		applicationContext.close();
	}

	/**
	 * 集群模式
	 */
	public static void cluster() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-jedis.xml");
		applicationContext.start();

		JedisClientClusterImpl jedisClientCluster = applicationContext.getBean(JedisClientClusterImpl.class);
		String string = jedisClientCluster.set("a", "123456");
		System.out.println(string);
		
		Boolean exists = jedisClientCluster.exists("name");
		System.out.println("exists:"+exists);

		applicationContext.close();
	}

	/**
	 * 分片
	 */
	public static void sharding() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-jedis.xml");
		applicationContext.start();

		JedisClientShardImpl JedisClientShard = applicationContext
				.getBean(JedisClientShardImpl.class);
		for (int i = 0; i < 100; i++) {
			String set = JedisClientShard.set("userName"+i, ""+i);
			System.out.println(set);
		}

		System.out.println(JedisClientShard.get("userName"));
		applicationContext.close();
	}
	
	
	/**
	 * redis主从
	 */
	public static void sentinel() {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-jedis.xml");
		applicationContext.start();
		
		JedisClientSentinelImpl JedisClientSentinel = applicationContext
				.getBean(JedisClientSentinelImpl.class);
		for (int i = 0; i < 10; i++) {
			String set = JedisClientSentinel.set("userName"+i, ""+i);
			System.out.println(set);
		}
		System.out.println(JedisClientSentinel.get("userName"));
		applicationContext.close();
	}
	
	/**
	 * twemproxy代理服务器
	 */
	public static void twemproxy(){
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-jedis.xml");
		applicationContext.start();
	
		JedisClientTwemproxyImpl JedisClientTwemproxyImpl = applicationContext.getBean(JedisClientTwemproxyImpl.class);
		String set = JedisClientTwemproxyImpl.set("userName", "甘亮");
		System.out.println(set);
		
		Set<String> keys = JedisClientTwemproxyImpl.keys("*");
		for (String string : keys) {
			System.out.println(string);
		}
		applicationContext.close();
	}
	
	public static void main(String[] args) {
		twemproxy();
	}
}
