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
@Table(name = "staff")
public class Staff {

    @Id
    @Column(name = "staff_id")
    private String staffId;

    @Column(name = "username", updatable = false, insertable = false)
    private String username;

    @Column(name = "th_name")
    private String thName;

    @Column(name = "en_name")
    private String enName;

    @Column(name = "section_id", insertable = false, updatable = false)
    private int sectionId;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
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
     * @return the section
     */
    public Section getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(Section section) {
        this.section = section;
    }
    
    public String toString() {
        return "คุณ " + thName;
    }
}
