package com.ddu.tes.utils;

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

    // for errror pages
    public static final String ALERT_TYPE_SUCCESS = "success";
    public static final String TYPE = "type";
    public static final String MESSAGE = "message";
    public static final String MESSAGE_LIST = "messageList";
    public static final String ALERT_TYPE_DANGER = "danger";
    public static final String SYSTEM = "system";
    public static final int Department_system=1;
    public static final String USER_SYSTEM="user";
    public static final String PASSWORD_SYSTEM="password";
    public static String generatenumber(){
        UUID uuid = UUID.randomUUID();
        String result=String.valueOf(uuid);
        return result;
    }
}
