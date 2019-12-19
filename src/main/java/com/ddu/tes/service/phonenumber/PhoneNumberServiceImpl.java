package com.ddu.tes.service.phonenumber;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService, InitializingBean {


    private static final String STANDARDIZED_PREFIX = "+";

    private static final int STANDARDIZED_PREFIX_LENGTH = STANDARDIZED_PREFIX.length();

    private static final String COUNTRY_CODE_PREFIX = "251";

    private static final int COUNTRY_CODE_PREFIX_LENGTH = COUNTRY_CODE_PREFIX.length();

    private static final String COLLOQUIAL_PREFIX = "0";
    private static final String COLLOQUIAL_DIAL_PREFIX = "9";
    private static final int COLLOQUIAL_PREFIX_LENGTH = COLLOQUIAL_PREFIX.length();
    private static final String phoneNumberPatternRegex = "^((?:\\+2519)[0-9]{8}|(?:2519)[0-9]{8}|(?:09)[0-9]{8})$";

    private Pattern phoneNumberPattern;

    @Override
    public void afterPropertiesSet() throws Exception {

        if (phoneNumberPattern == null) {
            phoneNumberPattern = Pattern.compile(phoneNumberPatternRegex);

        }

    }

    @Override
    public String standardize(String phoneNumber) {

        if (phoneNumber == null) {
            return null;
        }

        phoneNumber = phoneNumber.trim();

        if (phoneNumber.startsWith(STANDARDIZED_PREFIX)) {    //number already standardized

            return phoneNumber;

        }

        if (phoneNumber.length() == 9 && phoneNumber.startsWith("9")) {    //number in colloquial format eg. 09XXXXXXXX

            return STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX + phoneNumber;
        }


        if (phoneNumber.length() == 10) {    //number in colloquial format eg. 09XXXXXXXX

            return STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX + phoneNumber.substring(1);
        }

        if (phoneNumber.length() == 12) {    //number in full form without internationalizing prefix eg. 2519XXXXXXXX

            return STANDARDIZED_PREFIX + phoneNumber;
        }

        return phoneNumber;
    }

    @Override
    public String formatColloquial(String phoneNumber) {

        if (phoneNumber == null) {
            return null;
        }

        phoneNumber = phoneNumber.trim();

        if (phoneNumber.startsWith(COLLOQUIAL_PREFIX)) {  //number already in colloquial format eg. 09XXXXXXXX

            return phoneNumber;

        }

        if (phoneNumber.startsWith(STANDARDIZED_PREFIX)) { //number in standardized format eg. +2519XXXXXXXX

            return COLLOQUIAL_PREFIX + phoneNumber.substring(STANDARDIZED_PREFIX_LENGTH + COUNTRY_CODE_PREFIX_LENGTH);

        }

        if (phoneNumber.length() == 12) { //number in full form without internationalizing prefix eg. 2519XXXXXXXX

            return COLLOQUIAL_PREFIX + phoneNumber.substring(COUNTRY_CODE_PREFIX_LENGTH);
        }

        if (!phoneNumber.startsWith(COUNTRY_CODE_PREFIX) & !phoneNumber.startsWith(STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX) & !phoneNumber.startsWith(COLLOQUIAL_PREFIX)) {

            if (phoneNumber.length() == 9) {    //number in colloquial format eg. 9XXXXXXXX

                phoneNumber = STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX + phoneNumber;
            }
        }

        return phoneNumber;
    }

    @Override
    public String formatColloquialDialFormat(String phoneNumber) {

        if (phoneNumber == null) {
            return null;
        }

        phoneNumber = phoneNumber.trim();

        if (phoneNumber.startsWith(COLLOQUIAL_DIAL_PREFIX)) {  //number already in national dial format eg. 9XXXXXXXX

            return phoneNumber;

        }

        if (phoneNumber.startsWith(COLLOQUIAL_PREFIX)) {  //number already in colloquial format eg. 09XXXXXXXX

            return phoneNumber.substring(COLLOQUIAL_PREFIX_LENGTH);

        }

        if (phoneNumber.startsWith(STANDARDIZED_PREFIX)) { //number in standardized format eg. +2519XXXXXXXX

            return phoneNumber.substring(STANDARDIZED_PREFIX_LENGTH + COUNTRY_CODE_PREFIX_LENGTH);

        }

        if (phoneNumber.length() == 12) { //number in full form without internationalizing prefix eg. 2519XXXXXXXX

            return phoneNumber.substring(COUNTRY_CODE_PREFIX_LENGTH);
        }

        return phoneNumber;
    }

    @Override
    /**
     * Mask implemented to return first four and last 2 digits of phone number
     */
    public String mask(String phoneNumber) {

        if (phoneNumber == null) {
            return null;
        }

        //colloquialize
        phoneNumber = formatColloquial(phoneNumber);

        if (phoneNumber.length() <= 6) {

            return phoneNumber;
        }


        StringBuilder sb = new StringBuilder(10);

        if (phoneNumber.length() == 10) {    //number in ethiopian colloquial format eg. 09XXXXXXXX

            //format as 091-1XX-XX65
            sb.append(StringUtils.left(phoneNumber, 3)).append('-').append(phoneNumber.substring(3, 4)).append("XX-XX").append(StringUtils.right(phoneNumber, 2));

        } else {

            //format to show first four and last two with no dashes
            sb.append(StringUtils.left(phoneNumber, 4)).append(StringUtils.repeat("X", phoneNumber.length() - 6)).append(StringUtils.right(phoneNumber, 2));
        }

        return sb.toString();
    }

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {

        if (StringUtils.isBlank(phoneNumber)) {
            return false;
        }

        if (!phoneNumber.startsWith(COUNTRY_CODE_PREFIX) & !phoneNumber.startsWith(STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX) & !phoneNumber.startsWith(COLLOQUIAL_PREFIX)) {

            if (phoneNumber.length() == 9) {    //number in colloquial format eg. 9XXXXXXXX

                phoneNumber = STANDARDIZED_PREFIX + COUNTRY_CODE_PREFIX + phoneNumber;
            }
        }

        Matcher matcher = phoneNumberPattern.matcher(phoneNumber);

        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }

    }
}
