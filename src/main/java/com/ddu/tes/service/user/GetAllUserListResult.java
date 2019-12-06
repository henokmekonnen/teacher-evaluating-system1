package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

import java.util.List;
import java.util.Map;

public class GetAllUserListResult extends AbstractResponseModel {

    public List<Map<String, Object>> userList;

    public List<Map<String, Object>> getUserList() {
        return userList;
    }

    public void setUserList(List<Map<String, Object>> userList) {
        this.userList = userList;
    }
}
