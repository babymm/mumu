package com.lovecws.mumu.common.fdfs;

import com.lovecws.mumu.common.fdfs.service.FDFSAttachmentService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

/**
 * Created by Administrator on 2017/3/7.
 */
public class FDFSAttachmentServiceImplTest {

    public static void main(String[] args){
        upload();
        crc();
    }

    public static void upload(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-storage-fdfs.xml");
        applicationContext.start();
        FDFSAttachmentService fdfsAttachmentService = applicationContext.getBean(FDFSAttachmentService.class);

        //String url=fdfsAttachmentService.upload(new File("C:\\unintall.log"));
        //String url=fdfsAttachmentService.uploadWithUrl(new File("C:\\header.jpg"));
        //String url=fdfsAttachmentService.uploadWithUrl(new File("C:\\ERWin 7.3.zip"));
        //String url=fdfsAttachmentService.uploadWithUrl(new File("C:\\8月4日应用答疑（上）_知识讲解.avi"));
        String url = fdfsAttachmentService.uploadWithUrl(new File("D:\\logs\\error.log"));
        System.out.println(url);
        applicationContext.stop();
    }

    public static void crc(){
        CRC32 crc32 = new CRC32();
        FileInputStream fileinputstream = null;
        CheckedInputStream checkedinputstream = null;
        String crc = null;
        try {
            fileinputstream = new FileInputStream(new File("D:\\logs\\error.log"));
            checkedinputstream = new CheckedInputStream(fileinputstream, crc32);
            while (checkedinputstream.read() != -1) {
            }
            crc = Long.toHexString(crc32.getValue()).toUpperCase();
            System.out.println(crc);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileinputstream != null) {
                try {
                    fileinputstream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            if (checkedinputstream != null) {
                try {
                    checkedinputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
