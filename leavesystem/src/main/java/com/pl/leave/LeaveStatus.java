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
public enum LeaveStatus {
    WAIT (1), APRROVE (2), REJECT (3);

    private final int statusValue;
    LeaveStatus(int statusValue) {
        this.statusValue = statusValue;
    }
    
    /**
     *
     * @return
     */
    public int value() {
        return this.statusValue;
    }
}
