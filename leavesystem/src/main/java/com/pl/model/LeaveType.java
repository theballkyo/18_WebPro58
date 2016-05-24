/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author theba
 */
@Entity
@Table(name = "leave_type")
public class LeaveType {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(name = "th_name")
    private String thName;
    
    @Column(name = "amount_default")
    private int amountDefault;
    
    @OneToMany(mappedBy = "leaveType")
    private List<LeaveRemain> leaveRemains;

    @OneToOne(mappedBy = "leaveType", fetch = FetchType.LAZY)
    private LeaveRemain leaveRemain;
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
     * @return the leaveRemain
     */
    public List<LeaveRemain> getLeaveRemains() {
        return leaveRemains;
    }

    /**
     * @param leaveRemains the leaveRemain to set
     */
    public void setLeaveRemains(List<LeaveRemain> leaveRemains) {
        this.leaveRemains = leaveRemains;
    }

    /**
     * @return the amountDefault
     */
    public int getAmountDefault() {
        return amountDefault;
    }

    /**
     * @param amountDefault the amountDefault to set
     */
    public void setAmountDefault(int amountDefault) {
        this.amountDefault = amountDefault;
    }

    /**
     * @return the leaveRemain
     */
    public LeaveRemain getLeaveRemain() {
        return leaveRemain;
    }

    /**
     * @param leaveRemain the leaveRemain to set
     */
    public void setLeaveRemain(LeaveRemain leaveRemain) {
        this.leaveRemain = leaveRemain;
    }
    
    
    
}
