package com.ddu.tes.exception;

import java.io.Serializable;

/**
 * @Author abraham 14/02/2018
 */
public enum  ExceptionCode implements Serializable {

    INTERNAL_ERROR,
    CONFIGURATION_ERROR,
    ETL_ERROR,
    OPERATOR_ERROR,
    DATA_CONTEXT_INVALID_CLASS_TYPE,
    PERSISTENCE_ERROR,
    PERSISTENCE_DEADLOCK_ERROR,
    REPORTING_ERROR,
    SECURITY_ACCESS_ERROR,
    WEB_SERVICE_ERROR,
    WORKFLOW_ERROR,
    EVALUATION_ERROR,
    UI_ERROR,
    MAIL_ERROR,
    CHARTING_ERROR,
    REMOTING_ERROR;
}
