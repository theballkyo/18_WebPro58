/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package com.pl.controllers;

import com.pl.helper.EmailHelper;
import com.pl.helper.TimeHelper;
import com.pl.leave.LeaveStatus;
import com.pl.leave.LeaveTimeType;
import com.pl.leave.LeaveType;
import com.pl.model.LeaveForm;
import com.pl.model.LeaveFormDao;
import com.pl.model.Section;
import com.pl.model.SectionDao;
import com.pl.model.User;
import com.pl.model.UserDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Hibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author theba
 */
@Controller
@RequestMapping(value = "/leave")
public class LeaveController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LeaveFormDao leaveFormDao;

    @Autowired
    private SectionDao sectionDao;

    @Autowired
    private EmailHelper email;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest req;

    @RequestMapping(value = "/sick", method = RequestMethod.GET)
    public String getSick() {
        return "leave/sick";
    }

    @RequestMapping(value = "/sick", method = RequestMethod.POST)
    public String saveSick(
            RedirectAttributes ra,
            String reason,
            String period,
            String period_date,
            String period_time,
            String start_at,
            String end_at,
            String contact,
            MultipartFile medical_certificate) {

        if (!medical_certificate.isEmpty()) {
            try {
                String fileName = medical_certificate.getOriginalFilename();

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("/" + fileName)));

                // Write file
                FileCopyUtils.copy(medical_certificate.getInputStream(), stream);
                stream.close();
                /*
                redirectAttributes.addFlashAttribute("message",
                        "You successfully uploaded " + name + "!");
                 */
            } catch (Exception e) {/*
                redirectAttributes.addFlashAttribute("message",
                        "You failed to upload " + name + " => " + e.getMessage());
                 */
            }
        }

        LeaveForm lf = new LeaveForm();
        User user = (User) session.getAttribute("user");
        Section section = user.getSection();
        lf.setLeaveType(LeaveType.SICK);
        lf.setLeaveContact(contact);
        lf.setReason(reason);
        lf.setSection(section);
        lf.setUser(user);
        lf.setLeaveStatus(LeaveStatus.WAIT);
        lf.setLeaveYear("2016");
        if (period.equals("half")) {
            Date date_ = TimeHelper.strToDate(period_date);
            lf.setLeaveStartAt(date_);
            lf.setLeaveEndAt(date_);

            if (period_time.equals("morning")) {
                lf.setTimeType(LeaveTimeType.MORNING);
            } else if (period_time.equals("afternoon")) {
                lf.setTimeType(LeaveTimeType.AFTERNOON);
            }
        } else if (period.equals("full")) {
            lf.setLeaveStartAt(TimeHelper.strToDate(start_at));
            lf.setLeaveEndAt(TimeHelper.strToDate(end_at));
        }

        leaveFormDao.save(lf);
        String toAddress = sectionDao.findOne(user.getSectionId()).getUser().getEmail();
        //String toAddress = user.getSection().getUser().getEmail();
        email.send(toAddress, "แจ้งการขอลาป่วย", "<html><body><bold>User แจ้งลางาน</bold></body></html>");

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String getPersonal(Model model) {
        return "leave/personal";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public String savePersonal(
            RedirectAttributes ra,
            String reason,
            String period,
            String period_date,
            String period_time,
            String start_at,
            String end_at,
            String contact) {
        LeaveForm lf = new LeaveForm();
        User user = (User) session.getAttribute("user");
        Section section = user.getSection();
        lf.setLeaveType(LeaveType.PERSONAL);
        lf.setLeaveContact(contact);
        lf.setReason(reason);
        lf.setSection(section);
        lf.setUser(user);
        lf.setLeaveStatus(LeaveStatus.WAIT);

        if (period.equals("half")) {
            Date date_ = TimeHelper.strToDate(period_date);
            lf.setLeaveStartAt(date_);
            lf.setLeaveEndAt(date_);

            if (period_time.equals("morning")) {
                lf.setTimeType(LeaveTimeType.MORNING);
            } else if (period_time.equals("afternoon")) {
                lf.setTimeType(LeaveTimeType.AFTERNOON);
            }
        } else if (period.equals("full")) {
            lf.setLeaveStartAt(TimeHelper.strToDate(start_at));
            lf.setLeaveEndAt(TimeHelper.strToDate(end_at));
        }

        try {
            leaveFormDao.save(lf);
        } catch (Exception e) {

        }

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/givebirth", method = RequestMethod.GET)
    public String getGiveBirth() {
        LeaveForm lf = new LeaveForm();
        lf.setLeaveType(LeaveType.GIVE_BIRTH);
        return "leave/givebirth";
    }

    @RequestMapping(value = "/givebirth", method = RequestMethod.POST)
    public String saveGiveBirth(
            RedirectAttributes ra,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date start_at,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date end_at,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date give_birth_date,
            String contact) {
        LeaveForm lf = new LeaveForm();
        lf.setLeaveStartAt(start_at);
        lf.setLeaveEndAt(end_at);
        lf.setLeaveContact(contact);
        lf.setGiveBirthDate(give_birth_date);

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();
        lf.setUser(user);
        lf.setSection(section);

        leaveFormDao.save(lf);

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.GET)
    public String getVacation(Model model) {
        User user = (User) session.getAttribute("user");
        List<User> users = userDao.findBySectionIdAndFetch(user.getSectionId());
        model.addAttribute("users", users);
        return "leave/vacation";
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.POST)
    public String saveVacation(
            RedirectAttributes ra,
            String start_at,
            String end_at,
            String contact,
            String work_represent) {
        LeaveForm lf = new LeaveForm(LeaveType.VACATION);
        lf.setLeaveStartAt(TimeHelper.strToDate(start_at));
        lf.setLeaveEndAt(TimeHelper.strToDate(end_at));
        lf.setLeaveContact(contact);
        lf.setWorkRepresent(work_represent);

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();
        lf.setUser(user);
        lf.setSection(section);

        try {
            leaveFormDao.save(lf);
        } catch (Exception e) {

        }

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/wife", method = RequestMethod.GET)
    public String getWife() {

        LeaveForm lf = new LeaveForm();
        lf.setLeaveType(LeaveType.WIFE);
        return "leave/wife";
    }

    @RequestMapping(value = "/wife", method = RequestMethod.POST)
    public String saveWife(
            RedirectAttributes ra,
            String wife_name,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date give_birth_date,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date start_at,
            @DateTimeFormat(pattern = "dd-MM-yyyy") Date end_at,
            String contact) {

        LeaveForm lf = new LeaveForm(LeaveType.WIFE);
        lf.setLeaveStartAt(start_at);
        lf.setLeaveEndAt(end_at);
        lf.setGiveBirthDate(give_birth_date);
        lf.setWifeName(wife_name);
        lf.setLeaveContact(contact);

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();
        lf.setUser(user);
        lf.setSection(section);

        leaveFormDao.save(lf);

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/history")
    public String history(Model model) {
        String username = ((User) session.getAttribute("user")).getUsername();
        List<LeaveForm> lfs = leaveFormDao.findByUsernameOrderByLeaveCreatedAtDesc(username);
        model.addAttribute("lfs", lfs);
        return "leave/history";
    }

    @RequestMapping(value = "/list")
    public String list(Model model) {
        User user = (User) session.getAttribute("user");
        List<Section> sections = sectionDao.findByManager(user.getUsername());

        if (sections == null || sections.isEmpty()) {

        }

        List<Integer> sectionIds = new LinkedList<>();
        for (Section section : sections) {
            sectionIds.add(section.getSectionId());
        }
        List<LeaveForm> lfs = null;
        if (sectionIds.contains(6)) {
            lfs = (List<LeaveForm>) leaveFormDao.findAll();
        } else {
            lfs = leaveFormDao.findBySectionIdInAndStatusAndYearForManagerSection(sectionIds, LeaveStatus.WAIT.value(), "2016");
        }
        model.addAttribute("lfs", lfs);
        return "leave/list";
    }

    @RequestMapping(value = "/cancel/{id}")
    public String cancel(@PathVariable("id") int id, RedirectAttributes ra) {
        User user = ((User) session.getAttribute("user"));
        LeaveForm lf = leaveFormDao.findOne(id);
        if (lf == null) {
            ra.addFlashAttribute("message", "error");
        } else if (!lf.getUsername().equals(user.getUsername())) {
            ra.addFlashAttribute("message", "error");
        } else if (!lf.isWait()) {
            ra.addFlashAttribute("message", "waitOnly");
        } else {
            leaveFormDao.delete(lf);
            ra.addFlashAttribute("message", "success");
        }
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/approve/{id}")
    public String approve(@PathVariable("id") int id, RedirectAttributes ra) {
        LeaveForm lf = leaveFormDao.findOne(id);
        if (lf == null) {
            return "null";
        } else if (lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3))) {
            ra.addFlashAttribute("message", "No time");
        } else if (!lf.isWait()) {
            return "Not wait";
        } else {
            lf.setLeaveStatus(LeaveStatus.APRROVE);
            leaveFormDao.save(lf);
            ra.addFlashAttribute("message", "success");
        }
        return "redirect:/leave/list";
    }

    @RequestMapping(value = "/reject/{id}")
    public String reject(@PathVariable("id") int id, RedirectAttributes ra) {
        LeaveForm lf = leaveFormDao.findOne(id);
        if (lf == null) {
            return "null";
        } else if (lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3))) {
            ra.addFlashAttribute("message", "No time");
        } else if (!lf.isWait()) {
            return "Not wait";
        } else {
            lf.setLeaveStatus(LeaveStatus.REJECT);
            leaveFormDao.save(lf);
            ra.addFlashAttribute("message", "success");
        }
        return "redirect:/leave/list";
    }
}
