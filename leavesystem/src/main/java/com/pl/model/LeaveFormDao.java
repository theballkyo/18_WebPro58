/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

/**
 *
 * @author theba
 */
import com.pl.leave.LeaveStatus;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface LeaveFormDao extends CrudRepository<LeaveForm, Integer> {

    public LeaveForm findByLeaveIdAndUsername(int leaveId, String username);
    
    public List<LeaveForm> findByUsername(String username);

    @Query("from LeaveForm lf LEFT OUTER JOIN FETCH lf.user u LEFT OUTER JOIN FETCH u.teacher t LEFT OUTER JOIN FETCH u.staff s WHERE lf.sectionId IN (?1) AND lf.leaveStatus = (?2)")
    public List<LeaveForm> findBySectionIdInAndStatus(List<Integer> sectionIdList, int status);
    
}
