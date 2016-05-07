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
        return thPrename + " " + thName;
    }
}
