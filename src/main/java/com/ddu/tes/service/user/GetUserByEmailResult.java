package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetUserByEmailResult extends AbstractResponseModel {
    private boolean userExists;
    private String usrUuid;
    private String usrFirstName;
    private String usrLastName;
    private String usrGrandFatherName;
    private String usrEmail;
    private String usrPhoneNumber;
    private String usrGender;
    private String usrDepartmentName;
    private Integer usrDepartmentId;
    private String usrDateOfBirth;
    private Integer usrId;


    public boolean isUserExists() {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }

    public String getUsrUuid() {
        return usrUuid;
    }

    public void setUsrUuid(String usrUuid) {
        this.usrUuid = usrUuid;
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

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }
}
