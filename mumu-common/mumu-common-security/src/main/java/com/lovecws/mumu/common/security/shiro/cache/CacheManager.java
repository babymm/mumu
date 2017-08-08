package com.lovecws.mumu.common.security.shiro.cache;

public interface CacheManager {

	public Object put(String cacheName, String key, Object value);

	public Object get(String cacheName, String key);

	public Object remove(String cacheName, String key);

	public Object empty(String cacheName);
}
