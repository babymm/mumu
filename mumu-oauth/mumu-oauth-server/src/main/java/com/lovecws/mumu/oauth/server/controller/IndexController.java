package com.lovecws.mumu.oauth.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>OauthUser: Zhang Kaitao
 * <p>Date: 14-2-14
 * <p>Version: 1.0
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }


    @RequestMapping("/main")
    public String main(Model model) {
        return "main";
    }

}
