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
@Table(name = "section")
public class Section {
    
    @Id
    @Column(name = "section_id")
    private int sectionId;
    
    @Column(name = "th_name")
    private String thName;
    
    @Column(name = "en_name")
    private String enName;
    
    @Column(name = "manager", insertable = false, updatable = false)
    private String manager;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager")
    private User user;
}
