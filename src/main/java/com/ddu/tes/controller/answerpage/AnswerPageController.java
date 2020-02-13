package com.ddu.tes.controller.answerpage;


import com.ddu.tes.controller.model.answer.AnswerQuestionRequestModel;
import com.ddu.tes.controller.model.answer.AnswerQuestionResponseModel;
import com.ddu.tes.service.answer.AnswerService;
import com.ddu.tes.service.department.DepartmentService;
import com.ddu.tes.service.department.GetAllDepartmentListResult;
import com.ddu.tes.service.lookup.LookUpService;
import com.ddu.tes.service.question.GetAllQuestion;
import com.ddu.tes.service.question.QuestionServices;
import com.ddu.tes.service.user.GetAllUserListResult;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.utils.Constant;
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
@SessionAttributes({"answerQuestionRequestModel","answerTeacherQuestionRequestModel"})
public class AnswerPageController {
    private static final Log logger = LogFactory.getLog(AnswerPageController.class);
     @Autowired
    DepartmentService departmentService;

     @Autowired
     UserService userService;

    @Autowired
    AnswerService answerService;

    @Autowired
    QuestionServices questionServices;

    @Autowired
    LookUpService lookUpService;





    @RequestMapping(value = "/teacherpage", method = RequestMethod.GET)
    public String questionList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();
            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());
            AnswerQuestionRequestModel confirmAcceptAnswer =new AnswerQuestionRequestModel();
            model.addAttribute("answerTeacherQuestionRequestModel",confirmAcceptAnswer);
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

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();

            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());



            if (confirmAcceptAnswer.getCoreCompetence() == null){
                result.rejectValue("coreCompetence", "error.coreCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/student-question-page";

            }

            if (confirmAcceptAnswer.getProfessionalCompetence() == null){
                result.rejectValue("professionalCompetence", "error.ProfessionalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/student-question-page";

            }

            if (confirmAcceptAnswer.getEthicalCompetence() == null){
                result.rejectValue("ethicalCompetence", "error.ethicalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/student-question-page";

            }

            if (confirmAcceptAnswer.getTimeManagement() == null){
                result.rejectValue("timeManagement", "error.timeManagement", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/student-question-page";

            }

            answerService.acceptAnswer(confirmAcceptAnswer);

            return "answerpage/student-question-page-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/student-question-page";
        }

    }

    @RequestMapping(value = "/teacherConfirmAcceptAnswer", method = RequestMethod.POST)
    public String teacherConfirmAcceptAnswer(@Valid AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {

            List<String> errorList = new ArrayList<String>();

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();

            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            model.addAttribute("answerTeacherQuestionRequestModel",confirmAcceptAnswer);

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());



            if (confirmAcceptAnswer.getCoreCompetence() == null){
                result.rejectValue("coreCompetence", "error.coreCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/teacher-question-page";

            }

            if (confirmAcceptAnswer.getProfessionalCompetence() == null){
                result.rejectValue("professionalCompetence", "error.professionalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/teacher-question-page";

            }

            if (confirmAcceptAnswer.getEthicalCompetence() == null){
                result.rejectValue("ethicalCompetence", "error.ethicalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/teacher-question-page";

            }

            if (confirmAcceptAnswer.getTimeManagement() == null){
                result.rejectValue("timeManagement", "error.timeManagement", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
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

    @RequestMapping(value = "/chairedConfirmAcceptAnswer", method = RequestMethod.POST)
    public String chairedConfirmAcceptAnswer(@Valid AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {

            List<String> errorList = new ArrayList<String>();

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();

            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            model.addAttribute("answerChairedQuestionRequestModel",confirmAcceptAnswer);

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());



            if (confirmAcceptAnswer.getCoreCompetence() == null){
                result.rejectValue("coreCompetence", "error.coreCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/chaired-question-page";

            }

            if (confirmAcceptAnswer.getProfessionalCompetence() == null){
                result.rejectValue("professionalCompetence", "error.professionalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/chaired-question-page";

            }

            if (confirmAcceptAnswer.getEthicalCompetence() == null){
                result.rejectValue("ethicalCompetence", "error.ethicalCompetence", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/chaired-question-page";

            }

            if (confirmAcceptAnswer.getTimeManagement() == null){
                result.rejectValue("timeManagement", "error.timeManagement", "Please provide mulAnswer.");
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, "Please provide mulAnswer.");
                return "answerpage/chaired-question-page";

            }



            return "answerpage/chaired-question-page-confirm";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/chaired-question-page";
        }

    }

    @RequestMapping(value = "/acceptAnswer", method = RequestMethod.POST)
    public String acceptAnswer(@ModelAttribute AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {
            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();
            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());


            AnswerQuestionResponseModel responseModel = answerService.acceptAnswer(confirmAcceptAnswer);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "answerpage/student-question-page";
            }

            confirmAcceptAnswer = new AnswerQuestionRequestModel();

            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());

            return "answerpage/student-question-page-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/student-question-page";
        }

    }

    @RequestMapping(value = "/teacherAcceptAnswer", method = RequestMethod.POST)
    public String teacherAcceptAnswer(@ModelAttribute AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();

            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());


            AnswerQuestionResponseModel responseModel = answerService.acceptAnswer(confirmAcceptAnswer);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("answerTeacherQuestionRequestModel",confirmAcceptAnswer);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "answerpage/teacher-question-page";
            }

            confirmAcceptAnswer = new AnswerQuestionRequestModel();

            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());

            return "answerpage/teacher-question-page-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/teacher-question-page-page";
        }

    }

    @RequestMapping(value = "/chairedAcceptAnswer", method = RequestMethod.POST)
    public String chairedAcceptAnswer(@ModelAttribute AnswerQuestionRequestModel confirmAcceptAnswer, BindingResult result, Model model) {

        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
             GetAllUserListResult    userList=userService.getAllUsers();

            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());


            AnswerQuestionResponseModel responseModel = answerService.acceptAnswer(confirmAcceptAnswer);

            if(responseModel.getStatusCode() != 0){
                model.addAttribute("answerChairedQuestionRequestModel",confirmAcceptAnswer);
                model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
                model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());
                return "answerpage/chaired-question-page";
            }

            confirmAcceptAnswer = new AnswerQuestionRequestModel();

            model.addAttribute("answerChairedQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute("responseModel", responseModel);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_SUCCESS);
            model.addAttribute(Constant.MESSAGE, responseModel.getStatusMessage());

            return "answerpage/chaired-question-page-success";

        }catch (Exception ex){
            logger.error("error while creating dept"+ ex, ex.getCause());
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/chaired-question-page-page";
        }

    }

    @RequestMapping(value = "/chairedpage", method = RequestMethod.GET)
    public String questioList(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();
            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());
            AnswerQuestionRequestModel confirmAcceptAnswer =new AnswerQuestionRequestModel();
            model.addAttribute("answerTeacherQuestionRequestModel",confirmAcceptAnswer);
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

    @RequestMapping(value = "/acceptStudentAnswer", method = RequestMethod.GET)
    public String acceptStudentAnswer(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        try {

            GetAllDepartmentListResult departmentListResult = departmentService.getAllDepartments();
            GetAllUserListResult    userList=userService.getAllUsers();
            model.addAttribute("userList", userList.getUserList());
            model.addAttribute("departmentList", departmentListResult.getDepartmentList());

            AnswerQuestionRequestModel confirmAcceptAnswer =new AnswerQuestionRequestModel();
            model.addAttribute("answerQuestionRequestModel",confirmAcceptAnswer);

            GetAllQuestion questionListResult = questionServices.getAllQuestion();
            model.addAttribute("questionList", questionListResult.getQuestionList());

            return "answerpage/student-question-page";

        } catch (Exception ex) {
            logger.error("error while creating dept" + ex, ex.getCause());
            model.addAttribute(Constant.TYPE, Constant.ALERT_TYPE_DANGER);
            model.addAttribute(Constant.MESSAGE, ex.getMessage());
            return "answerpage/student-question-page";
        }

    }


}
