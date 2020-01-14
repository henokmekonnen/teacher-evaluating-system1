package com.ddu.tes.service.answer;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetAnswerResult extends AbstractResponseModel {
    private boolean answerExists;
    private Integer answer;
    private Integer questionAnswerId;
    private String description;
    private Integer questionId;




    public boolean isAnswerExists() {
        return answerExists;
    }

    public void setAnswerExists(boolean answerExists) {
        this.answerExists = answerExists;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }
}
