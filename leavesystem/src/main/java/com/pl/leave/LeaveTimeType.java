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
public enum LeaveTimeType {
    FULL (1, "เต็มวัน"), MORNING (2, "ช่วงเช้า"), AFTERNOON (3, "ช่วงบ่าย");
    
    private final int statusValue;
    private final String thName;
    LeaveTimeType(int statusValue, String thName) {
        this.statusValue = statusValue;
        this.thName = thName;
    }
    
    /**
     *
     * @return
     */
    public int value() {
        return this.statusValue;
    }
    
    public String thName() {
        return this.thName;
    }
}
