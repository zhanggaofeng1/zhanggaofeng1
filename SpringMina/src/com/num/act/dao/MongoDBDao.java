/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.num.act.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Administrator
 */
@Repository
public class MongoDBDao {
    
    @Autowired
    private MongoTemplate mongoTemplate;
    
    public void mongoDbTest() {
        mongoTemplate.find(null, null);
    }
}
