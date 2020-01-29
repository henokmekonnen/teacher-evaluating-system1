package com.ddu.tes.controller.dashboard;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class DashboardController {

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {



        return "dashboard/dashboard";
    }
}
