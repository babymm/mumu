package com.lovecws.mumu.oauth.client;

import org.apache.shiro.authc.AuthenticationException;

/**
 * <p>User: Zhang Kaitao
 * <p>Date: 14-2-18
 * <p>Version: 1.0
 */
public class OAuth2AuthenticationException extends AuthenticationException {

    public OAuth2AuthenticationException(Throwable cause) {
        super(cause);
    }
}
