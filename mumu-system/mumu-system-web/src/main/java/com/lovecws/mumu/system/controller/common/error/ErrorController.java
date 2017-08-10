package com.lovecws.mumu.system.controller.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/30.
 */
@Controller
@RequestMapping("/common/error")
public class ErrorController {

    @RequestMapping("/404")
    public String notFound(){
        return "common/error/404";
    }

    @RequestMapping("/500")
    public String serverError(){
        return "common/error/500";
    }

    @RequestMapping("/unauth")
    public String unauth(){
        return "common/error/unauth";
    }
}
