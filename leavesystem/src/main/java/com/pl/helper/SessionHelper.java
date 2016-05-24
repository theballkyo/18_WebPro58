/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.helper;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;
import com.pl.model.User;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author theba
 */
@JadeHelper("session")
public class SessionHelper {
    
    @Autowired
    private HttpSession session;
    
    public Object get(String name) {
        return session.getAttribute(name);
    }
    
    public User getUser() {
        return (User) session.getAttribute("user");
    }
}
