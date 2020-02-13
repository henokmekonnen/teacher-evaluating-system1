package com.ddu.tes.service.answer;

import com.ddu.tes.controller.model.answer.AnswerQuestionRequestModel;
import com.ddu.tes.controller.model.answer.AnswerQuestionResponseModel;
import com.ddu.tes.data.modle.QuestionAnswer;
import com.ddu.tes.data.repository.SqlRepository;
import com.ddu.tes.service.user.GetUserByEmailResult;
import com.ddu.tes.service.user.UserService;
import com.ddu.tes.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerServiceImpl implements AnswerService {
    private static final Log logger = LogFactory.getLog(AnswerServiceImpl.class);

    @Autowired
    SqlRepository sqlRepository;

    @Autowired
    UserService userService;

    @Override
    public GetAllAnswerList getAllAnswer() {

        GetAllAnswerList responseModel = new GetAllAnswerList();
        try {

            List<Object> questionList = sqlRepository.findAll(QuestionAnswer.class);


            if (questionList == null) {
                responseModel.setStatusCode(1000);
                responseModel.setStatusMessage("No question found");
                return responseModel;
            }

            List<Map<String, Object>> selectedquestionList = new ArrayList<>();

            for (Object question : questionList) {

                Map<String, Object> questionMap = new HashMap<>();
                question = (QuestionAnswer) question;
                questionMap.put("questionAnswerId", ((QuestionAnswer) question).getQuestionAnswerId());
                questionMap.put("answer", ((QuestionAnswer) question).getAnswer());
                questionMap.put("description", ((QuestionAnswer) question).getDescription());
                questionMap.put("questionId", ((QuestionAnswer) question).getQuestionId());

                selectedquestionList.add(questionMap);
            }
            responseModel.setStatusCode(0);
            responseModel.setStatusMessage(" question found");
            responseModel.setAnswerList(selectedquestionList);
            return responseModel;

        } catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return responseModel;
        }
    }
    @Override
    public GetAnswerResult getAnswer(final Integer answer){

        GetAnswerResult result = new GetAnswerResult();

        try {

            if(answer==null){
                result.setStatusCode(1000);
                result.setStatusMessage("department name not found");
                result.setAnswerExists(Boolean.FALSE);
                return  null;
            }

            QuestionAnswer filter = new  QuestionAnswer();
            filter.setQuestionId(answer);

            QuestionAnswer quest = (QuestionAnswer) sqlRepository.findOne(filter);

            if(quest == null ){
                result.setStatusCode(1000);
                result.setStatusMessage("department not found");
                result.setAnswerExists(Boolean.FALSE);
                return  result;
            }

            result.setStatusCode(0);
            result.setQuestionAnswerId(quest.getQuestionAnswerId());
            result.setAnswer(quest.getAnswer());
            result.setDescription(quest.getDescription());
            result.setQuestionId(quest.getQuestionId());
            result.setStatusMessage("Found");
            result.setAnswerExists(Boolean.TRUE);
            return  result;

        }catch (Exception ex){
            logger.error(ex);
            result.setStatusCode(1000);
            result.setStatusMessage(ex.getMessage());
            result.setAnswerExists(Boolean.FALSE);
            return  result;
        }
    }

    @Override
    public AnswerQuestionResponseModel acceptAnswer(AnswerQuestionRequestModel confirmAcceptAnswer){
        AnswerQuestionResponseModel responseModel = new AnswerQuestionResponseModel();

        try {

            GetUserByEmailResult result = userService.getCurrentUser();

            for (String coreAnswer :confirmAcceptAnswer.getCoreCompetence()) {
                 processAnswer(confirmAcceptAnswer.getUserId(), result != null ? result.getUsrId() : null, coreAnswer);
            }

            for (String ethicalAnswer :confirmAcceptAnswer.getEthicalCompetence()) {
                 processAnswer(confirmAcceptAnswer.getUserId(), result != null ? result.getUsrId() : null, ethicalAnswer);
            }

            for (String profAnswer :confirmAcceptAnswer.getProfessionalCompetence()) {
                 processAnswer(confirmAcceptAnswer.getUserId(), result != null ? result.getUsrId() : null, profAnswer);
            }

            for (String timeAnswer :confirmAcceptAnswer.getTimeManagement()) {
                 processAnswer(confirmAcceptAnswer.getUserId(), result != null ? result.getUsrId() : null, timeAnswer);
            }


            responseModel.setStatusCode(0);
            responseModel.setStatusMessage("Successfully created question");
            return  responseModel;

        }catch (Exception ex) {
            logger.error(ex);
            responseModel.setStatusCode(1000);
            responseModel.setStatusMessage(ex.getMessage());
            return  responseModel;
        }
    }

    private void processAnswer(Integer userId, Integer answeringUserId, String answer) {

        try {
            QuestionAnswer questionAnswer = new QuestionAnswer();

            questionAnswer.setAnswerdByUserId(answeringUserId);//if student null else id
            questionAnswer.setAnswerdForUserId(userId); // teacher
            questionAnswer.setQuestionId(Integer.valueOf(answer.split("_")[0].toString()));
            questionAnswer.setAnswer(Integer.valueOf(answer.split("_")[1].toString()));

            sqlRepository.insert(questionAnswer);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

    }
}
