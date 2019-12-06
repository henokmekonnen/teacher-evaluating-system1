package com.ddu.tes.controller.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author GHabtamu
 */
@Getter
@Setter
public class AbstractResponseModel {
    private int statusCode;
    private String statusMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


}
