package com.ddu.tes.controller.questionpage;

import com.ddu.tes.controller.user.UserController;
import com.ddu.tes.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/question")
//@SessionAttributes({"createUserRequestModel, editUserRequestModel"})
public class QuestionPageController {
    private static final Log logger = LogFactory.getLog(UserController.class);
    @RequestMapping(value = "/questionpage", method = RequestMethod.GET)
    public String createUser(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            return "question/question-page";

        }catch (Exception ex){
            logger.error("error while creating user"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "dashboard/dashboard";
        }
    }
}
