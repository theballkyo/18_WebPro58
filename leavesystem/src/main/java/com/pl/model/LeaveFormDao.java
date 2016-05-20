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
    
    @Query("from LeaveForm lf WHERE lf.leaveId = ?1 AND lf.leaveCreatedAt > (CURRENT_DATE - 3)")
    public LeaveForm findOneForManagerCheck(int leaveId);
    
    public LeaveForm findByLeaveIdAndUsername(int leaveId, String username);
    
    public List<LeaveForm> findByUsernameOrderByLeaveCreatedAtDesc(String username);

    @Query("from LeaveForm lf LEFT OUTER JOIN FETCH lf.user u LEFT OUTER JOIN FETCH u.teacher t LEFT OUTER JOIN FETCH u.staff s WHERE lf.sectionId IN (?1) AND lf.leaveStatus = ?2 AND lf.leaveYear = ?3 AND lf.leaveCreatedAt > (CURRENT_DATE - 3)")
    public List<LeaveForm> findBySectionIdInAndStatusAndYearForManagerSection(List<Integer> sectionIdList, int status, String year);
    
    @Query("SELECT SUM(lf.leaveTotalDate) FROM LeaveForm lf WHERE lf.username = ?1 AND lf.leaveYear = ?2")
    public float countTotalDay(String username, String year);
}
