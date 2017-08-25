package com.lovecws.mumu.oauth.server.service.impl;

import com.lovecws.mumu.common.redis.JedisClient;
import com.lovecws.mumu.oauth.server.service.ClientService;
import com.lovecws.mumu.oauth.server.service.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OAuthServiceImpl implements OAuthService {

    @Autowired
    private ClientService clientService;

    @Autowired
    private JedisClient jedisClient;
    private static final String MUMU_OAUTH_ACCESSTOKEN="mumu:oauth:accesstoken";
    private static final String MUMU_OAUTH_AUTHCODE="mumu:oauth:authcode";



    @Override
    public void addAccessToken(String accessToken, String username) {
        jedisClient.hset(MUMU_OAUTH_ACCESSTOKEN,accessToken,username);
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return jedisClient.hget(MUMU_OAUTH_ACCESSTOKEN,accessToken);
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return jedisClient.hget(MUMU_OAUTH_ACCESSTOKEN,accessToken)!=null;
    }

    @Override
    public void addAuthCode(String authCode, String username) {
        jedisClient.hset(MUMU_OAUTH_AUTHCODE,authCode,username);
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return jedisClient.hget(MUMU_OAUTH_AUTHCODE,authCode)!=null;
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return jedisClient.hget(MUMU_OAUTH_AUTHCODE,authCode);
    }

    @Override
    public boolean checkClientId(String clientId) {
        return clientService.findByClientId(clientId) != null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return clientService.findByClientSecret(clientSecret) != null;
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }
}
