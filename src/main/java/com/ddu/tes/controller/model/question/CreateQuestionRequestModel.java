package com.ddu.tes.controller.model.question;

public class CreateQuestionRequestModel {
    private String  question;
    private String description;
    private String typeLookLp;
private String descName;
    private String  questionType;
    private String qstName;

    public String getQstName() {
        return qstName;
    }

    public void setQstName(String qstName) {
        this.qstName = qstName;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
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

    public String getDescName() {
        return descName;
    }

    public void setDescName(String descName) {
        this.descName = descName;
    }
}
