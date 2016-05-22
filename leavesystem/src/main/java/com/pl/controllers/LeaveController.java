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
import com.pl.model.LeaveRemain;
import com.pl.model.LeaveRemainDao;
import com.pl.model.Section;
import com.pl.model.SectionDao;
import com.pl.model.User;
import com.pl.model.UserDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validator.LeaveSickValidator;

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
    private LeaveRemainDao leaveRemainDao;

    @Autowired
    private EmailHelper email;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest req;

    @RequestMapping(value = "/sick", method = RequestMethod.GET)
    public String getSick(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), "2016", com.pl.leave.LeaveType.SICK.value());
        model.addAttribute("remain", lr.getAmount());
        return "leave/sick";
    }

    @RequestMapping(value = "/sick", method = RequestMethod.POST)
    public String saveSick(
            RedirectAttributes ra,
            Model model,
            @ModelAttribute("leaveForm") LeaveForm leaveForm,
            BindingResult result,
            MultipartFile medical_certificate) {

        Map<String, String> errors = new HashMap<>();
        model.addAttribute("errors", errors);

        if (!medical_certificate.isEmpty()) {
            try {
                String fileName = medical_certificate.getOriginalFilename();

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(req.getServletContext().getRealPath("/resources/imgs/") + fileName)));

                FileCopyUtils.copy(medical_certificate.getInputStream(), stream);
                stream.close();

            } catch (Exception e) {
                errors.put("medical_certificate", "การเขียนไฟล์ภาพมีปัญหา");
                return "leave/sick";
            }
        } else {
            errors.put("medical_certificate", "กรุณาเลือกไฟล์ด้วย");
        }

        LeaveSickValidator lsv = new LeaveSickValidator();
        lsv.validate(leaveForm, result);
        
        if (result.hasErrors()) {
            ra.addFlashAttribute("errors", result.getFieldErrors());
            /*
            String a = "";
            for (FieldError error : result.getFieldErrors()) {
                a += error.toString();
            }
            */
            return "redirect:/leave/sick";
        }
        
        User user = (User) session.getAttribute("user");
        Section section = user.getSection();

        leaveForm.setUser(user);
        leaveForm.setSection(section);
        leaveForm.setLeaveYear("2016");

        leaveFormDao.save(leaveForm);
        // String toAddress = sectionDao.findOne(user.getSectionId()).getUser().getEmail();
        // String toAddress = user.getSection().getUser().getEmail();
        // email.send(toAddress, "แจ้งการขอลาป่วย", "<html><body><bold>User แจ้งลางาน</bold></body></html>");

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
        //return leaveForm.getLeaveType() +"";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String getPersonal(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), "2016", com.pl.leave.LeaveType.PERSONAL.value());
        model.addAttribute("remain", lr.getAmount());
        return "leave/personal";
    }

    @RequestMapping(value = "/personal", method = RequestMethod.POST)
    public String savePersonal(
            RedirectAttributes ra,
            Model model,
            @ModelAttribute("leaveForm") LeaveForm leaveForm,
            BindingResult result) {

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();

        leaveForm.setUser(user);
        leaveForm.setSection(section);
        leaveForm.setLeaveYear("2016");

        leaveFormDao.save(leaveForm);
        // String toAddress = sectionDao.findOne(user.getSectionId()).getUser().getEmail();
        // String toAddress = user.getSection().getUser().getEmail();
        // email.send(toAddress, "แจ้งการขอลาป่วย", "<html><body><bold>User แจ้งลางาน</bold></body></html>");

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/givebirth", method = RequestMethod.GET)
    public String getGiveBirth(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), "2016", com.pl.leave.LeaveType.GIVE_BIRTH.value());
        model.addAttribute("remain", lr.getAmount());
        return "leave/givebirth";
    }

    @RequestMapping(value = "/givebirth", method = RequestMethod.POST)
    public String saveGiveBirth(
            RedirectAttributes ra,
            Model model,
            @ModelAttribute("leaveForm") LeaveForm leaveForm,
            BindingResult result) {
        
        User user = (User) session.getAttribute("user");
        Section section = user.getSection();

        leaveForm.setUser(user);
        leaveForm.setSection(section);
        leaveForm.setLeaveYear("2016");

        leaveFormDao.save(leaveForm);

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.GET)
    public String getVacation(Model model) {
        User user = (User) session.getAttribute("user");

        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), "2016", com.pl.leave.LeaveType.VACATION.value());
        model.addAttribute("remain", lr.getAmount());

        List<User> users = userDao.findBySectionIdAndFetch(user.getSectionId());
        model.addAttribute("users", users);
        return "leave/vacation";
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.POST)
    public String saveVacation(
            RedirectAttributes ra,
            Model model,
            @ModelAttribute("leaveForm") LeaveForm leaveForm,
            BindingResult result) {

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();

        leaveForm.setUser(user);
        leaveForm.setSection(section);
        leaveForm.setLeaveYear("2016");
        User workRepresent = userDao.findOne(leaveForm.getWorkRepresent());
        if (workRepresent != null) {
            leaveForm.setUserWorkRepresent(workRepresent);
        }
        
        leaveFormDao.save(leaveForm);

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/wife", method = RequestMethod.GET)
    public String getWife(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), "2016", com.pl.leave.LeaveType.WIFE.value());
        model.addAttribute("remain", lr.getAmount());

        return "leave/wife";
    }

    @RequestMapping(value = "/wife", method = RequestMethod.POST)
    public String saveWife(
            RedirectAttributes ra,
            Model model,
            @ModelAttribute("leaveForm") LeaveForm leaveForm,
            BindingResult result) {

        User user = (User) session.getAttribute("user");
        Section section = user.getSection();

        leaveForm.setUser(user);
        leaveForm.setSection(section);
        leaveForm.setLeaveYear("2016");

        leaveFormDao.save(leaveForm);

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
