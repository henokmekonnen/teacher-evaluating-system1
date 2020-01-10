package com.ddu.tes.controller.question;


import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;
import com.ddu.tes.controller.model.question.EditQuestionRequestModel;
import com.ddu.tes.controller.model.question.EditQuestionResponseModel;
import com.ddu.tes.service.lookup.GetAllLookUpList;
import com.ddu.tes.service.lookup.LookUpService;
import com.ddu.tes.service.question.*;
import com.ddu.tes.service.validation.ValidationService;
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
import java.util.Map;

@Controller
@RequestMapping("/question")
@SessionAttributes({"createQuestionRequestModel", "editQuestionRequestModel"})
public class QuestionController {
    private static final Log logger = LogFactory.getLog(QuestionController.class);

    @Autowired
    public ValidationService validationService;
    @Autowired
    QuestionServices questionServices;
     @Autowired
    LookUpService lookUpService;


    @RequestMapping(value = "/createQuestion", method = RequestMethod.GET)
    public String createQuestion(Model model, HttpSession session, RedirectAttributes redirectAttributes) {

        try {
            GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
            model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());
                      CreateQuestionRequestModel createQuestionRequestModel = new CreateQuestionRequestModel();
            model.addAttribute("createQuestionRequestModel",createQuestionRequestModel);
            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());

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
            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());
            GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
            model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());

            if (StringUtils.isBlank(confirmCreateQuestion.getQuestion())){
                result.rejectValue("question", "error.question", "Please provide question.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide question.");
                return "question/create-question";

            }


            boolean questionExist = questionServices.questionExist(confirmCreateQuestion.getQuestion());

            if(!questionExist){

                result.rejectValue("question", "error.question", "Question Already exists.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "question Already exists.");
                return "question/create-question";
            }

            if (StringUtils.isBlank(confirmCreateQuestion.getTypeLookLp())) {
                result.rejectValue("typeLookLp", "error.typeLookLp", "Please provide typeLookLp name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide typeLookLp name.");
                return "question/edit-question";
            }
            for(Map<String, Object> lookUp :lookUpList.getLookUpList()){

                if(lookUp.get("lookUpId").equals(confirmCreateQuestion.getTypeLookLp())){

                    confirmCreateQuestion.setDescName(lookUp.get("descrption").toString());
                }
            }

            if (StringUtils.isBlank(confirmCreateQuestion.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "question/edit-question";

            }



            if (StringUtils.isBlank(confirmCreateQuestion.getQuestionType())){
                result.rejectValue("questionType", "error.questionType", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide questionType.");
                return "question/create-question";

            }
            for(Map<String, Object> questionType :questionTypeList.getQuestionTypeList()){

                if(questionType.get("questionTypeId").equals(confirmCreateQuestion.getQuestionType())){

                    confirmCreateQuestion.setDescName(questionType.get("description").toString());
                }
            }


            return "question/create-question-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/create-question";
        }

    }

    @RequestMapping(value = "/createQuestion", method = RequestMethod.POST)
    public String createQuestion(@ModelAttribute CreateQuestionRequestModel confirmCreateQuestion, BindingResult result, Model model) {

        try {

            GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
            model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());
            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());

            CreateQuestionResponseModel responseModel = questionServices.createQuestion(confirmCreateQuestion);

            if(responseModel.getStatusCode() != 0){
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
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("createQuestionRequestModel",confirmCreateQuestion);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/create-question";
        }

    }

    @RequestMapping(value = "/questionList", method = RequestMethod.GET)
    public String questionList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());
            return "question/question-list";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/question-list";
        }

    }

    @RequestMapping(value = "/teacherPage", method = RequestMethod.GET)
    public String questionnList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());
            return "question/teacher-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/teacher-page";
        }

    }



    @RequestMapping(value = "/editQuestion/{questionId}", method = RequestMethod.GET)
    public String editQuestion(Model model, @PathVariable(name = "questionId") Integer typeLookLp) {

        GetAllQuestion questionListResult = questionServices.getAllQuestion();
        model.addAttribute("questionList", questionListResult.getQuestionList());
        GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
        model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());
        try {

            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());
            if(typeLookLp == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide id");
                return "question/question-list";
            }

            GetQuestionResult result =  questionServices.getQuestion(typeLookLp);

            if (result.getStatusCode() != 0) {
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, result.getStatusMessage());
                return "question/question-list";
            }
            EditQuestionRequestModel editQuestionRequestModel = new EditQuestionRequestModel();

            editQuestionRequestModel.setQuestion(result.getQuestion());
            editQuestionRequestModel.setQuestionType(result.getQuestionType());
            editQuestionRequestModel.setDescription(result.getDescription());
            editQuestionRequestModel.setTypeLookLp(result.getTypeLookLp());
            editQuestionRequestModel.setQuestionId(result.getQuestionId());


            model.addAttribute("editQuestionRequestModel", editQuestionRequestModel);


            return "question/edit-question";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/question-list";
        }

    }


    @RequestMapping(value = "/confirmEditQuestion", method = RequestMethod.POST)
    public String confirmEditQuestion(@Valid EditQuestionRequestModel confirmEditQuestion, BindingResult result, Model model) {

        try {

            List<String> errorList = new ArrayList<String>();
            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());
            GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
            model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());

            model.addAttribute("confirmEditQuestion",confirmEditQuestion);

            if (StringUtils.isBlank(confirmEditQuestion.getQuestion())){
                result.rejectValue("question", "error.question", "Please provide department name.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide question name.");
                return "question/edit-question";

            }



            GetQuestionResult result1 = questionServices.getQuestion(confirmEditQuestion.getQuestionId());

            if (result1.getStatusCode() != 0) {
                result.rejectValue("questionId", "error.questionId", "question Already exists.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "question Already exists.");
                return "question/edit-question";
            }

            if (StringUtils.isBlank(confirmEditQuestion.getDescription())){
                result.rejectValue("description", "error.description", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide description.");
                return "question/edit-question";

            }
            for(Map<String, Object> role :lookUpList.getLookUpList()){

                if(role.get("lookUpId").equals(confirmEditQuestion.getTypeLookLp())){

                    confirmEditQuestion.setTypeLookLp(role.get("descrption").toString());
                }
            }
            if (StringUtils.isBlank(confirmEditQuestion.getTypeLookLp())){
                result.rejectValue("typeLookLp", "error.typeLookLp", "Please provide typeLookLp name");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide typeLookLp name.");
                return "question/edit-question";

            }



            if (StringUtils.isBlank(confirmEditQuestion.getQuestionType())){
                result.rejectValue("questionType", "error.questionType", "Please provide valid description.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide questionType.");
                return "question/edit-question";

            }
            for(Map<String, Object> questionType :questionTypeList.getQuestionTypeList()){

                if(questionType.get("questionTypeId").equals(confirmEditQuestion.getQuestionType())){

                    confirmEditQuestion.setQstName(questionType.get("description").toString());
                }
            }


            return "question/edit-question-confirm";

        }catch (Exception ex){
            logger.error("error while creating question"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/edit-question";
        }

    }

    @RequestMapping(value = "/updateQuestion", method = RequestMethod.POST)
    public String editQuestion(@ModelAttribute EditQuestionRequestModel confirmEditQuestion, BindingResult result, Model model) {
        GetAllQuestionType questionTypeList= questionServices.getAllQuestionType();
        model.addAttribute("questionTypeList",questionTypeList.getQuestionTypeList());

        try {
             model.addAttribute("EditQuestionRequestModel",confirmEditQuestion);

            GetAllLookUpList lookUpList=lookUpService.getAllLookUp();
            model.addAttribute("lookUpList",lookUpList.getLookUpList());
            if(confirmEditQuestion.getQuestionId() == null){
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, " Invalid question id");
                return "question/edit-question";
            }

            EditQuestionResponseModel responseModel = questionServices.editQuestion(confirmEditQuestion);

            if(responseModel.getStatusCode() != 0){

                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "question/edit-question";
            }

            confirmEditQuestion = new EditQuestionRequestModel();

            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
            return "question/edit-question-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());

            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "question/edit-question";
        }

    }

}