package com.ddu.tes.service.question;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetAllTeacherQuestion extends AbstractResponseModel {
    private boolean teacherQuestionExists;
    private Integer  questionId;
    private String  question;
    private String description;
    private String typeLookLp;
    private String  questionType;

    public boolean isTeacherQuestionExists() {
        return teacherQuestionExists;
    }

    public void setTeacherQuestionExists(boolean teacherQuestionExists) {
        this.teacherQuestionExists = teacherQuestionExists;
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

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
