package com.lovecws.mumu.common.security.shiro.exception;

import org.apache.shiro.authc.AccountException;

/**
 * Created by Administrator on 2017/8/16/016.
 */
public class AccountUnActiveException extends AccountException {

    public AccountUnActiveException() {
    }

    public AccountUnActiveException(String message) {
        super(message);
    }

    public AccountUnActiveException(Throwable cause) {
        super(cause);
    }

    public AccountUnActiveException(String message, Throwable cause) {
        super(message, cause);
    }
}
