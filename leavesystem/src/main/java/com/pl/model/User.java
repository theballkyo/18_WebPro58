package com.pl.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author theba
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Transient
    final private int TEACHER_ROLE_NUM = 1;
    
    @Transient
    final private int STAFF_ROLE_NUM = 2;
    
    @Id
    @Column(name = "username")
    @NotNull
    @Min(value = 19)
    private String username;

    @Column(name = "password")
    @NotNull(message = "รหัสผ่านห้ามเป็นค่าว่าง")
    private String password;

    @Column(name = "role_id", updatable = false, insertable = false)
    private int roleId;

    @Column(name = "section_id", updatable = false, insertable = false)
    private int sectionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)   
    private Set<Section> manageSection;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Staff staff;
    
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Teacher teacher;
    
    @OneToMany(mappedBy = "user")
    private Set<LeaveForm> leaveForm;
    
    public User(String username, String password, Role role) {
        super();
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(Role role) {
        this.role = role;
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

    public User(String username) {
        super();
        this.username = username;
    }

    public User() {
        super();
    }
    
    public boolean isTeacher() {
        return this.roleId == TEACHER_ROLE_NUM;
    }
    
    public boolean isStaff() {
        return this.roleId == STAFF_ROLE_NUM;
    }

    /**
     * @return the section
     */
    public Section getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(Section section) {
        this.section = section;
    }

    /**
     * @return the sectionId
     */
    public int getSectionId() {
        return sectionId;
    }

    /**
     * @param sectionId the sectionId to set
     */
    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * @return the staff
     */
    public Staff getStaff() {
        return staff;
    }

    /**
     * @param staff the staff to set
     */
    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    /**
     * @return the teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString()
    {
        if (isStaff()) {
            return staff.toString();
        } else {
            return teacher.toString();
        }
    }
    
    public String getMobile()
    {
        if (isStaff()) {
            return staff.getMobile();
        } else {
            return teacher.getMobile();
        }
    } 

    /**
     * @return the leaveForm
     */
    public Set<LeaveForm> getLeaveForm() {
        return leaveForm;
    }

    /**
     * @param leaveForm the leaveForm to set
     */
    public void setLeaveForm(Set<LeaveForm> leaveForm) {
        this.leaveForm = leaveForm;
    }

    /**
     * @return the manageSection
     */
    public Set<Section> getManageSection() {
        return manageSection;
    }

    /**
     * @param manageSection the manageSection to set
     */
    public void setManageSection(Set<Section> manageSection) {
        this.manageSection = manageSection;
    }

}
