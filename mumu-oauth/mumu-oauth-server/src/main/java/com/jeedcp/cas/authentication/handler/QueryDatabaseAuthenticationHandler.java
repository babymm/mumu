package com.jeedcp.cas.authentication.handler;

import java.security.GeneralSecurityException;
import java.util.Map;

import com.jeedcp.services.support.LoginNameUtils;
import org.jasig.cas.adaptors.jdbc.AbstractJdbcUsernamePasswordAuthenticationHandler;
import org.jasig.cas.authentication.HandlerResult;
import org.jasig.cas.authentication.PreventedException;
import org.jasig.cas.authentication.UsernamePasswordCredential;
import org.jasig.cas.authentication.principal.SimplePrincipal;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;


import javax.security.auth.login.AccountNotFoundException;
import javax.security.auth.login.FailedLoginException;
import javax.validation.constraints.NotNull;

/**
 * cas数据库认证处理器
 * @author Jeedcp
 *
 */
public class QueryDatabaseAuthenticationHandler extends AbstractJdbcUsernamePasswordAuthenticationHandler {

    @NotNull
    private String sql;
    @NotNull
    private String sqlTel;
    @NotNull
    private String sqlEmail;

    /** {@inheritDoc} */
    @Override
    protected final HandlerResult authenticateUsernamePasswordInternal(final UsernamePasswordCredential credential)
            throws GeneralSecurityException, PreventedException {

        final String username = credential.getUsername();
        try {
            final Map<String, Object> queryMap;
            if (username.length()==11&&LoginNameUtils.isMobile(username)) {
                queryMap=getJdbcTemplate().queryForMap(this.sqlTel, username);
            } else if(LoginNameUtils.isMobile(username)){
                queryMap=getJdbcTemplate().queryForMap(this.sqlEmail, username);
            }else {
                queryMap=getJdbcTemplate().queryForMap(this.sql, username);
            }
            final String dbPassword=(String)queryMap.get("password");
            final String loginFlag=(String)queryMap.get("login_flag");
            final String encryptedPassword = this.getPasswordEncoder().encode(dbPassword.substring(0,16)+credential.getPassword());
            if (!dbPassword.substring(16).equals(encryptedPassword)) {
                throw new FailedLoginException("Password does not match value on record.");
            }else if(loginFlag.equals("0")){
                throw new FailedLoginException("This user is not allowed to login.");
            }
        } catch (final IncorrectResultSizeDataAccessException e) {
            if (e.getActualSize() == 0) {
                throw new AccountNotFoundException(username + " not found with SQL query");
            } else {
                throw new FailedLoginException("Multiple records found for " + username);
            }
        } catch (final DataAccessException e) {e.printStackTrace();
            throw new PreventedException("SQL exception while executing query for " + username, e);
        }
        return createHandlerResult(credential, new SimplePrincipal(username), null);
    }

    /**
     * @param sql The sql to set.
     */
    public void setSql(final String sql) {
        this.sql = sql;
    }

    public void setSqlTel(final String sqlTel) {
        this.sqlTel = sqlTel;
    }

    public void setSqlEmail(final String sqlEmail) {
        this.sqlEmail = sqlEmail;
    }
}