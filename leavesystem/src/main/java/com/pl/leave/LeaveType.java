/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.leave;

/**
 *
 * @author theba
 */
public enum LeaveType {
    SICK (1, "ลาป่วย"), PERSONAL (2, "ลากิจ"), VACATION (3, "ลาพักผ่อน"), GIVE_BIRTH (4, "ลาคลอด"), WIFE (5, "ลาช่วยภรรยาคลอด");
    
    private final int statusValue;
    private final String statusThName;
    LeaveType(int statusValue, String status) {
        this.statusValue = statusValue;
        this.statusThName = status;
    }
    
    /**
     *
     * @return
     */
    public int value() {
        return this.statusValue;
    }
    
    public String thName() {
        return this.statusThName;
    }
}
