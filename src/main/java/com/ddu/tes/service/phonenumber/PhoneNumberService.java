package com.ddu.tes.service.phonenumber;

public interface PhoneNumberService {


    public String standardize(String phoneNumber);

    public String formatColloquial(String phoneNumber);

    /**
     * National dial format.e.g 91123... (0 or +251 discarded)
     *
     * @param phoneNumber
     * @return
     */
    public String formatColloquialDialFormat(String phoneNumber);

    public String mask(String phoneNumber);

    public boolean validatePhoneNumber(String phoneNumber);
}
