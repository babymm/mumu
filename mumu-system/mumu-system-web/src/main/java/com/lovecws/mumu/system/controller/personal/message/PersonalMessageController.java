package com.lovecws.mumu.system.controller.personal.message;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/8/10/010.
 */
@Controller
@RequestMapping("/personal/message")
public class PersonalMessageController {

    /**
     * 进入个人消息页面
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String  message(){
        return "personal/message/index";
    }
}
