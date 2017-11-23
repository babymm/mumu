package com.lovecws.mumu.common.core.serialize;

import com.caucho.hessian.io.*;
import com.jcraft.jsch.UserInfo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: hessian 序列化工具
 * @date 2017-11-22 14:17
 */
public class HessianSerializeUtil {

    /**
     * hession序列化
     *
     * @param obj         对象
     * @param hessianType hession序列化方式 HESSIAN、HESSIAN2、HessianSerializer
     * @return
     * @throws IOException
     */
    public static byte[] serialize(Object obj, HessianType hessianType) {
        if (obj == null) return null;
        if (hessianType == null) hessianType = HessianType.HESSIAN;
        ByteArrayOutputStream os = null;
        AbstractHessianOutput output = null;
        try {
            os = new ByteArrayOutputStream();
            switch (hessianType) {
                case HESSIAN:
                    output = new HessianOutput(os);
                    break;
                case HESSIAN2:
                    output = new Hessian2Output(os);
                    break;
                case HessianSerializer:
                    output = new HessianSerializerOutput(os);
                    break;
                default:
                    output = new HessianOutput(os);
                    break;
            }
            output.writeObject(obj);
            output.close();
            return os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * hessian反序列化
     *
     * @param bytes       hessian序列化字节数组
     * @param hessianType hessian序列化类型 HESSIAN、HESSIAN2、HessianSerializer
     * @return
     */
    public static Object deserialize(byte[] bytes, HessianType hessianType) {
        if (bytes == null) return null;
        if (hessianType == null) hessianType = HessianType.HESSIAN;
        ByteArrayInputStream is = null;
        AbstractHessianInput input = null;
        try {
            is = new ByteArrayInputStream(bytes);
            switch (hessianType) {
                case HESSIAN:
                    input = new HessianInput(is);
                    break;
                case HESSIAN2:
                    input = new Hessian2Input(is);
                    break;
                case HessianSerializer:
                    input = new HessianSerializerInput(is);
                    break;
                default:
                    input = new HessianInput(is);
                    break;
            }
            return input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public enum HessianType {
        HESSIAN,
        HESSIAN2,
        HessianSerializer
    }
}
