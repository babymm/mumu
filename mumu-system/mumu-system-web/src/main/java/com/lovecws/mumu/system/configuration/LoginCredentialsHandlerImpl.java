package com.lovecws.mumu.system.configuration;

import com.lovecws.mumu.common.security.shiro.credentials.LoginCredentialsHandler;
import com.lovecws.mumu.system.controller.system.message.SysMessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 登陆业务逻辑操作器
 */
@Component
public class LoginCredentialsHandlerImpl implements LoginCredentialsHandler{

    @Autowired
    private SysMessageHandler messageHandler;

    @Override
    public void before() {

    }

    @Override
    public void after() {
        messageHandler.handler();
    }
}
