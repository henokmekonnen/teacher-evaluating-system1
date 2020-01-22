package com.ddu.tes.controller;

import com.ddu.tes.data.modle.User;
import com.ddu.tes.security.SecurityInfoMgn;
import com.ddu.tes.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class HomeController {

@Autowired
    UserService userService;

    @Autowired
    public SecurityInfoMgn securityInfoManager;


    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(Model model, HttpServletRequest request) {
        User currentUser = securityInfoManager.getCurrentUser();
//        if(currentUser.getPasswordChangeRequired()){
//
//            model.addAttribute(Constants.TYPE, Constants.ALERT_TYPE_DANGER);
//            model.addAttribute(Constants.MESSAGE, "Please change your system generated password.");
//            return "redirect:/changePassword?fistTime="+Boolean.TRUE ;
//        }

        if (currentUser != null) {

            model.addAttribute("loggedInUserName", currentUser.getFirstName());

        }

        return "dashboard/dashboard";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest request) {

        return "login";
    }

}
