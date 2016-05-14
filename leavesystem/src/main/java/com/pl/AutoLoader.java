/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author theba
 */
@Component
public class AutoLoader {
    
    @Autowired
    private ServletContext sc;
    
    @PostConstruct
    public void init() {
        sc.setAttribute("aaa", "test");
    }
}
