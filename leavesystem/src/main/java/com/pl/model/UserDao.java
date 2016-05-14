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
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, String> {
    
    public User findByUsernameAndPassword(String username, String password);
    
    
    public List<User> findBySectionId(int sectionId);
    
    @Query("SELECT u, t, s from User u LEFT OUTER JOIN FETCH u.teacher t LEFT OUTER JOIN FETCH u.staff s WHERE u.sectionId = (:id) ")
    public List<User> findBySectionIdAndFetch(@Param("id") int sectionId);
}
