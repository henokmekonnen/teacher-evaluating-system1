package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;

public interface QuestionServices {
    public boolean questionExist(String question);
    public GetQuestionResult getQuestion (final String question);
    public CreateQuestionResponseModel createQuestion(CreateQuestionRequestModel confirmCreateQuestion);
}
