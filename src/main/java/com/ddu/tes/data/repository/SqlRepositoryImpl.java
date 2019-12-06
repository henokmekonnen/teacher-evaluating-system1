package com.ddu.tes.data.repository;

import com.ddu.tes.data.modle.BaseModel;
import com.ddu.tes.exception.ExceptionCode;
import com.ddu.tes.exception.ExceptionMessage;
import com.ddu.tes.exception.FatalException;
import com.ddu.tes.utils.Constant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.*;

/**
 * @Author abraham 12/02/2018
 */

@Service
public class SqlRepositoryImpl implements SqlRepository, InitializingBean {

    public static final Log logger = LogFactory.getLog(SqlRepositoryImpl.class);
    public static final String REPOSITORY_NAME_PREFIX = "Repository";

    @Autowired
    private Map<String, JpaRepository> repository;

    @Autowired
    protected DataSource dataSource;

    private SQLErrorCodeSQLExceptionTranslator sqlErrorTranslator;
    @Override
    public void afterPropertiesSet() throws Exception {

        Assert.notEmpty(repository, "repository can't be null");
        Assert.notNull(dataSource, "dataSource can't be null");
        sqlErrorTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
    }

    private JpaRepository getRepository(final Class tClass) {
        return  repository.get(Character.toLowerCase(tClass.getSimpleName().charAt(0)) + tClass.getSimpleName().substring(1) + REPOSITORY_NAME_PREFIX);
    }

    @Override
    public List<Object> find(Object filter) {

        final JpaRepository repositoryManager = getRepository(filter.getClass());

        final Iterable resultList = repositoryManager.findAll(Example.of(filter));

        return resultList == null ? null : (List<Object>) resultList;
    }

    @Override
    public Object findOne(Object filter) {

        final List<Object> findResult = find(filter);

        return null != findResult && findResult.size() != 0 ? findResult.get(0) : null;
    }

    @Override
    public List<Object> findAll(Class<?> clazz) {

        final JpaRepository repositoryManager = getRepository(clazz);

        final Iterable<Object> results = repositoryManager.findAll();

        return results != null ? (List<Object>) results : null;
    }

    @Override
    public Optional get(Class<?> clazz, Integer id, Locale locale) {

        if (clazz == null) throw new RuntimeException(Constant.INVALID_DATA_TYPE_PROVIDED_FOR_DATA_CONTEXT);

        if (id == null) throw new RuntimeException(Constant.INVALID_ID_TYPE_PROVIDED_FOR_DATA_CONTEXT);

        JpaRepository  repositoryManager = getRepository(clazz);

        return (Optional) repositoryManager.getOne(id);
    }

    @Override
    public long update(Object object) {

        if(object == null) throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_UPDATE_OPERATION);

        if(!BaseModel.class.isAssignableFrom(object.getClass()))
            throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_UPDATE_OPERATION_ENTITY);

        final JpaRepository repositoryManager = getRepository(object.getClass());

        try {

            final Object result = repositoryManager.save(object);

            return ((BaseModel) result).getId();

        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public long delete(Object object) {

        if(object == null) {
            throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_DELETE_OPERATION);
        }

        if(!BaseModel.class.isAssignableFrom(object.getClass()))
            throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_DELETE_OPERATION_ENTITY);

        final JpaRepository repositoryManager = getRepository(object.getClass());

        try {

            repositoryManager.delete(object);

            return 1;

        } catch (Exception ex) {

            logger.error(ex.getMessage(), ex);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Object insert(Object object) {

        if(object == null) {
            throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_INSERT_OPERATION);
        }

        final JpaRepository repositoryManager = getRepository(object.getClass());

        final Object result =  repositoryManager.save(object);

        final BaseModel savedObj = (BaseModel) result;

        return savedObj;
    }

    @Override
    @Transactional
    public List<Object> bulkInsert(List<Object> object) {

        if(object == null || object.isEmpty()) {
            throw new RuntimeException(Constant.INVALID_OBJECT_PROVIDED_FOR_SQL_DATA_CONTEXT_BULK_INSERT_OPERATION);
        }

        final JpaRepository repositoryManager = getRepository(object.get(0).getClass());

        return  (List<Object>) repositoryManager.save(object);
    }


    @Override
    public List<Object> getList(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            List<Object> list;
            try (ResultSet rs = statement.executeQuery()) {

                if (rs.getMetaData().getColumnCount() < 1) {

                    return new ArrayList<>();
                }

                list = new ArrayList<>();
 
                while (rs.next()) {

                    list.add(rs.getObject(1));
                } 
            }

            return list;

        } catch(SQLException se) {

            throw handleSQLException( se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    @Override
    public Map<Object,Object> getMap(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            Map<Object, Object> map;
            try (ResultSet rs = statement.executeQuery()) {

                if (rs.getMetaData().getColumnCount() < 2) {

                    return new HashMap<>();
                }

                map = new LinkedHashMap<>();

                while (rs.next()) {

                    map.put(rs.getObject(1), rs.getObject(2));
                }
            }

            return map;

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                logger.error(e.getMessage(),e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Collection<Map<String,Object>> getMapCollection(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                return buildMapList(rs);
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Map<String,Object>> getMapList(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            ResultSet rs = statement.executeQuery();

            return buildMapList(rs);

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Collection<Map<String,Object>>> getMapCollectionList(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            List<Collection<Map<String,Object>>> resultSet = new ArrayList<>();

            if(statement.execute()) {

                do{

                    rs = statement.getResultSet();

                    resultSet.add(buildMapList(rs));

                }while(statement.getMoreResults());

            }

            return resultSet;

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public int executeStatement(String query, Object[] parameters){

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            return statement.executeUpdate();

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    private List<Map<String, Object>> buildMapList(ResultSet rs) throws SQLException {

        List<Map<String, Object>> list = new ArrayList<>();

        Map<String, Object> row;

        if(rs != null) {

            ResultSetMetaData meta = rs.getMetaData();

            while (rs.next()) {

                row = new LinkedHashMap<>();  //Must be HashMap or similar to allow null entries

                for (int i = 1; i <= meta.getColumnCount(); ++i) {

                    row.put(meta.getColumnLabel(i), rs.getObject(i));
                }

                list.add(row);
            }
        }

        return list;
    }

    @Override
    public Double getDouble(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getDouble(1);

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Integer getInteger(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getInt(1);

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Long getLong(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getLong(1);

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    @Override
    public Date getDate(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getDate(1);

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Boolean getBoolean(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getBoolean(1);

                } else {

                    return false;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public byte[] getBlob(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    try {
                        // Get as a BLOB
                        Blob aBlob = rs.getBlob(1);
                        return aBlob.getBytes(1, (int) aBlob.length());

                    } catch (Exception ex) {

                        // The driver could not handle this as a BLOB...
                        // Fallback to default (and slower) byte[] handling
                        return rs.getBytes(1);
                    }

                } else {

                    return new byte[]{};
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public String getString(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    return rs.getString(1);

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) { 
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public String getXML(String query, Object[] parameters) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = DataSourceUtils.getConnection(dataSource);
            statement = connection.prepareStatement(query);

            setStatementParameters(statement, parameters);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {

                    SQLXML xml = rs.getSQLXML(1);

                    return xml == null ? null : xml.getString();

                } else {

                    return null;
                }
            }

        } catch(SQLException se) {

            throw handleSQLException(se, query, parameters);

        } finally {

            try{ if (statement != null) statement.close(); }
            catch(SQLException e) {
                
                logger.error(e.getMessage(), e);
            }

            DataSourceUtils.releaseConnection(connection, dataSource);
        }
    }


    private void setStatementParameters(PreparedStatement statement, Object[] parameters) throws SQLException {

        if(parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                if(parameters[i] == null){
                    statement.setObject(i+1, null, Types.JAVA_OBJECT);
                } else if(parameters[i] instanceof Date) {
                    statement.setTimestamp(i+1, new Timestamp(((Date)parameters[i]).getTime()));
                } else {
                    statement.setObject(i+1, parameters[i]);
                }
            }
        }
    }
  
    private RuntimeException handleSQLException(SQLException se, String statement, Object[] parameters) {

        DataAccessException dae = sqlErrorTranslator.translate("SQLDAO operation", null, se);

        if(dae instanceof DeadlockLoserDataAccessException) {

            logger.debug(Constant.DEADLOCK_EXCEPTION_CAUGHT_EXECUTING_STATEMENT + statement + "[" + Arrays.toString(parameters) + "]", se);

            return new FatalException(new ExceptionMessage(Constant.ERROR_SQLDAOMAPPER, se), this, ExceptionCode.PERSISTENCE_DEADLOCK_ERROR);
        }

        return new FatalException(new ExceptionMessage(Constant.ERROR_SQLDAOMAPPER, se), this, ExceptionCode.PERSISTENCE_ERROR);
    }


}
