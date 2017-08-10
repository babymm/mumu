package com.lovecws.mumu.system.controller.common.image;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/common/image")
public class ImageController {

	/**
	 * 展示图片
	 * @return
	 */
	@RequestMapping(value="/show",method=RequestMethod.GET)
	public String show(String url,HttpServletRequest request){
		request.setAttribute("imageURL", url);
		return "common/image/show";
	}
	
	/**
	 * 显示图标
	 * @return
	 */
	@RequestMapping(value="/icon",method=RequestMethod.GET)
	public String icon(){
		return "common/image/icon";
	}

	/**
	 * 显示图标
	 * @return
	 */
	@RequestMapping(value="/avator",method=RequestMethod.GET)
	public String avator(){
		return "common/image/avator";
	}
}
