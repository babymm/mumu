package com.lovecws.mumu.oauth.server.service.impl;

import com.lovecws.mumu.common.core.utils.SerializeUtils;
import com.lovecws.mumu.common.redis.JedisClient;
import com.lovecws.mumu.oauth.server.entity.OauthClient;
import com.lovecws.mumu.oauth.server.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 使用redis来缓存 client信息
 */
@Transactional
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private JedisClient jedisClient;
    private static final String MUMU_OAUTH_CLIENT="mumu:oauth:client";
    private static final String MUMU_OAUTH_CLIENTID="mumu:oauth:clientid";

    @Override
    public OauthClient createClient(OauthClient client) {
        long id = jedisClient.decr(MUMU_OAUTH_CLIENTID);
        client.setId(id);
        client.setClientId(UUID.randomUUID().toString());
        client.setClientSecret(UUID.randomUUID().toString());
        jedisClient.hset(MUMU_OAUTH_CLIENT.getBytes(), client.getId().toString().getBytes(),SerializeUtils.serialize(client));
        return client;
    }

    @Override
    public OauthClient updateClient(OauthClient client) {
        jedisClient.hset(MUMU_OAUTH_CLIENT.getBytes(), client.getId().toString().getBytes(),SerializeUtils.serialize(client));
        return client;
    }

    @Override
    public void deleteClient(Long clientId) {
        jedisClient.hdel(MUMU_OAUTH_CLIENT.getBytes(),clientId.toString().getBytes());
    }

    @Override
    public OauthClient findOne(Long clientId) {
        byte[] clientBytes = jedisClient.hget(MUMU_OAUTH_CLIENT.getBytes(), clientId.toString().getBytes());
        return (OauthClient)SerializeUtils.deserialize(clientBytes);
    }

    @Override
    public List<OauthClient> findAll() {
        Map<byte[], byte[]> clientMaps = jedisClient.hgetall(MUMU_OAUTH_CLIENT.getBytes());
        List<OauthClient> clients=new ArrayList<OauthClient>();
        for (Map.Entry<byte[], byte[]> entry:clientMaps.entrySet()){
            clients.add((OauthClient)SerializeUtils.deserialize(entry.getValue()));
        }
        return clients;
    }

    @Override
    public OauthClient findByClientId(String clientId) {
        Map<byte[], byte[]> clientMaps = jedisClient.hgetall(MUMU_OAUTH_CLIENT.getBytes());
        List<OauthClient> clients=new ArrayList<OauthClient>();
        for (Map.Entry<byte[], byte[]> entry:clientMaps.entrySet()){
            OauthClient client = (OauthClient) SerializeUtils.deserialize(entry.getValue());
            if(client.getClientId().equals(clientId)){
                return client;
            }
        }
        return null;
    }

    @Override
    public OauthClient findByClientSecret(String clientSecret) {
        Map<byte[], byte[]> clientMaps = jedisClient.hgetall(MUMU_OAUTH_CLIENT.getBytes());
        List<OauthClient> clients=new ArrayList<OauthClient>();
        for (Map.Entry<byte[], byte[]> entry:clientMaps.entrySet()){
            OauthClient client = (OauthClient) SerializeUtils.deserialize(entry.getValue());
            if(client.getClientSecret().equals(clientSecret)){
                return client;
            }
        }
        return null;
    }
}
