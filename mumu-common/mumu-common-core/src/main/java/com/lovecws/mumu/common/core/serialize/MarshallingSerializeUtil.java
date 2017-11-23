package com.lovecws.mumu.common.core.serialize;

import org.jboss.marshalling.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: jboss marshalling工具类
 * @date 2017-11-20 16:20
 */
public class MarshallingSerializeUtil {

    private static Marshaller marshaller = null;
    private static Unmarshaller unmarshaller = null;

    static {
        MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
        MarshallingConfiguration marshallingConfiguration = new MarshallingConfiguration();
        marshallingConfiguration.setVersion(5);
        try {
            marshaller = marshallerFactory.createMarshaller(marshallingConfiguration);
            unmarshaller = marshallerFactory.createUnmarshaller(marshallingConfiguration);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 序列化
     *
     * @param object 独享
     * @return
     */
    public static byte[] serialize(Object object) {
        if (object == null) return null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        OutputStreamByteOutput output = null;

        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            output = new OutputStreamByteOutput(byteArrayOutputStream);

            marshaller.start(output);
            marshaller.writeObject(object);
            marshaller.finish();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 反序列化
     *
     * @param bytes marshalling序列化的字节数组
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        if (bytes == null) return null;
        InputStreamByteInput inputStreamByteInput = null;
        ByteArrayInputStream byteArrayInputStream = null;

        try {
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            inputStreamByteInput = new InputStreamByteInput(byteArrayInputStream);

            unmarshaller.start(inputStreamByteInput);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayInputStream.close();
                inputStreamByteInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
