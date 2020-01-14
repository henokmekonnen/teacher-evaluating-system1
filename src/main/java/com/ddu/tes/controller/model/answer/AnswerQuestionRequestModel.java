package com.ddu.tes.controller.model.answer;

public class AnswerQuestionRequestModel {
    private Integer[] mulAnswer;
    private Integer answer;
    private String description;
    private Integer questionId;

    public Integer[] getMulAnswer() {
        return mulAnswer;
    }

    public void setMulAnswer(Integer[] mulAnswer) {
        this.mulAnswer = mulAnswer;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
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
