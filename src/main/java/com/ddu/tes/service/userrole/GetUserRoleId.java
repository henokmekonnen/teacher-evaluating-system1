package com.ddu.tes.service.userrole;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetUserRoleId extends AbstractResponseModel {
    private Integer userRole;
    private String description;
    private Integer roleId;
    private Integer userId;
    private boolean userRoleExists;

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isUserRoleExists() {
        return userRoleExists;
    }

    public void setUserRoleExists(boolean userRoleExists) {
        this.userRoleExists = userRoleExists;
    }
}
