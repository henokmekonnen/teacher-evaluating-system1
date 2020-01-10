package com.ddu.tes.controller.answerpage;


import com.ddu.tes.service.lookup.LookUpService;
import com.ddu.tes.service.question.GetAllQuestion;
import com.ddu.tes.service.question.QuestionServices;
import com.ddu.tes.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/answerpage")
@SessionAttributes()
public class AnswerPageController {
    private static final Log logger = LogFactory.getLog(AnswerPageController.class);

    @Autowired
    QuestionServices questionServices;

    @Autowired
    LookUpService lookUpService;

    @RequestMapping(value = "/answerpage", method = RequestMethod.GET)
    public String questionList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
             model.addAttribute("questionList", questionListResult.getQuestionList());
            return "answerpage/answer-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/answer-page";
        }

    }

    @RequestMapping(value = "/chairedpage", method = RequestMethod.GET)
    public String questioList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());
            return "answerpage/chaired-question-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/answer-page";
        }

    }
    @RequestMapping(value = "/studentpage", method = RequestMethod.GET)
    public String questList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());
            return "answerpage/student-question-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/answer-page";
        }

    }


}
