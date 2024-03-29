/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import java.util.List;
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
public interface LeaveTypeDao extends CrudRepository<LeaveType, Integer>{
    
    @Query("FROM LeaveType lt LEFT JOIN FETCH lt.leaveRemain lr WHERE lr.username = ?1")
    public List<LeaveType> findAllAndGetRemainByUser(String username);
    
}
