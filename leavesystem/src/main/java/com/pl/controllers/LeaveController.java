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
    
    @RequestMapping(value = "/leave/save", method = RequestMethod.POST)
    public String save() {
        return "";
    }
}
