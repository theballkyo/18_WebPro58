/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author theba
 */
@Transactional
@Repository
public interface SystemConfigDao extends CrudRepository<SystemConfig, Integer>{
    
    public SystemConfig findByKey(String key);
    
    @Query("SELECT sc.value FROM SystemConfig sc WHERE sc.key = 'year'")
    public String getYear();
}
