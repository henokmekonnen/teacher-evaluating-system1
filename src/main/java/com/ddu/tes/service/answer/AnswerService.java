package com.ddu.tes.service.answer;

import com.ddu.tes.controller.model.answer.AnswerQuestionRequestModel;
import com.ddu.tes.controller.model.answer.AnswerQuestionResponseModel;

public interface AnswerService {
    public GetAllAnswerList getAllAnswer();
    public GetAnswerResult getAnswer (final Integer answer);
    public AnswerQuestionResponseModel acceptAnswer(AnswerQuestionRequestModel confirmAcceptAnswer);


}
