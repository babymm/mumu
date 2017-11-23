package com.lovecws.mumu.common.ftp;

import com.jcraft.jsch.*;
import com.lovecws.mumu.common.ftp.exception.FtpException;

import java.util.Properties;

/**
 * Java Secure Channel
 * 
 * @author ShenHuaJie
 * @version 2016年5月20日 下午3:19:19
 */
public class SftpClient {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SftpClient.class);
    private Session session = null;
    private ChannelSftp channel = null;

    private SftpClient() {
    }

    public static SftpClient connect() {
        //return new SftpClient().init();
        return null;
    }

    public SftpClient init(String host, int port, String username, String password,int timeout,int aliveMax) {
        try {
            Properties config = new Properties();
            /*String host = PropertiesUtil.getString("sftp.host");
            int port = PropertiesUtil.getInt("sftp.port");
            String userName = PropertiesUtil.getString("sftp.user.name");
            String password = PropertiesUtil.getString("sftp.user.password");
            int timeout = PropertiesUtil.getInt("sftp.timeout");
            int aliveMax = PropertiesUtil.getInt("sftp.aliveMax");*/
            JSch jsch = new JSch(); // 创建JSch对象
            session = jsch.getSession(username, host, port); // 根据用户名，主机ip，端口获取一个Session对象
            if (password != null) {
                session.setPassword(password); // 设置密码
            }
            config.put("userauth.gssapi-with-mic", "no");
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config); // 为Session对象设置properties
            session.setTimeout(timeout); // 设置timeout时间
            session.setServerAliveCountMax(aliveMax);
            session.connect(); // 通过Session建立链接
            channel = (ChannelSftp)session.openChannel("sftp"); // 打开SFTP通道
            channel.connect(); // 建立SFTP通道的连接
            logger.info("SSH Channel connected.");
        } catch (JSchException e) {
            throw new FtpException("", e);
        }
        return this;
    }

    public void disconnect() {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
            logger.info("SSH Channel disconnected.");
        }
    }

    /** 发送文件 */
    public void put(String src, String dst) {
        try {
            channel.put(src, dst, new FileProgressMonitor());
        } catch (SftpException e) {
            throw new FtpException("", e);
        }
    }

    /** 获取文件 */
    public void get(String src, String dst) {
        try {
            channel.get(src, dst, new FileProgressMonitor());
        } catch (SftpException e) {
            throw new FtpException("", e);
        }
    }
}
