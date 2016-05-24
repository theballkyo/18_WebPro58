/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.helper.TimeHelper;
import com.pl.leave.LeaveTimeType;
import com.pl.model.LeaveForm;
import com.pl.model.LeaveRemain;
import com.pl.model.LeaveRemainDao;
import com.pl.model.Section;
import com.pl.model.User;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author theba
 */
abstract class BaseLeaveValidator implements Validator {

    protected final LeaveRemainDao leaveRemainDao;

    protected final User user;
    protected final Section section;

    protected final String year;

    protected BaseLeaveValidator(User user, LeaveRemainDao leaveRemainDao, String year) {
        this.user = user;
        this.leaveRemainDao = leaveRemainDao;
        this.year = year;
        if (user != null) {
            this.section = user.getSection();
        } else {
            this.section = null;
        }
    }

    @Override
    public boolean supports(Class clazz) {
        return LeaveForm.class.equals(clazz);
    }

    protected void validTime(LeaveForm lf, Errors errors) {
        if (lf.getTimeType() == LeaveTimeType.FULL.value()) {
            Date leaveStartAt = lf.getLeaveStartAt();
            Date leaveEndAt = lf.getLeaveEndAt();
            if (leaveStartAt == null || leaveEndAt == null) {

            } else {
                int total = TimeHelper.countDayNoWeekEnd(lf.getLeaveStartAt(), lf.getLeaveEndAt());
                lf.setLeaveTotalDate(total);
                Date start = DateUtils.addDays(new Date(), -1);
                start = DateUtils.setHours(start, 0);
                start = DateUtils.setMinutes(start, 0);
                start = DateUtils.setSeconds(start, 0);
                start = DateUtils.setMilliseconds(start, 0);
                
                if (lf.getLeaveStartAt().getTime() < start.getTime()) {
                    errors.rejectValue("leaveStartAt", "");
                }

                if (total == -1) {
                    errors.rejectValue("leaveEndAt", "");
                }
            }
        } else {
            lf.setLeaveTotalDate(0.5);
        }
    }

    protected void validRemain(User user, LeaveForm lf, Errors errors) {
        LeaveRemain leaveRemain = leaveRemainDao.findByUsernameAndYearAndLeaveTypeId(user.getUsername(), lf.getLeaveYear(), lf.getLeaveType());

        double amount = leaveRemain.getAmount() - lf.getLeaveTotalDate();
        if (amount < 0) {
            errors.reject("amount", "Amount is too high.");
        } else {
            leaveRemain.setAmount(amount);
            lf.setLeaveRemain(leaveRemain);
        }
    }

    protected void validStartAfterDay(LeaveForm lf, Errors errors, int afterDay) {
        Date after = new Date();
        after = DateUtils.addDays(after, afterDay);
        after = DateUtils.setHours(after, 0);
        after = DateUtils.setMinutes(after, 0);
        after = DateUtils.setSeconds(after, 0);
        after = DateUtils.setMilliseconds(after, 0);
        if (lf.getLeaveStartAt() != null) {
            if (lf.getLeaveStartAt().getTime() < after.getTime()) {
                errors.reject("startFast", "");
            }
        }
    }

    protected void validStartAfterWorkingDay(LeaveForm lf, Errors errors, int afterDay) {
        Date after = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(after);

        if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            after = DateUtils.addDays(after, 2);
        } else if (c1.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            after = DateUtils.addDays(after, 1);
        }

        after = DateUtils.addDays(after, afterDay);
        after = DateUtils.setHours(after, 0);
        after = DateUtils.setMinutes(after, 0);
        after = DateUtils.setSeconds(after, 0);
        after = DateUtils.setMilliseconds(after, 0);
        if (lf.getLeaveStartAt() != null) {
            if (lf.getLeaveStartAt().getTime() < after.getTime()) {
                errors.reject("startFast", "");
            }
        }
    }
    
    protected void setInfo(LeaveForm lf) {
        lf.setUser(user);
        lf.setSection(section);
        lf.setLeaveYear(year);
    }
}
