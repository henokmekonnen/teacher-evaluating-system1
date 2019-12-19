package com.ddu.tes.service.validation;

import com.ddu.tes.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService, InitializingBean {

    private Pattern emailPatternMatcher;
    private Pattern validateNameMatcher;
    private Pattern websitePatternMatcher;
    private static final Integer PASSWORD_STRENGTH_POLICY=8;


    private static final String DEFAULT_PASSWORD_VALIDATE_LOWERCASE = "(?=.*[a-z]).*";  //lowercase character
    private static final String DEFAULT_PASSWORD_VALIDATE_UPPERCASE = "(?=.*[A-Z]).*";  //uppercase character
    private static final String DEFAULT_PASSWORD_VALIDATE_DIGIT = "(?=.*\\d).*";        //digits
    private static final String DEFAULT_PASSWORD_VALIDATE_SPECIAL_CHARACTER = "(?=.*\\W+).*";  //special characters

    private List<String> passwordValidationExpressions;

    @Override
    public void afterPropertiesSet() throws Exception {

        emailPatternMatcher = Pattern.compile(Constant.EMAIL_ADDRESS_VALIDATOR_REGEX);
        validateNameMatcher = Pattern.compile(Constant.CUSTOMER_NAME_VALIDATOR_REGEX);
        websitePatternMatcher = Pattern.compile(Constant.WEBSITE_VALIDATOR_REGEX);
    }

    @Override
    public boolean isEmailValid(String emailAddress) {

        if (StringUtils.isBlank(emailAddress)) {
            return false;
        }

        Matcher matcher = null;

        try {

            matcher = emailPatternMatcher.matcher(emailAddress);

            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            //TODO THROW EXCEPTION
            throw ex;
        }
    }

    @Override
    public boolean isValidName(String name) {

        Matcher matcher = validateNameMatcher.matcher(name);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isValidWebSite(String site) {
        if (StringUtils.isBlank(site)) {
            return false;
        }

        Matcher matcher = null;

        try {

            matcher = websitePatternMatcher.matcher(site);

            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            //TODO THROW EXCEPTION
            throw ex;
        }
    }

    @Override
    public boolean isPasswordValid(String password) {

        if (org.apache.commons.lang3.StringUtils.isBlank(password)){
            return false;
        }

        if(password.length() < PASSWORD_STRENGTH_POLICY) {

            return false;
        }

        for (String validationRegEx : getPasswordValidationExpressions()) {

            if (!password.matches(validationRegEx)) {

                return false;
            }
        }

        return true;

    }



    public List<String> getPasswordValidationExpressions() {
        if (passwordValidationExpressions == null) {

            passwordValidationExpressions = new ArrayList<String>();
            passwordValidationExpressions.add(DEFAULT_PASSWORD_VALIDATE_LOWERCASE);
            passwordValidationExpressions.add(DEFAULT_PASSWORD_VALIDATE_UPPERCASE);
            passwordValidationExpressions.add(DEFAULT_PASSWORD_VALIDATE_SPECIAL_CHARACTER);
            passwordValidationExpressions.add(DEFAULT_PASSWORD_VALIDATE_DIGIT);


        }

        return passwordValidationExpressions;
    }

}
