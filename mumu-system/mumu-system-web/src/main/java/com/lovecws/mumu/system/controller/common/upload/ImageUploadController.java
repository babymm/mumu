package com.lovecws.mumu.system.controller.common.upload;

import com.lovecws.mumu.common.core.response.ResponseEntity;
import com.lovecws.mumu.common.core.utils.UploadUtil;
import com.lovecws.mumu.common.fdfs.service.FDFSAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/common/upload")
public class ImageUploadController {

	@Autowired
	private FDFSAttachmentService fdfsAttachmentService;

	@ResponseBody
	@RequestMapping(value = "/img", method = RequestMethod.POST)
	public ResponseEntity img(HttpServletRequest request) {
		String upload=null;
		ResponseEntity responseEntity = null;
		try {
			List<String> uploadImage = UploadUtil.uploadImage(request);
			for (String filePath : uploadImage) {
				upload = fdfsAttachmentService.uploadWithUrl(new File(filePath));
				System.out.println(upload);
			}
			responseEntity=new ResponseEntity(200, "图片上传成功", upload);
		} catch (Exception e) {
			e.printStackTrace();
			responseEntity=new ResponseEntity(500, "图片上传失败", null);
		}
		return responseEntity;
	}

	/**
	 * layedit 富文本编译器
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/layedit", method = RequestMethod.POST)
	public Map layedit(HttpServletRequest request) {
		String upload=null;
		Map<String,Object> map=new HashMap<String,Object>();
		Map<String,Object> dataMap=new HashMap<String,Object>();
		try {
			List<String> uploadImage = UploadUtil.uploadImage(request);
			for (String filePath : uploadImage) {
				upload = fdfsAttachmentService.uploadWithUrl(new File(filePath));
			}
			map.put("code","0");
			map.put("msg","");
			dataMap.put("src",upload);
			dataMap.put("title","");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("code","500");
			map.put("msg","图片上传失败");
			dataMap.put("src","");
			dataMap.put("title","");
		}
		map.put("data",dataMap);
		return map;
	}

	/**
	 *  fullAvatarEditor 富文本头像上传器
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/fullAvatarEditor", method = RequestMethod.POST)
	public Map<String,Object> fullAvatarEditor(HttpServletRequest request){
		Map<String,Object> map=new HashMap<String,Object>();
		List<String> avatarUrls=new ArrayList<String>();
		try {
			List<String> uploadImage = UploadUtil.uploadImage(request);
			for (String filePath : uploadImage) {
				String upload = fdfsAttachmentService.uploadWithUrl(new File(filePath));
				avatarUrls.add(upload);
			}
			map.put("avatarUrls",avatarUrls);
			map.put("success",true);
			map.put("msg","Success");
		} catch (IOException e) {
			e.printStackTrace();
			map.put("success",false);
			map.put("msg","Failure");
		}
		return map;
	}
}
