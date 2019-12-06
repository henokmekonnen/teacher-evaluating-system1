package com.ddu.tes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(HttpServletRequest request) {

        return "dashboard/dashboard";
    }

}
