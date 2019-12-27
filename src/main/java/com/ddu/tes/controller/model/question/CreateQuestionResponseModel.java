package com.ddu.tes.controller.model.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class CreateQuestionResponseModel extends AbstractResponseModel {
    private String question;
    private Integer typeLookLp;
    private Boolean allowsMultipleAnswer;
    private String createdBy;
    private String description;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getTypeLookLp() {
        return typeLookLp;
    }

    public void setTypeLookLp(Integer typeLookLp) {
        this.typeLookLp = typeLookLp;
    }

    public Boolean getAllowsMultipleAnswer() {
        return allowsMultipleAnswer;
    }

    public void setAllowsMultipleAnswer(Boolean allowsMultipleAnswer) {
        this.allowsMultipleAnswer = allowsMultipleAnswer;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
