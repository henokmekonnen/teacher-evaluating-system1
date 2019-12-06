package com.ddu.tes.service.user;

import com.ddu.tes.controller.model.AbstractResponseModel;

public class GetUserByPhoneResult extends AbstractResponseModel {
    private boolean userPhoneExists;

    public boolean isUserPhoneExists() {
        return userPhoneExists;
    }

    public void setUserPhoneExists(boolean userPhoneExists) {
        this.userPhoneExists = userPhoneExists;
    }
}
