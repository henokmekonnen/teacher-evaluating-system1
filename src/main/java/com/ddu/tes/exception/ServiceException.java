package com.ddu.tes.exception;


/**
 * @Author abraham 14/02/2018
 */
public interface ServiceException {

    public ExceptionCode getCode();

    public Class<Object> getThrower();

    public ExceptionMessage getExceptionMessage();
}
