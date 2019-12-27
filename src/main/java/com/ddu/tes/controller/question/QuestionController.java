package com.ddu.tes.controller.question;

import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.question.QuestionServices;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/question")
@SessionAttributes("createQuestionRequestModel")
public class QuestionController {
    private static final Log logger = LogFactory.getLog(QuestionController.class);
    @Autowired
    SqlRepository sqlRepository;

    @Autowired
     QuestionServices questionServices;

    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public String createQuestion(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {

            CreateQuestionRequestModel createQuestionRequestModel = new CreateQuestionRequestModel();
            model.addAttribute("createQuestionRequestModel",createQuestionRequestModel);
            return "question/create-question";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/create-question";
        }

    }


    @RequestMapping(value = "/confirmCreateQuestion", method = RequestMethod.POST)
    public String confirmCreateQuestion(@Valid CreateQuestionRequestModel confirmCreateQuestion, BindingResult result, Model model) {

        try {
            List<String> errorList = new ArrayList<String>();
            model.addAttribute("createQuestionRequest",confirmCreateQuestion);

            if (StringUtils.isBlank(confirmCreateQuestion.getQuestion())){
                result.rejectValue("question", "error.question", "Please provide question name.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide question name.");
                return "question/create-question";

            }


//            boolean questionExist = questionServices.questionExist(confirmCreateQuestion.getQuestion());
//
//            if(!questionExist){
//
//                result.rejectValue("question", "error.question", "Question Already exists.");
//                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
//                model.addAttribute(Constant.MESSAGE, "Question Already exists.");
//                return "question/create-question";
//            }

            if (StringUtils.isBlank(confirmCreateQuestion.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "question/create-question";

            }


            return "question/create-question-confirm";

        }catch (Exception ex){
            logger.error("error while creating question"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/create-question";
        }

    }
    @RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute CreateQuestionRequestModel confirmCreateQuestion, BindingResult result, Model model) {

        try {

            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);

            CreateQuestionResponseModel responseModel = questionServices.createQuestion(confirmCreateQuestion);
            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
            if(responseModel.getStatusCode() == 0){
                model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "question/create-question";
            }

            confirmCreateQuestion = new CreateQuestionRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "question/create-question-success";

        }catch (Exception ex){
            logger.error("error while creating question"+ ex, ex.getCause());
            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
             model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/create-question";
        }

    }


}
