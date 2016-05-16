/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.controllers;

/**
 *
 * @author theba
 */
import com.pl.helper.EmailHelper;
import com.pl.model.RoleDao;
import com.pl.model.SectionDao;
import com.pl.model.User;
import com.pl.model.UserDao;

import javax.servlet.http.HttpSession;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserDao usersDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private HttpSession session;

    @Autowired
    private EmailHelper email;

    @RequestMapping(value = "/")
    public String Home(Model model) {
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        return "redirect:/member";
    }

    @RequestMapping(value = "/test")
    public String Test(Model model) {
        return "test";
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public String member() {
        return "member";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String Login(Model model) {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String DoLogin(String username, String password) {
        User user = usersDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            return "login";
        }
        if (!sectionDao.findByManager(user.getUsername()).isEmpty()) {
            session.setAttribute("isManager", true);
        } else {
            session.setAttribute("isManager", false);
        }
        session.setAttribute("user", user);
        Hibernate.initialize(user.getSection());

        return "redirect:/member";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }

    @RequestMapping(value = "/mail")
    @ResponseBody
    public String testMail() {
        email.send("theballkyo@gmail.com", "theballkyo@gmail.com", "test Email", "TEST TEST TEST");
        return "ok";
    }

    /*
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String Agenda(Model model) {
        model.addAttribute("User", new User());
        return "Users";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(String username) {
        User users = null;
        Role role = roleDao.findOne(2);
        try {
            users = new User(username, "itkmitl", role);
            if (usersDao.exists(username)) {
                return "User already used.";
            }
            usersDao.save(users);
        } catch (Exception ex) {
            return "Error creating the user: " + ex.toString();
        }
        return "User successfully created! (id =" + users.getRole().getRoleName() + ")";
    }
     */
}
