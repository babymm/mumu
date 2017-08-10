package com.lovecws.mumu.system.controller.common.select;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Administrator on 2017/4/19.
 */
@Controller
@RequestMapping("/common/select")
public class SelectController {

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    public String tree(){
        return "common/select/tree";
    }
}
