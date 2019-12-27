package com.ddu.tes.service.role;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllRoleList extends AbstractResponseModel {

    public List<Map<String, Object>> roleList;

    public List<Map<String, Object>> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Map<String, Object>> roleList) {
        this.roleList = roleList;
    }
}
