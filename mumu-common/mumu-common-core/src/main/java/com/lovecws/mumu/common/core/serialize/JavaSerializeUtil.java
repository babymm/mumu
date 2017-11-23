package com.lovecws.mumu.common.core.serialize;

import java.io.*;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: java 序列化工具
 * @date 2017-11-22 14:17
 */
public class JavaSerializeUtil {

    /**
     * 将对象序列化成字节数组
     *
     * @param o
     * @return
     */
    public static byte[] serialize(Object o) {
        if (o == null) {
            return null;
        }
        ObjectOutputStream outputStream = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            outputStream = new ObjectOutputStream(out);
            outputStream.writeObject(o);
            return out.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 将序列化的字节数据反序列化为对象
     *
     * @param b
     * @return
     */
    public static Object deserialize(byte[] b) {
        if (b == null) {
            return null;
        }
        ByteArrayInputStream bs = null;
        ObjectInputStream oin = null;
        try {
            bs = new ByteArrayInputStream(b);
            oin = new ObjectInputStream(bs);
            return oin.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
                oin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
