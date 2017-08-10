package com.lovecws.mumu.system.controller.common.upload;

import com.alibaba.fastjson.JSON;
import com.lovecws.mumu.common.core.utils.DateUtils;
import com.lovecws.mumu.common.core.utils.HttpClientUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/6/30.
 */
@Controller
@RequestMapping("/common/upload")
public class DocUploadController {

    //String web_url="http://192.168.11.26:8080/tool/doc/upload/async";
    String web_url="http://www.xiaomoc.top:8080/tool/doc/upload/async";

    @ResponseBody
    @RequestMapping(value = "/doc", method = RequestMethod.POST)
    public Object doc(HttpServletRequest request) {
        try {
            String upload = HttpClientUtil.upload(web_url, uploadFile(request));
            System.out.println(upload);
            return JSON.parse(upload);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 上传文件
     * @param request
     * @return
     */
    public File uploadFile(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        try {
            if (multipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                Iterator<String> iterator = multiRequest.getFileNames();
                while (iterator.hasNext()) {
                    String key = iterator.next();
                    MultipartFile multipartFile = multiRequest.getFile(key);
                    if (multipartFile != null) {
                        String name = multipartFile.getOriginalFilename();
                        String pathDir = request.getSession().getServletContext().getRealPath("/upload/" + DateUtils.currentTime());
                        File dirFile = new File(pathDir);
                        if (!dirFile.isDirectory()) {
                            dirFile.mkdirs();
                        }
                        String filePath = pathDir+File.separator+name;
                        File file = new File(filePath);
                        file.setWritable(true, false);

                        multipartFile.transferTo(file);
                        return file;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
