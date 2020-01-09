package com.ddu.tes.controller.model.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class EditQuestionResponseModel extends AbstractResponseModel {
    private Integer  questionId;
    private  String  question;
    private String description;
    private String typeLookLp;
    private String  questionType;


    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeLookLp() {
        return typeLookLp;
    }

    public void setTypeLookLp(String typeLookLp) {
        this.typeLookLp = typeLookLp;
    }
}
