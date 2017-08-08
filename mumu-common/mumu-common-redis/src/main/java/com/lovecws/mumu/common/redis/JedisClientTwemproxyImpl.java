package com.lovecws.mumu.common.redis;

import java.util.Set;

/**
 * 使用Twemproxy 代理实现分片
 * @author lovecws
 */
public class JedisClientTwemproxyImpl extends JedisClientImpl{

	@Override
	public Set<String> keys(String pattern) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Set<byte[]> keys(byte[] pattern) {
		throw new UnsupportedOperationException();
	}
}
