package com.ddu.tes.controller;

public class ChangePasswordResponse {

    private Boolean hasError = false;
    private String description;

    public String getDescription() {
        return description;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
