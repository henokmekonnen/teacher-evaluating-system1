package com.ddu.tes.controller.model.answer;

public class AnswerQuestionRequestModel {

    private String[]  ethicalCompetence;
    private String[]  professionalCompetence;
    private String[]  timeManagement;
    private String[]  coreCompetence;
    private Integer departmentId;
    private Integer userId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String[] getEthicalCompetence() {
        return ethicalCompetence;
    }

    public void setEthicalCompetence(String[] ethicalCompetence) {
        this.ethicalCompetence = ethicalCompetence;
    }

    public String[] getProfessionalCompetence() {
        return professionalCompetence;
    }

    public void setProfessionalCompetence(String[] professionalCompetence) {
        this.professionalCompetence = professionalCompetence;
    }

    public String[] getTimeManagement() {
        return timeManagement;
    }

    public void setTimeManagement(String[] timeManagement) {
        this.timeManagement = timeManagement;
    }

    public String[] getCoreCompetence() {
        return coreCompetence;
    }

    public void setCoreCompetence(String[] coreCompetence) {
        this.coreCompetence = coreCompetence;
    }
}
