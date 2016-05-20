/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pl.model;

import com.pl.helper.TimeHelper;
import com.pl.leave.LeaveStatus;
import com.pl.leave.LeaveTimeType;
import com.pl.leave.LeaveType;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author theba
 */
@Entity
@Table(name = "leave_form")
public class LeaveForm {
    @Id
    @Column(name = "leave_id", nullable = false)
    private int leaveId;
    
    @Column(name = "leave_created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date leaveCreatedAt;
    
    @Column(name = "leave_start_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date leaveStartAt;
    
    @Column(name = "leave_end_at", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date leaveEndAt;
    
    @Column(name = "leave_year", nullable = false)
    private String leaveYear;
    
    @Column(name = "leave_total_date", nullable = false)
    private double leaveTotalDate;
    
    @Column(name = "leave_contact", nullable = false)
    private String leaveContact;
    
    @Column(name = "leave_type", nullable = false)
    private int leaveType;
    
    @Column(name = "time_type", nullable = false)
    private int timeType;
    
    @Column(name = "leave_status", nullable = false)
    private int leaveStatus;
    
    @Column(name = "approve_comment")
    private String approveComment;
    
    @Column(name = "approve_date")
    private String approveDate;
    
    @Column(name = "approve_by", insertable = false, updatable = false)
    private String approveBy;
    
    @Column(name = "wife_name")
    private String wifeName;
    
    @Column(name = "give_birth_date")
    @Temporal(TemporalType.DATE)
    private Date giveBirthDate;
    
    @Column(name = "reason")
    private String reason;
    
    @Column(name = "medical_certificate")
    private String medicalCertificate;
    
    @Column(name = "work_represent", insertable = false, updatable = false)
    private String workRepresent;
    
    @Column(name = "username", insertable = false, updatable = false, nullable = false)
    private String username;
    
    @Column(name = "section_id", insertable = false, updatable = false, nullable = false)
    private int sectionId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_represent")
    private User userWorkRepresent;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    public LeaveForm() {
        timeType = LeaveTimeType.FULL.value();
        leaveStatus = LeaveStatus.WAIT.value();
    }
    
    public LeaveForm(LeaveType lt) {
        leaveType = lt.value();
        timeType = LeaveTimeType.FULL.value();
        leaveStatus = LeaveStatus.WAIT.value();
    }
    
    public LeaveForm(LeaveType lt, LeaveTimeType ltt) {
        leaveType = lt.value();
        timeType = ltt.value();
        leaveStatus = LeaveStatus.WAIT.value();
    }
    
    /**
     * @return the leaveId
     */
    public int getLeaveId() {
        return leaveId;
    }

    /**
     * @param leaveId the leaveId to set
     */
    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
    }

    /**
     * @return the leaveCreatedAt
     */
    public Date getLeaveCreatedAt() {
        return leaveCreatedAt;
    }

    /**
     * @param leaveCreatedAt the leaveCreatedAt to set
     */
    public void setLeaveCreatedAt(Date leaveCreatedAt) {
        this.leaveCreatedAt = leaveCreatedAt;
    }

    /**
     * @return the leaveStartAt
     */
    public Date getLeaveStartAt() {
        return leaveStartAt;
    }

    /**
     * @param leaveStartAt the leaveStartAt to set
     */
    public void setLeaveStartAt(Date leaveStartAt) {
        this.leaveStartAt = leaveStartAt;
    }

    /**
     * @return the leaveEndAt
     */
    public Date getLeaveEndAt() {
        return leaveEndAt;
    }

    /**
     * @param leaveEndAt the leaveEndAt to set
     */
    public void setLeaveEndAt(Date leaveEndAt) {
        this.leaveEndAt = leaveEndAt;
    }

    /**
     * @return the leaveYear
     */
    public String getLeaveYear() {
        return leaveYear;
    }

    /**
     * @param leaveYear the leaveYear to set
     */
    public void setLeaveYear(String leaveYear) {
        this.leaveYear = leaveYear;
    }

    /**
     * @return the leaveTotalDate
     */
    public double getLeaveTotalDate() {
        return leaveTotalDate;
    }

    /**
     * @param leaveTotalDate the leaveTotalDate to set
     */
    public void setLeaveTotalDate(double leaveTotalDate) {
        this.leaveTotalDate = leaveTotalDate;
    }

    /**
     * @return the leaveContact
     */
    public String getLeaveContact() {
        return leaveContact;
    }

    /**
     * @param leaveContact the leaveContact to set
     */
    public void setLeaveContact(String leaveContact) {
        this.leaveContact = leaveContact;
    }

    /**
     * @return the leaveType
     */
    public int getLeaveType() {
        return leaveType;
    }

    /**
     * @param type 
     */
    public void setLeaveType(LeaveType type) {
        leaveType = type.value();
    }

    /**
     * @return the leaveStatus
     */
    public int getLeaveStatus() {
        return leaveStatus;
    }

    /**
     * @param l
     */
    public void setLeaveStatus(LeaveStatus l) {
        this.leaveStatus = l.value();
    }

    /**
     * @return the approveComment
     */
    public String getApproveComment() {
        return approveComment;
    }

    /**
     * @param approveComment the approveComment to set
     */
    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }

    /**
     * @return the approveDate
     */
    public String getApproveDate() {
        return approveDate;
    }

    /**
     * @param approveDate the approveDate to set
     */
    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    /**
     * @return the approveBy
     */
    public String getApproveBy() {
        return approveBy;
    }

    /**
     * @param approveBy the approveBy to set
     */
    public void setApproveBy(String approveBy) {
        this.approveBy = approveBy;
    }

    /**
     * @return the wifeName
     */
    public String getWifeName() {
        return wifeName;
    }

    /**
     * @param wifeName the wifeName to set
     */
    public void setWifeName(String wifeName) {
        this.wifeName = wifeName;
    }

    /**
     * @return the giveBirthDate
     */
    public Date getGiveBirthDate() {
        return giveBirthDate;
    }

    /**
     * @param giveBirthDate the giveBirthDate to set
     */
    public void setGiveBirthDate(Date giveBirthDate) {
        this.giveBirthDate = giveBirthDate;
    }

    /**
     * @return the reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * @param reason the reason to set
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return the medicalCertificate
     */
    public String getMedicalCertificate() {
        return medicalCertificate;
    }

    /**
     * @param medicalCertificate the medicalCertificate to set
     */
    public void setMedicalCertificate(String medicalCertificate) {
        this.medicalCertificate = medicalCertificate;
    }

    /**
     * @return the workRepresent
     */
    public String getWorkRepresent() {
        return workRepresent;
    }

    /**
     * @param workRepresent the workRepresent to set
     */
    public void setWorkRepresent(String workRepresent) {
        this.workRepresent = workRepresent;
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
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
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
     * @return the timeType
     */
    public int getTimeType() {
        return timeType;
    }

    /**
     * @param ltt
     */
    public void setTimeType(LeaveTimeType ltt) {
        this.timeType = ltt.value();
    }

    /**
     * @return the userWorkRepresent
     */
    public User getUserWorkRepresent() {
        return userWorkRepresent;
    }

    /**
     * @param userWorkRepresent the userWorkRepresent to set
     */
    public void setUserWorkRepresent(User userWorkRepresent) {
        this.userWorkRepresent = userWorkRepresent;
    }
    
    public boolean isWait() {
        return leaveStatus == LeaveStatus.WAIT.value();
    }
    
    public boolean isApprove() {
        return leaveStatus == LeaveStatus.APRROVE.value();
    }
    
    public boolean isReject() {
        return leaveStatus == LeaveStatus.REJECT.value();
    }
    
    public String getThName() {
        for (LeaveType lt : LeaveType.values()) {
            if (lt.value() == leaveType) {
                return lt.thName();
            }
        }
        
        return "Unknown";
    }
    
    public String getLeaveCreatedAtFormat() {
        return TimeHelper.formatDate(leaveCreatedAt);
    }
    
    public String getLeaveStartAtFormat() {
        return TimeHelper.formatDateNoTime(leaveStartAt);
    }
    
    public String getLeaveEndAtFormat() {
        return TimeHelper.formatDateNoTime(leaveEndAt);
    }
    
    public String getLeaveTimeTypeText() {
        for (LeaveTimeType ltt : LeaveTimeType.values()) {
            if (ltt.value() == timeType) {
                return ltt.thName();
            }
        }
        
        return "Unknown";
    }
}
