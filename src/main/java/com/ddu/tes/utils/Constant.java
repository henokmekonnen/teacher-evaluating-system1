package com.ddu.tes.utils;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @author GHabtamu
 */
public class Constant {
    public static final String INVALID_DATA_TYPE_PROVIDED_FOR_DATA_CONTEXT = "Invalid data type provided for DataContext";
    public static final String INVALID_ID_TYPE_PROVIDED_FOR_DATA_CONTEXT = "Invalid Id type provided for DataContext";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_UPDATE_OPERATION = "invalid object provided for sql-DataContext , update operation ";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_UPDATE_OPERATION_ENTITY = "invalid object provided for sql-DataContext , update operation , Entity";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_DELETE_OPERATION = "invalid object provided for sql-DataContext , delete operation ";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_DELETE_OPERATION_ENTITY = "invalid object provided for sql-DataContext , delete operation , Entity";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_INSERT_OPERATION = "invalid object provided for sql-DataContext , insert operation ";
    public static final String INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_BULK_INSERT_OPERATION = "invalid object provided for sql-DataContext , bulkInsert operation ";
    public static final String DEADLOCK_EXCEPTION_CAUGHT_EXECUTING_STATEMENT = "Deadlock exception caught executing statement: ";
    public static final String ERROR_SQLDAOMAPPER = "error.sqldaomapper";
    public static final String Back_To_Create="createDepartment";
    public static final String TEACHER_ROLE="Teacher";
    public static final String STUDENT_ROLE="STUDENT";
    public static final String CHAIRED_ROLE="head_of_Department";
    public static final String DEAN_ROLE="Dean_of_Department";
    public static final String ADMIN_ROLE="Admin";

    public static final String EMAIL_ADDRESS_VALIDATOR_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static final String CUSTOMER_NAME_VALIDATOR_REGEX = "(?i)[a-z]([- /',.a-z]{0,23}[a-z])?$";
    public static final String WEBSITE_VALIDATOR_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    // for errror pages
    public static final String UserRole="Teacher";
    public static final String ALERT_TYPE_SUCCESS = "success";
    public static final String TYPE = "type";
    public static final String MESSAGE = "message";
    public static final String MESSAGE_LIST = "messageList";
    public static final String ALERT_TYPE_DANGER = "danger";


   static String password = "password";
   static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    static  String hashedPassword =passwordEncoder.encode(password);

    public static final String SYSTEM = "system";
    public static final int Department_system=1;
    public static final String USER_SYSTEM="user";

    public static final String PASSWORD_SYSTEM=hashedPassword;
    public static String generatenumber(){
        UUID uuid = UUID.randomUUID();
        String result=String.valueOf(uuid);
        return result;
    }
}
