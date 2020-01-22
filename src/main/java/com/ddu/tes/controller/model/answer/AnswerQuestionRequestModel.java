package com.ddu.tes.controller.model.answer;

public class AnswerQuestionRequestModel {
      private Integer answer;
    private String description;
    private Integer questionId;
    private Integer[]  ethicalCompetence;
    private Integer[]  professionalCompetence;
    private Integer[]  timeManagement;
    private Integer[]  coreCompetence;

    public Integer[] getCoreCompetence() {

        return coreCompetence;
    }

    public void setCoreCompetence(Integer[] coreCompetence) {
        this.coreCompetence = coreCompetence;
    }

    public Integer[] getEthicalCompetence() {
        return ethicalCompetence;
    }

    public void setEthicalCompetence(Integer[] ethicalCompetence) {
        this.ethicalCompetence = ethicalCompetence;
    }

    public Integer[] getProfessionalCompetence() {
        return professionalCompetence;
    }

    public void setProfessionalCompetence(Integer[] professionalCompetence) {
        this.professionalCompetence = professionalCompetence;
    }

    public Integer[] getTimeManagement() {
        return timeManagement;
    }

    public void setTimeManagement(Integer[] timeManagement) {
        this.timeManagement = timeManagement;
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
