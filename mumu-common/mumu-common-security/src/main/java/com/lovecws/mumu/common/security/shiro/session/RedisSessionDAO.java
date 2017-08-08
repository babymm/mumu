package com.lovecws.mumu.common.security.shiro.session;

import com.lovecws.mumu.common.core.utils.SerializeUtils;
import com.lovecws.mumu.common.redis.JedisClient;
import org.apache.log4j.Logger;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = Logger.getLogger(RedisSessionDAO.class);

    @Override
    public void update(Session session) throws UnknownSessionException {
    	//logger.info("update session "+session.toString());
    	this.saveSession(session);
    }

    /**
     * save session
     * @param session
     * @throws UnknownSessionException
     */
    private void saveSession(Session session) throws UnknownSessionException{
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }
        
        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        session.setTimeout(expire*1000);
        try {
            this.jedisClient.set(key, value, expire);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Session session) {
    	logger.info("delete session "+session.toString());
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }
        jedisClient.del(this.getByteKey(session.getId()));

    }

    //用来统计当前活动的session
    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<Session>();
        try {
            Set<byte[]> keys = jedisClient.keys((this.keyPrefix + "*").getBytes());
            if (keys != null && keys.size() > 0) {
                for (byte[] key : keys) {
                    Session s = (Session) SerializeUtils.deserialize(jedisClient.get(key));
                    sessions.add(s);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return sessions;
    }

    @Override
    protected Serializable doCreate(Session session) {
    	logger.info("save session "+session.toString());
    	
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null){
            logger.error("session id is null");
            return null;
        }
        try {
            byte[] c = jedisClient.get(this.getByteKey(sessionId));
            if (c == null) {
                return null;
            }
            Session s = (Session) SerializeUtils.deserialize(c);
            return s;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得byte[]型的key
     * @return
     */
    private byte[] getByteKey(Serializable sessionId){
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }


    /**
     * 默认shiro-redis的session对象前缀
     */

    private String keyPrefix = "mumu:shiro:redis:session:";

    /**
     * 默认设置缓存永不过期
     */
    private int expire = 0;

    @Autowired
    private JedisClient jedisClient;

    public JedisClient getJedisClient() {
        return jedisClient;
    }

    public void setJedisClient(JedisClient jedisClient) {
        this.jedisClient = jedisClient;
    }
    /**
     * Returns the Redis session keys
     * prefix.
     * @return The prefix
     */
    public String getKeyPrefix() {
        return keyPrefix;
    }

    /**
     * Sets the Redis sessions key
     * prefix.
     * @param keyPrefix The prefix
     */
    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}
