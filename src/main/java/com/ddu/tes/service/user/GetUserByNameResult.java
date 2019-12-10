package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetUserByNameResult extends AbstractResponseModel {
    private Integer userId;
    private String uuid;
    private String usrFirstName;
    private String usrLastName;
    private String usrGrandFatherName;
    private String usrEmail;
    private String usrPhoneNumber;
    private String usrGender;
    private String usrDepartmentName;
    private Integer usrDepartmentId;
    private String usrDateOfBirth;
    private String usrUserName;
    private String usrPassword;
    private boolean userNameExists;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getUsrGrandFatherName() {
        return usrGrandFatherName;
    }

    public void setUsrGrandFatherName(String usrGrandFatherName) {
        this.usrGrandFatherName = usrGrandFatherName;
    }

    public String getUsrEmail() {
        return usrEmail;
    }

    public void setUsrEmail(String usrEmail) {
        this.usrEmail = usrEmail;
    }

    public String getUsrPhoneNumber() {
        return usrPhoneNumber;
    }

    public void setUsrPhoneNumber(String usrPhoneNumber) {
        this.usrPhoneNumber = usrPhoneNumber;
    }

    public String getUsrGender() {
        return usrGender;
    }

    public void setUsrGender(String usrGender) {
        this.usrGender = usrGender;
    }

    public String getUsrDepartmentName() {
        return usrDepartmentName;
    }

    public void setUsrDepartmentName(String usrDepartmentName) {
        this.usrDepartmentName = usrDepartmentName;
    }

    public Integer getUsrDepartmentId() {
        return usrDepartmentId;
    }

    public void setUsrDepartmentId(Integer usrDepartmentId) {
        this.usrDepartmentId = usrDepartmentId;
    }

    public String getUsrDateOfBirth() {
        return usrDateOfBirth;
    }

    public void setUsrDateOfBirth(String usrDateOfBirth) {
        this.usrDateOfBirth = usrDateOfBirth;
    }

    public String getUsrUserName() {
        return usrUserName;
    }

    public void setUsrUserName(String usrUserName) {
        this.usrUserName = usrUserName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public boolean isUserNameExists() {
        return userNameExists;
    }


    public void setUserNameExists(boolean userIdExists) {
        this.userNameExists = userNameExists;
    }


}
