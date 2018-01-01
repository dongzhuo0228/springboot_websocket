package com.example.service;

import java.util.Map;
public interface MongoService {
    /**
     * Get Data BY ID
     * 
     * @param db
     * @param table
     * @param Id
     * @throws Exception 
     */
    public Map<String,Integer> queryByID( String table, Object Id) throws Exception;

    /**
     * Insert Data
     * 
     * @param db
     * @param table
     * @param document
     */
//    public boolean insert(String table, Document doc);

    /**
     * Delete Many Data.if doc is empty will delete all Data
     * 
     * @param db
     * @param table
     * @param document
     */
//    public boolean delete( String table, BasicDBObject doc);

    /**
     * Update All Data
     * 
     * @param db
     * @param table
     * @param oldDoc
     * @param newDoc
     */
  /*  public boolean update(String table, BasicDBObject oldDoc,
            BasicDBObject newDoc);
*/
}
