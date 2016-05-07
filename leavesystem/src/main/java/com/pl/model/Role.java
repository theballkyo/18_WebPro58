/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author theba
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
    
    @Id
    @Column(name = "role_id")
    private int roleId;
    
    @Column(name = "role_name")
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "roleId")       
    private Set<User> user;
    
    public Role() {
        
    }

    public Role(int roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }
    
    /**
     * @return the roleId
     */
    public int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the user
     */
    public Set<User> getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(Set<User> user) {
        this.user = user;
    }
    
}
