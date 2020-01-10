package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.question.CreateQuestionRequestModel;
import com.ddu.tes.controller.model.question.CreateQuestionResponseModel;
import com.ddu.tes.controller.model.question.EditQuestionRequestModel;
import com.ddu.tes.controller.model.question.EditQuestionResponseModel;

public interface QuestionServices {
    public boolean questionExist(String question);
    public GetQuestionResult getQuestion (final Integer question);
    public GetAllTeacherQuestion getTeacherQuestion (final String question);
    public CreateQuestionResponseModel createQuestion(CreateQuestionRequestModel confirmCreateQuestion);
    public GetAllQuestion getAllQuestion();
    public GetAllQuestionType getAllQuestionType();
    public EditQuestionResponseModel editQuestion(EditQuestionRequestModel confirmEditQuestion);

}
