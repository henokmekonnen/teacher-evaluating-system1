package com.ddu.tes.data.repository;

import java.util.*;

/**
 * @Author abraham 12/02/2018
 */

public interface SqlRepository {

    /**
     * Find one or more domain objects using the provided object as a filter
     * @param filter A filter to find domain objects, the non-filtering properties should be null
     * @return
     */
    public List<Object> find(Object filter);


    /**
     *
     * @param clazz
     * @return
     */
    public List<Object> findAll(Class<?> clazz);

    /**
     * Find only one domain objects using the provided object as a filter, or null if none match
     * @param filter A filter to find domain objects, the non-filtering properties should be null
     * @return
     */
    public Object findOne(Object filter);

    /**
     * Select a single domain object with the provided if
     * @param  id of the object to find
     * @return
     */
    public Object get(Class<?> clazz, Integer id, Locale locale);

    /**
     * Insert a new domain object
     * @param object
     * @return returns the id of an object which is updated.
     */
    public Object update(Object object);

    /**
     * Delete an existing domain object
     * @param object
     * @return true if the object is deleted successfully
     */
    public long delete(Object object);

    /**
     * Insert a new domain object
     * @param object
     * @return The newly inserted DomainObject with's id and
     */
    public Object insert(Object object);


    /**
     * processes a bulk insert of a list of similar typed domain objects
     * @param object
     * @return The newly inserted DomainObject with's id and
     */
    public List<Object> bulkInsert(List<Object> object);

    /**
     * This utility method returns a list based on the first column in the result set based on the query and parameters provided.
     * The result set must have at least one column or an exception will be thrown.
     * @param
     * @param
     * @return
     */
    public List<Object> getList(String query, Object[] parameters);


    /**
     * This utility method returns a map based on the values of the first two columns returned from the result set based on the query and parameters provided.
     * The first column provides the map key, the second column provides the map value
     * The result set must have at least two columns or an exception will be thrown.
     * @param
     * @param
     * @return
     */
    public Map<Object, Object> getMap(String query, Object[] parameters);


    /**
     * This utility method returns a list representation of the result set based on the query and parameters provided. in the result-set order
     * Each entry in the list represents a row in the result set and contains a map of column-name, column-value pairs.
     * @param
     * @param
     * @return
     */
    public List<Map<String, Object>> getMapList(String query, Object[] parameters);


    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    public Collection<Map<String,Object>> getMapCollection(String query, Object[] parameters);

    /**
     * This utility method returns a list of collection representations of the the result set based on the query and parameters provided.
     * Each entry in the top list represents a different result set. each entry in the inner list
     * represents a row in the result set and contains a map of column-name, column-value pairs.
     * @param query
     * @param parameters
     * @return
     */
    public List<Collection<Map<String,Object>>> getMapCollectionList(String query, Object[] parameters);

    /**
     * Executes a statement on the data source that does not expect any return values
     * @param query
     * @param parameters
     * @return
     */
    public int executeStatement(String query, Object[] parameters);


    /**
     * returns date after executing the given query
     * @param query
     * @param parameters
     * @return
     */
    public Date getDate(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    Boolean getBoolean(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    Long getLong(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    Integer getInteger(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    Double getDouble(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    byte[] getBlob(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    String getString(String query, Object[] parameters);

    /**
     *
     * @param query
     * @param parameters
     * @return
     */
    String getXML(String query, Object[] parameters);

}
