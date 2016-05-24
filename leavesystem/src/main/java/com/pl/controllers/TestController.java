/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.controllers;

import com.pl.model.LeaveRemain;
import com.pl.model.LeaveRemainDao;
import com.pl.model.LeaveType;
import com.pl.model.LeaveTypeDao;
import com.pl.model.SystemConfigDao;
import com.pl.model.User;
import com.pl.model.UserDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author theba
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeaveRemainDao leaveRemainDao;

    @Autowired
    private LeaveTypeDao leaveTypeDao;

    @Autowired
    private SystemConfigDao scd;
    
    @RequestMapping(value = "genremain")
    @ResponseBody
    public String generateRemain() {
        Iterable<LeaveType> lts = leaveTypeDao.findAll();

        String a = "";
        for (User user : userDao.findAll()) {

            for (LeaveType lt : lts) {
                LeaveRemain lr = new LeaveRemain();
                lr.setUser(user);
                lr.setYear("2016");
                lr.setLeaveType(lt);
                lr.setAmount(lt.getAmountDefault());
                leaveRemainDao.save(lr);
            }

        }

        for (LeaveType lt : lts) {
            a += ":" + lt.getId();
        }

        return a;
    }

    @RequestMapping(value = "form")
    public String form() {
        return "test";
    }
    
    @RequestMapping(value = "query")
    @ResponseBody   
    public String query() {
        return scd.getYear();
    }
}
