package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;
import com.ddu.tes.data.modle.Question;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionServicesImpl implements QuestionServices {
    private static final Log logger = LogFactory.getLog(QuestionServicesImpl.class);
    @Autowired
    SqlRepository sqlRepository;

    @Override
    public CreateQuestionResponseModel createQuestion(CreateQuestionRequestModel confirmCreateQuestion){
        CreateQuestionResponseModel responseModel = new CreateQuestionResponseModel();
        try {
            Question newQuestion = new Question();
            newQuestion.setQuestion(confirmCreateQuestion.getQuestion());
            newQuestion.setDescription(confirmCreateQuestion.getDescription());
            newQuestion.setCreatedBy(Constant.SYSTEM);
            newQuestion.setAllowsMultipleAnswer(Boolean.FALSE);

           newQuestion= (Question)sqlRepository.insert(newQuestion);

            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully created user");
            responseModel.setQuestion(confirmCreateQuestion.getQuestion());
            responseModel.setDescription(confirmCreateQuestion.getDescription());

            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }

    @Override
    public GetQuestionResult getQuestion (final String question) {

        GetQuestionResult result = new GetQuestionResult();

        try {

            if(StringUtils.isBlank(question)){
                result.setStatusCode(1000);
                result.setStatusMessage("department name not found");
                result.setQuestionExists(Boolean.FALSE);
                return  null;
            }

            Question filter = new Question();
            filter.setQuestion(question);

            Question quest = (Question) sqlRepository.findOne(filter);

            if(quest == null ){
                result.setStatusCode(1000);
                result.setStatusMessage("department not found");
                result.setQuestionExists(Boolean.FALSE);
                return  result;
            }

            result.setStatusCode(0);

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
}
