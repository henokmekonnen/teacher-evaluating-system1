package com.ddu.tes.controller.model.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class EditUserResponseModel extends AbstractResponseModel {

    private String firstName;
    private String lastName;
    private String grandFatherName;
    private String email;
    private String phoneNumber;
    private String gender;
    private String departmentName;
    private Integer departmentId;
    private String dateOfBirth;
    private Integer usrId;
private Integer usrRoleId;
    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
        this.usrId = usrId;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGrandFatherName() {
        return grandFatherName;
    }

    public void setGrandFatherName(String grandFatherName) {
        this.grandFatherName = grandFatherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getUsrRoleId() {
        return usrRoleId;
    }

    public void setUsrRoleId(Integer usrRoleId) {
        this.usrRoleId = usrRoleId;
    }
    private String usrRoleName;
    private Integer usrUserRole;

    public Integer getUsrUserRole() {
        return usrUserRole;
    }

    public void setUsrUserRole(Integer usrUserRole) {
        this.usrUserRole = usrUserRole;
    }

    public String getUsrRoleName() {
        return usrRoleName;
    }

    public void setUsrRoleName(String usrRoleName) {
        this.usrRoleName = usrRoleName;
    }
}
