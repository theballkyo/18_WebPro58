/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.controllers;

import com.pl.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author theba
 */
@Controller
public class LeaveController {
    
    @Autowired
    private UserDao userDao;
    
    @RequestMapping(value = "/leave/sick", method = RequestMethod.GET)
    public String getSick() {
        return "leave/sick";
    }
    
    @RequestMapping(value = "/leave/personal", method = RequestMethod.GET)
    public String getPersonal() {
        return "leave/personal";
    }
    
    @RequestMapping(value = "/leave/givebirth", method = RequestMethod.GET)
    public String getGiveBirth() {
        return "leave/givebirth";
    }
    
    @RequestMapping(value = "/leave/vacation", method = RequestMethod.GET)
    public String getVacation() {
        return "leave/vacation";
    }
    
    @RequestMapping(value = "/leave/wife", method = RequestMethod.GET)
    public String getWife() {
        return "leave/wife";
    }
    
    
    @RequestMapping(value = "/leave/save", method = RequestMethod.POST)
    public String save() {
        return "";
    }
}
