/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author theba
 */
@Entity
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "teacher_id")
    private String teacherId;
    
    @Column(name = "username", insertable = false, updatable = false)
    private String username;
    
    @Column(name = "th_prename")
    private String thPrename;
    
    @Column(name = "th_name")
    private String thName;
    
    @Column(name = "en_prename")
    private String enPrename;
    
    @Column(name = "en_name")
    private String enName;
    
    @Column(name = "status_id", insertable = false, updatable = false)
    private int statusId;
    
    @Column(name = "mobile")
    private String mobile;
    
    @Column(name = "email")
    private String email;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;
    
    @Override
    public String toString() {
        return getThPrename() + " " + getThName();
    }

    /**
     * @return the teacherId
     */
    public String getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the thPrename
     */
    public String getThPrename() {
        return thPrename;
    }

    /**
     * @param thPrename the thPrename to set
     */
    public void setThPrename(String thPrename) {
        this.thPrename = thPrename;
    }

    /**
     * @return the thName
     */
    public String getThName() {
        return thName;
    }

    /**
     * @param thName the thName to set
     */
    public void setThName(String thName) {
        this.thName = thName;
    }

    /**
     * @return the enPrename
     */
    public String getEnPrename() {
        return enPrename;
    }

    /**
     * @param enPrename the enPrename to set
     */
    public void setEnPrename(String enPrename) {
        this.enPrename = enPrename;
    }

    /**
     * @return the enName
     */
    public String getEnName() {
        return enName;
    }

    /**
     * @param enName the enName to set
     */
    public void setEnName(String enName) {
        this.enName = enName;
    }

    /**
     * @return the statusId
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
