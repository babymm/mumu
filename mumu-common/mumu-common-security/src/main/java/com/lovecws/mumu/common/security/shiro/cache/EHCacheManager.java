package com.lovecws.mumu.common.security.shiro.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EHCacheManager implements CacheManager{

	@Autowired(required = false)
	private EhCacheManager cacheManager;
	
	/**
	 * 存入缓存
	 * @param cacheName 缓存名称
	 * @param key 缓存key
	 * @param value 缓存值
	 */
	@Override
	public Object put(String cacheName,String key,Object value){
		Cache<Object, Object> cache = cacheManager.getCache(cacheName);
		if(cache==null){
			cacheManager.getCacheManager().addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
		}
		return cache.put(key, value);
	}
	
	/**
	 * 获取缓存的值
	 * @param cacheName 缓存名称
	 * @param key 缓存key
	 * @return
	 */
	@Override
	public Object get(String cacheName,String key){
		Cache<Object, Object> cache = cacheManager.getCache(cacheName);
		return cache.get(key);
	}
	
	/**
	 * 删除缓存数据 缓存名称
	 * @param cacheName 缓存key
	 * @param key
	 * @return
	 */
	@Override
	public Object remove(String cacheName,String key){
		Cache<Object, Object> cache = cacheManager.getCache(cacheName);
		cacheManager.getCacheManager().removeCache(cacheName);
		return cache.remove(key);
	}
	
	/**
	 * 删除缓存数据 缓存名称
	 * @param cacheName 缓存key
	 * @param key
	 * @return
	 */
	@Override
	public Object empty(String cacheName){
		cacheManager.getCache(cacheName).clear();
		return null;
	}
	
}
