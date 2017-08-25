package com.lovecws.mumu.oauth.server.service;

import com.lovecws.mumu.oauth.server.entity.OauthClient;

import java.util.List;

/**
 * <p>OauthUser: Zhang Kaitao
 * <p>Date: 14-1-28
 * <p>Version: 1.0
 */
public interface ClientService {

    public OauthClient createClient(OauthClient client);
    public OauthClient updateClient(OauthClient client);
    public void deleteClient(Long clientId);

    OauthClient findOne(Long clientId);

    List<OauthClient> findAll();

    OauthClient findByClientId(String clientId);
    OauthClient findByClientSecret(String clientSecret);

}
