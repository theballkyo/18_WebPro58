/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author theba
 */
@Entity
@Table(name = "status")
public class Status {
    
    @Id
    @Column(name = "status_id")
    private int statusId;
    
    @Column(name = "status_name")
    private String statusName;
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusId")
    private Set<Teacher> teachers;
}
