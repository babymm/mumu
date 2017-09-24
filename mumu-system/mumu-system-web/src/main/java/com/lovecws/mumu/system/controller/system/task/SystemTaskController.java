package com.lovecws.mumu.system.controller.system.task;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author babymm
 * @version 1.0-SNAPSHOT
 * @Description: 任务控制区
 * @date 2017-09-22 21:53
 */
@Controller
@RequestMapping("/system/task")
public class SystemTaskController {

    /**
     * 任务首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "system/task/index";
    }
}
