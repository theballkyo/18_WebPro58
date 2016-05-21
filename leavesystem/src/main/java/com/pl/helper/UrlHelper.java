/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.helper;

import com.domingosuarez.boot.autoconfigure.jade4j.JadeHelper;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author theba
 */
@JadeHelper("url")
public class UrlHelper {
    
    public String create(String path) {
        if (path.startsWith("/")) {
            return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).build().toUriString(); 
        }
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/" + path).build().toUriString();
    }
    
}
