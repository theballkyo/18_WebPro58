/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. 
 */
package com.pl.controllers;

import com.pl.helper.EmailHelper;
import com.pl.leave.LeaveStatus;
import com.pl.model.AssociateDeanDao;
import com.pl.model.DeanDao;
import com.pl.model.LeaveForm;
import com.pl.model.LeaveFormDao;
import com.pl.model.LeaveRemain;
import com.pl.model.LeaveRemainDao;
import com.pl.model.Section;
import com.pl.model.SectionDao;
import com.pl.model.SystemConfigDao;
import com.pl.model.User;
import com.pl.model.UserDao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import validator.LeaveGiveBirthValidator;
import validator.LeavePersonalValidator;
import validator.LeaveSickValidator;
import validator.LeaveVacationValidator;
import validator.LeaveWifeValidator;

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
    private DeanDao deanDao;

    @Autowired
    private AssociateDeanDao associateDeanDao;

    @Autowired
    private SystemConfigDao systemConfigDao;

    @Autowired
    private EmailHelper email;

    @Autowired
    private HttpSession session;

    @Autowired
    private HttpServletRequest req;

    @Autowired
    private ServletContext servletContext;

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

        if (!medical_certificate.isEmpty()) {
            try {
                String fileName = medical_certificate.getOriginalFilename();

                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File(req.getServletContext().getRealPath("/") + "resources/imgs/" + fileName)));

                FileCopyUtils.copy(medical_certificate.getInputStream(), stream);
                stream.close();

            } catch (Exception e) {
                return "leave/sick";
            }
        }
        User user = (User) session.getAttribute("user");
        LeaveSickValidator lsv = new LeaveSickValidator(user, leaveRemainDao, systemConfigDao.getYear());
        lsv.validate(leaveForm, result);

        if (result.hasErrors()) {
            result.getFieldError();
            ra.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/leave/sick";
        }

        leaveFormDao.save(leaveForm);
        leaveRemainDao.save(leaveForm.getLeaveRemain());

        // Send email .. bla bla bla
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
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), systemConfigDao.getYear(), com.pl.leave.LeaveType.PERSONAL.value());
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
        LeavePersonalValidator lpv = new LeavePersonalValidator(user, leaveRemainDao, systemConfigDao.getYear());
        lpv.validate(leaveForm, result);

        if (result.hasErrors()) {
            result.getFieldError();
            ra.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/leave/personal";
        }

        leaveFormDao.save(leaveForm);
        leaveRemainDao.save(leaveForm.getLeaveRemain());

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/vacation", method = RequestMethod.GET)
    public String getVacation(Model model) {
        User user = (User) session.getAttribute("user");

        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), systemConfigDao.getYear(), com.pl.leave.LeaveType.VACATION.value());
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
        LeaveVacationValidator lvv = new LeaveVacationValidator(user, leaveRemainDao, systemConfigDao.getYear(), userDao);
        lvv.validate(leaveForm, result);

        if (result.hasErrors()) {
            result.getFieldError();
            ra.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/leave/vacation";
        }

        leaveFormDao.save(leaveForm);
        leaveRemainDao.save(leaveForm.getLeaveRemain());

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/givebirth", method = RequestMethod.GET)
    public String getGiveBirth(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), systemConfigDao.getYear(), com.pl.leave.LeaveType.GIVE_BIRTH.value());
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
        LeaveGiveBirthValidator lgbv = new LeaveGiveBirthValidator(user, leaveRemainDao, systemConfigDao.getYear());
        lgbv.validate(leaveForm, result);

        if (result.hasErrors()) {
            result.getFieldError();
            ra.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/leave/givebirth";
        }

        leaveFormDao.save(leaveForm);
        leaveRemainDao.save(leaveForm.getLeaveRemain());

        ra.addFlashAttribute("message", "saveSuccess");
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/wife", method = RequestMethod.GET)
    public String getWife(Model model) {
        User user = (User) session.getAttribute("user");
        LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), systemConfigDao.getYear(), com.pl.leave.LeaveType.WIFE.value());
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
        LeaveWifeValidator lwv = new LeaveWifeValidator(user, leaveRemainDao, systemConfigDao.getYear());
        lwv.validate(leaveForm, result);

        if (result.hasErrors()) {
            result.getFieldError();
            ra.addFlashAttribute("errors", result.getAllErrors());
            return "redirect:/leave/givebirth";
        }

        leaveFormDao.save(leaveForm);
        leaveRemainDao.save(leaveForm.getLeaveRemain());

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

        // Not manager
        if (sections == null || sections.isEmpty()) {
            return "redirect:/member";
        }

        String username = user.getUsername();
        String year = systemConfigDao.getYear();
        int status = LeaveStatus.WAIT.value();

        boolean isDean = deanDao.findOne(username) != null;
        boolean isAssociateDean = associateDeanDao.findOne(username) != null;
        // 6 is Human resource section.
        boolean isSuperManager = sectionDao.countByManagerAndSectionId(user.getUsername(), 6) != 0;
        List<LeaveForm> lfs;

        if (isDean) {
            lfs = leaveFormDao.findByLeaveStatusAndLeaveYearAndUsernameNot(status, year, username);
        } else if (isSuperManager) {
            lfs = leaveFormDao.findByForSuperManagerByUsernameAndLeaveStatusAndLeaveYear(username, status, year);
        } else if (isAssociateDean) {
            lfs = leaveFormDao.findForAssociateDeanByUsernameAndYearAndStatus(username, status, year);
        } else {
            lfs = leaveFormDao.findForManagerByUsernameAndStatusAndYear(username, status, year);
        }
        model.addAttribute("lfs", lfs);
        return "leave/list";
    }

    @RequestMapping(value = "/cancel/{id}")
    public String cancel(@PathVariable("id") int id, RedirectAttributes ra) {
        User user = ((User) session.getAttribute("user"));
        LeaveForm lf = leaveFormDao.findOne(id);
        if (lf == null) {
            ra.addFlashAttribute("message", "not_found");
        } else if (!lf.getUsername().equals(user.getUsername())) {
            ra.addFlashAttribute("message", "owner_error");
        } else if (!lf.isWait()) {
            ra.addFlashAttribute("message", "wait_only");
        } else {
            leaveFormDao.delete(lf);
            ra.addFlashAttribute("message", "cancel_success");
        }
        return "redirect:/leave/history";
    }

    @RequestMapping(value = "/approve/{id}")
    public String approve(@PathVariable("id") int id, RedirectAttributes ra) {
        LeaveForm lf = leaveFormDao.findOne(id);
        User user = (User) session.getAttribute("user");
        if (lf == null) {
            ra.addFlashAttribute("message", "not_found");
        } else if (lf.getUsername().equals(user.getUsername())) {
            ra.addFlashAttribute("message", "no_permission");
        } else if (!lf.isWait()) {
            ra.addFlashAttribute("message", "wait_only");
        } else {
            String username = user.getUsername();
            boolean isDean = deanDao.findOne(username) != null;
            boolean isAssociateDean = associateDeanDao.findOne(username) != null;
            // 6 is Human resource section.
            boolean isSuperManager = sectionDao.countByManagerAndSectionId(user.getUsername(), 6) != 0;
            boolean after3day = lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3));

            if (isDean) {
                ra.addFlashAttribute("message", "success");
            } else if (isAssociateDean && lf.getUser().getSectionId() != 16) {
                if (lf.getUser().getSection().getManager().equals(user.getUsername())) {
                    ra.addFlashAttribute("message", "success");
                } else if (after3day) {
                    if (sectionDao.findByManager(lf.getUsername()).isEmpty()) {
                        ra.addFlashAttribute("message", "success");
                    }
                }
            } else if (isSuperManager) {
                if (lf.getUser().getSectionId() != 16
                        && deanDao.findOne(lf.getUsername()) == null
                        && associateDeanDao.findOne(lf.getUsername()) == null) {
                    ra.addFlashAttribute("message", "success");
                }
            } else if (lf.getUser().getSectionId() != 16
                    && deanDao.findOne(lf.getUsername()) == null
                    && associateDeanDao.findOne(lf.getUsername()) == null
                    && lf.getUser().getSection().getManager().equals(user.getUsername())
                    && !after3day) {
                ra.addFlashAttribute("message", "success");
            }
        }

        if (ra.getFlashAttributes().get("message") == null) {
            ra.addFlashAttribute("message", "no_permission");
        }
        if (ra.getFlashAttributes().get("message").equals("success")) {
//            lf.setLeaveStatus(LeaveStatus.APRROVE);
//            leaveFormDao.save(lf);
        }
        return "redirect:/leave/list";
    }

    @RequestMapping(value = "/reject/{id}")
    public String reject(@PathVariable("id") int id, RedirectAttributes ra) {
//        LeaveForm lf = leaveFormDao.findOne(id);
//        User user = (User) session.getAttribute("user");
//
//        if (lf == null) {
//            ra.addFlashAttribute("message", "not_found");
//        } else {
//            boolean after3day = lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3));
//            if (!lf.isWait()) {
//                ra.addFlashAttribute("message", "wait_only");
//            } else if (lf.getUsername().equals(user.getUsername())) {
//                ra.addFlashAttribute("message", "no_permission");
//            } else if (after3day) {
//                if (associateDeanDao.exists(user.getUsername())) {
//                    /*
//                    lf.setLeaveStatus(LeaveStatus.REJECT);
//                    leaveFormDao.save(lf);
//                    LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(lf.getUsername(), lf.getLeaveYear(), lf.getLeaveType());
//                    lr.setAmount(lr.getAmount() + lf.getLeaveTotalDate());
//                    leaveRemainDao.save(lr);
//                     */
//                    ra.addFlashAttribute("message", "success");
//                } else {
//                    ra.addFlashAttribute("message", "no_time");
//                }
//            } else if (lf.getUser().getSection().getManager().equals(user.getUsername())) {
//                /*
//                lf.setLeaveStatus(LeaveStatus.REJECT);
//                leaveFormDao.save(lf);
//                LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(lf.getUsername(), lf.getLeaveYear(), lf.getLeaveType());
//                lr.setAmount(lr.getAmount() + lf.getLeaveTotalDate());
//                leaveRemainDao.save(lr);
//                 */
//                ra.addFlashAttribute("message", "success");
//            } else {
//                ra.addFlashAttribute("message", "no_permission");
//            }
//        }
        LeaveForm lf = leaveFormDao.findOne(id);
        User user = (User) session.getAttribute("user");
        if (lf == null) {
            ra.addFlashAttribute("message", "not_found");
        } else if (lf.getUsername().equals(user.getUsername())) {
            ra.addFlashAttribute("message", "no_permission");
        } else if (!lf.isWait()) {
            ra.addFlashAttribute("message", "wait_only");
        } else {
            String username = user.getUsername();
            boolean isDean = deanDao.findOne(username) != null;
            boolean isAssociateDean = associateDeanDao.findOne(username) != null;
            // 6 is Human resource section.
            boolean isSuperManager = sectionDao.countByManagerAndSectionId(user.getUsername(), 6) != 0;
            boolean after3day = lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3));

            if (isDean) {
                ra.addFlashAttribute("message", "success");
            } else if (isAssociateDean) {
                if (lf.getUser().getSection().getManager().equals(user.getUsername())) {
                    ra.addFlashAttribute("message", "success");
                } else if (after3day) {
                    if (sectionDao.findByManager(lf.getUsername()).isEmpty()) {
                        ra.addFlashAttribute("message", "success");
                    }
                }
            } else if (isSuperManager) {
                if (lf.getUser().getSectionId() != 16
                        && deanDao.findOne(lf.getUsername()) == null
                        && associateDeanDao.findOne(lf.getUsername()) == null) {
                    ra.addFlashAttribute("message", "success");
                }
            } else if (lf.getUser().getSectionId() != 16
                    && deanDao.findOne(lf.getUsername()) == null
                    && associateDeanDao.findOne(lf.getUsername()) == null
                    && lf.getUser().getSection().getManager().equals(user.getUsername())
                    && !after3day) {
                ra.addFlashAttribute("message", "success");
            }
        }

        if (ra.getFlashAttributes().get("message") == null) {
            ra.addFlashAttribute("message", "no_permission");
        }
        if (ra.getFlashAttributes().get("message").equals("success")) {
//            lf.setLeaveStatus(LeaveStatus.REJECT);
//            leaveFormDao.save(lf);
//            LeaveRemain lr = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(lf.getUsername(), lf.getLeaveYear(), lf.getLeaveType());
//            lr.setAmount(lr.getAmount() + lf.getLeaveTotalDate());
//            leaveRemainDao.save(lr);
        }
        return "redirect:/leave/list";
    }

    public String checkPermissionLeave(int leaveId) {
        return checkPermissionLeave(leaveId, -1);
    }

    public String checkPermissionLeave(int leaveId, int byStatus) {
        LeaveForm lf = leaveFormDao.findOne(leaveId);
        User user = (User) session.getAttribute("user");
        if (lf == null) {
            return "not_found";
        } else if (lf.getUsername().equals(user.getUsername())) {
            return "no_permission";
        } else if (byStatus != -1) {
            if (lf.getLeaveStatus() != byStatus) {
                return "status_not_match";
            }
        }
        String username = user.getUsername();
        boolean isDean = deanDao.findOne(username) != null;
        boolean isAssociateDean = associateDeanDao.findOne(username) != null;
        // 6 is Human resource section.
        boolean isSuperManager = sectionDao.countByManagerAndSectionId(user.getUsername(), 6) != 0;
        boolean after3day = lf.getLeaveCreatedAt().before(DateUtils.addDays(new Date(), -3));

        if (isDean) {
            return "success";
        } else if (isAssociateDean && lf.getUser().getSectionId() != 16) {
            if (lf.getUser().getSection().getManager().equals(user.getUsername())) {
                return "success";
            } else if (after3day) {
                if (sectionDao.findByManager(lf.getUsername()).isEmpty()) {
                    return "success";
                }
            }
        } else if (isSuperManager) {
            if (lf.getUser().getSectionId() != 16
                    && deanDao.findOne(lf.getUsername()) == null
                    && associateDeanDao.findOne(lf.getUsername()) == null) {
                return "success";
            }
        } else if (lf.getUser().getSectionId() != 16
                && deanDao.findOne(lf.getUsername()) == null
                && associateDeanDao.findOne(lf.getUsername()) == null
                && lf.getUser().getSection().getManager().equals(user.getUsername())
                && !after3day) {
            return "success";
        }

        return "no_permission";
    }
}
