package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetUserByEmailResult extends AbstractResponseModel {
    private boolean userExists;

    public boolean isUserExists() {
        return userExists;
    }

    public void setUserExists(boolean userExists) {
        this.userExists = userExists;
    }
}
