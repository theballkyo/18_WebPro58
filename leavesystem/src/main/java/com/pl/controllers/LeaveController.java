/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.controllers;

import com.pl.model.LeaveForm;
import com.pl.model.LeaveFormDao;
import com.pl.model.User;
import com.pl.model.UserDao;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author theba
 */
@Controller
public class LeaveController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeaveFormDao leaveFormDao;

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
    @ResponseBody
    public String save(HttpSession session, String start_at, String end_at, String contact, String work_represent) {
        User user = (User) session.getAttribute("user");
        user.getSection();
        LeaveForm leaveForm = new LeaveForm();
        leaveForm.setLeaveCreatedAt(new Date());
        leaveForm.setLeaveStartAt(new Date());
        leaveForm.setLeaveEndAt(new Date());
        leaveForm.setLeaveYear("2016");
        leaveForm.setLeaveTotalDate(1);
        leaveForm.setLeaveContact(contact);
        leaveForm.setLeaveType(1);
        leaveForm.setLeaveStatus(1);
        leaveForm.setUser(user);
        leaveForm.setSection(user.getSection());
        
        if (leaveFormDao.save(leaveForm) == null) {
            return "Can't save.";
        }
        
        return "Saved.";
    }
}
