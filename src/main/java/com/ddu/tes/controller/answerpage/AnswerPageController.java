package com.ddu.tes.controller.answerpage;


import com.ddu.tes.controller.model.answer.AnswerQuestionRequestModel;
import com.ddu.tes.controller.model.answer.AnswerQuestionResponseModel;
import com.ddu.tes.service.answer.AnswerService;
import com.ddu.tes.service.lookup.LookUpService;
import com.ddu.tes.service.question.GetAllQuestion;
import com.ddu.tes.service.question.QuestionServices;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/answerpage")
@SessionAttributes("answerQuestionRequestModel")
public class AnswerPageController {
    private static final Log logger = LogFactory.getLog(AnswerPageController.class);

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    LookUpService lookUpService;

    @RequestMapping(value = "/answerpage", method = RequestMethod.GET)
    public String questionList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            AnswerQuestionRequestModel confirmAcceptAnswer =new AnswerQuestionRequestModel();
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            GetAllQuestion questionListResult = questionServices.getAllQuestion();
             model.addAttribute("questionList", questionListResult.getQuestionList());
            return "answerpage/teacher-question-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/teacher-question-page";
        }

    }

    @RequestMapping(value = "/confirmAcceptAnswer", method = RequestMethod.POST)
    public String confirmAcceptAnswer(@Valid AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {
            List<String> errorList = new ArrayList<String>();
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());

            if (confirmAcceptAnswer.getMulAnswer() == null){
                result.rejectValue("mulAnswer", "error.mulAnswer", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/teacher-question-page";

            }




            if (StringUtils.isBlank(confirmAcceptAnswer.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "answerpage/teacher-question-page";

            }



            return "answerpage/teacher-question-page-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/teacher-question-page";
        }

    }
    @RequestMapping(value = "/acceptAnswer", method = RequestMethod.POST)
    public String createDepartment(@ModelAttribute AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {
            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());
            model.addAttribute("acceptAnswerRequestModel",confirmAcceptAnswer);

            AnswerQuestionResponseModel responseModel = answerService.acceptAnswer(confirmAcceptAnswer);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("acceptAnswerRequestModel",confirmAcceptAnswer);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "answerpage/teacher-question-page";
            }

            confirmAcceptAnswer = new AnswerQuestionRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "department/create-department-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("acceptAnswerRequestModel",confirmAcceptAnswer);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "department/create-department";
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
