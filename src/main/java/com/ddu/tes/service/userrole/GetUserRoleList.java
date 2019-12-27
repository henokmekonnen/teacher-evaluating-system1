package com.ddu.tes.service.userrole;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetUserRoleList extends AbstractResponseModel {

    public List<Map<String, Object>> userRoleList;

    public List<Map<String, Object>> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<Map<String, Object>> userRoleList) {
        this.userRoleList = userRoleList;
    }
}
