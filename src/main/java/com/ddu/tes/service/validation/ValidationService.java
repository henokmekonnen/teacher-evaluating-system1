package com.ddu.tes.service.validation;

public interface ValidationService {

    public boolean isEmailValid(String emailAddress);

    public boolean isValidName(String name);

    public boolean isValidWebSite(String site);

    public boolean isPasswordValid(final String password);
}
