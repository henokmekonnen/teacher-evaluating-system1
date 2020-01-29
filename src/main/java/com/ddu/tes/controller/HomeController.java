package com.ddu.tes.controller;

import com.ddu.tes.security.SecurityInfoMgn;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HomeController {

@Autowired
    UserService userService;

    @Autowired
    public SecurityInfoMgn securityInfoManager;


    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String index() {
      /*  User currentUser = securityInfoManager.getCurrentUser();
//        if(currentUser.getPasswordChangeRequired()){
//
//            model.addAttribute(Constants.TYPE, Constants.ALERT_TYPE_DANGER);
//            model.addAttribute(Constants.MESSAGE, "Please change your system generated password.");
//            return "redirect:/changePassword?fistTime="+Boolean.TRUE ;
//        }

        if (currentUser != null) {

            model.addAttribute("loggedInUserName", currentUser.getFirstName());

        }*/

        return "index";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.GET)
    public String login(Model model,HttpServletRequest request) {

        return "login";
    }

    @RequestMapping(value = {"/changePassword"}, method = RequestMethod.GET)
    public String changePassword(Model model, HttpSession session){

        ChangePasswordRequest request = new ChangePasswordRequest();
        model.addAttribute("changePasswordRequest", request);
        return "change-password";
    }

    @RequestMapping(value = {"/forgetPassword"}, method = RequestMethod.GET)
    public String forgottenPassword(Model model) {

        ForgetPasswordModel forgetPasswordModel = new ForgetPasswordModel();

        return "forgot-password";
    }

    @RequestMapping(value = "/confirmPasswordChange", method = RequestMethod.POST)
    public String initiateChangePassword(@ModelAttribute ChangePasswordRequest request, Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        if(StringUtils.isBlank(request.getOldPassword())){

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, "old password  must be provided");

            return "change-password";
        }

        if(StringUtils.isBlank(request.getNewPassword()) && StringUtils.isBlank(request.getConfirmPassword()) ){

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, "password  must be provided");

            return "change-password";
        }

        if(!request.getNewPassword().equals(request.getConfirmPassword())){

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, "password and confirm password don't match");

            return "change-password";
        }

        if(request.getNewPassword().equals(request.getOldPassword())){

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, "old password can't be used again");

            return "change-password";

        }


        try{

            ChangePasswordResponse responseModel= securityInfoManager.changePassword(request);

            if (!responseModel.getHasError()) {
                if(session != null) {
                    SecurityContextHolder.clearContext();
                    session.invalidate();
                }
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
                model.addAttribute(Constant.MESSAGE,"Password have been changed successfully, Please login back.");

                return "redirect:/login?logout" ;
            } else {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE,responseModel.getDescription());

                return "change-password";
            }
        }catch (Exception ex){

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);

                model.addAttribute(Constant.MESSAGE,  "Error while getting detail information. please try again later");

            return "change-password";

        }
    }
}
