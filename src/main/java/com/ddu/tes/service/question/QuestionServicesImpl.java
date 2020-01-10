package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;
import com.ddu.tes.controller.model.question.EditQuestionRequestModel;
import com.ddu.tes.controller.model.question.EditQuestionResponseModel;
import com.ddu.tes.data.modle.Question;
import com.ddu.tes.data.modle.QuestionType;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServicesImpl implements QuestionServices {
    private static final Log logger = LogFactory.getLog(QuestionServicesImpl.class);
    @Autowired
    SqlRepository sqlRepository;



    @Override
    public CreateQuestionResponseModel createQuestion(CreateQuestionRequestModel confirmCreateQuestion) {
        CreateQuestionResponseModel responseModel = new CreateQuestionResponseModel();

        try {
            Question newDepartment = new Question();

            newDepartment.setQuestion(confirmCreateQuestion.getQuestion());
            newDepartment.setTypeLookLp(confirmCreateQuestion.getTypeLookLp());
            newDepartment.setQuestionType(confirmCreateQuestion.getQuestionType());
            newDepartment.setDescription(confirmCreateQuestion.getDescription());
            newDepartment.setCreatedBy(Constant.SYSTEM);
            newDepartment.setAllowMultipleChoice(Boolean.FALSE);

            newDepartment = (Question)  sqlRepository.insert(newDepartment);

            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully created question");
            responseModel.setQuestion(newDepartment.getQuestion());
            responseModel.setDescription(newDepartment.getDescription());
            responseModel.setTypeLookLp(newDepartment.getTypeLookLp());
            responseModel.setQuestionType(newDepartment.getQuestionType());
            return  responseModel;

        }catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return  responseModel;
        }
    }

    @Override
    public GetQuestionResult getQuestion (final Integer question) {

        GetQuestionResult result = new GetQuestionResult();

        try {

            if(question==null){
                result.setStatusCode(1000);
                result.setStatusMessage("department name not found");
                result.setQuestionExists(Boolean.FALSE);
                return  null;
            }

            Question filter = new Question();
            filter.setQuestionId(question);

            Question quest = (Question) sqlRepository.findOne(filter);

            if(quest == null ){
                result.setStatusCode(1000);
                result.setStatusMessage("department not found");
                result.setQuestionExists(Boolean.FALSE);
                return  result;
            }

            result.setStatusCode(0);
            result.setQuestionId(quest.getQuestionId());
            result.setQuestion(quest.getQuestion());
            result.setQuestionType(quest.getQuestionType());
            result.setDescription(quest.getDescription());
            result.setTypeLookLp(quest.getTypeLookLp());
            result.setStatusMessage("Found");
            result.setQuestionExists(Boolean.TRUE);
            return  result;

        }catch (Exception ex){
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setQuestionExists(Boolean.FALSE);
            return  result;
        }
    }

@Override
public GetAllTeacherQuestion getTeacherQuestion (final String question){

    GetAllTeacherQuestion result = new GetAllTeacherQuestion();

    try {

        if (question == null) {
            result.setStatusCode(1000);
            result.setStatusMessage("department name not found");
            result.setTeacherQuestionExists(Boolean.FALSE);
            return null;
        }

        Question filter = new Question();
        filter.setTypeLookLp(question);

        Question quest = (Question) sqlRepository.findOne(filter);

        if (quest == null) {
            result.setStatusCode(1000);
            result.setStatusMessage("department not found");
            result.setTeacherQuestionExists(Boolean.FALSE);
            return result;
        }
        if (quest.getTypeLookLp().equalsIgnoreCase("teacher")) {
            result.setStatusCode(0);
            result.setQuestionId(quest.getQuestionId());
            result.setQuestion(quest.getQuestion());
            result.setQuestionType(quest.getQuestionType());
            result.setDescription(quest.getDescription());
            result.setTypeLookLp(quest.getTypeLookLp());
            result.setStatusMessage("Found");
            result.setTeacherQuestionExists(Boolean.TRUE);
        }
        return result;
    }catch (Exception ex){
        logger.error(ex);
        result.setStatusCode(1000);
        result.setStatusMessage(ex.getMessage());
        result.setTeacherQuestionExists(Boolean.FALSE);
        return  result;
    }
    }
    @Override
    public boolean questionExist(String question){
        GetQuestionResult result = new GetQuestionResult();

        try {

            if(StringUtils.isBlank(question)){
                return  false;
            }

            Question filter = new Question();
            filter.setQuestion(question);

            Question user = (Question) sqlRepository.findOne(filter);

            if(user != null ){
                return  false;
            }

            return  true;

        }catch (Exception ex){
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setQuestionExists(Boolean.FALSE);
            throw  ex;
        }
    }

    @Override
    public GetAllQuestion getAllQuestion() {


        GetAllQuestion responseModel = new GetAllQuestion();
        try {

            List<Object> questionList = sqlRepository.findAll(Question.class);


            if (questionList == null) {
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No question found");
                return responseModel;
            }

            List<Map<String, Object>> selectedquestionList = new ArrayList<>();

            for (Object question : questionList) {

                Map<String, Object> questionMap = new HashMap<>();
                question = (Question) question;
                questionMap.put("questionId", ((Question) question).getQuestionId());
                questionMap.put("question", ((Question) question).getQuestion());
                questionMap.put("typeLookLp", ((Question) question).getTypeLookLp());
                questionMap.put("questionType", ((Question) question).getQuestionType());

                selectedquestionList.add(questionMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" question found");
            responseModel.setQuestionList(selectedquestionList);
            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }
    @Override
    public GetAllQuestionType getAllQuestionType() {


        GetAllQuestionType responseModel = new GetAllQuestionType();
        try {

            List<Object> questionTypeList = sqlRepository.findAll(QuestionType.class);


            if (questionTypeList == null) {
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No question found");
                return responseModel;
            }

            List<Map<String, Object>> selectedquestionTypeList = new ArrayList<>();

            for (Object questionType : questionTypeList) {

                Map<String, Object> questionMap = new HashMap<>();
                questionType = (QuestionType) questionType;
                questionMap.put("questionTypeId", ((QuestionType) questionType).getQuestionTypeId());
                questionMap.put("description", ((QuestionType) questionType).getDescription());


                selectedquestionTypeList.add(questionMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" question found");
            responseModel.setQuestionTypeList(selectedquestionTypeList);
            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }
    @Override
    public EditQuestionResponseModel editQuestion(EditQuestionRequestModel confirmEditQuestion) {
        EditQuestionResponseModel responseModel = new EditQuestionResponseModel();

        try {
            Question filterquestion = new Question();

            filterquestion.setQuestionId(confirmEditQuestion.getQuestionId());

            filterquestion = (Question) sqlRepository.findOne(filterquestion);

            if(filterquestion == null){

                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("Department not found");
                return responseModel;
            }

            filterquestion.setQuestion(confirmEditQuestion.getQuestion());
            filterquestion.setQuestionType(confirmEditQuestion.getQuestionType());
            filterquestion.setTypeLookLp(confirmEditQuestion.getTypeLookLp());
            filterquestion.setDescription(confirmEditQuestion.getDescription());

            sqlRepository.update(filterquestion);

            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully Updated department");
            responseModel.setQuestion(filterquestion.getQuestion());
            responseModel.setDescription(filterquestion.getDescription());
            responseModel.setTypeLookLp(filterquestion.getTypeLookLp());
            responseModel.setQuestionType(filterquestion.getQuestionType());
            return  responseModel;

        }catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return  responseModel;
        }
    }

}
