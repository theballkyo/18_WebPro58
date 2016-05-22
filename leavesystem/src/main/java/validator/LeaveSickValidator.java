/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.helper.TimeHelper;
import com.pl.leave.LeaveTimeType;
import com.pl.model.LeaveForm;
import java.util.Date;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author theba
 */
public class LeaveSickValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {
        return LeaveForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LeaveForm lf = (LeaveForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "reason.required", "Reason is required.");
        ValidationUtils.rejectIfEmpty(errors, "leaveStartAt", "startat.required", "");
        ValidationUtils.rejectIfEmpty(errors, "leaveEndAt", "endAt.required", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveContact", "contact.required", "");
        if (lf.getTimeType() == LeaveTimeType.FULL.value()) {
            Date leaveStartAt = lf.getLeaveStartAt();
            Date leaveEndAt = lf.getLeaveEndAt();
            if (leaveStartAt == null || leaveEndAt == null) {
                // errors.rejectValue("leaveAt", "");
            } else {
                int total = TimeHelper.countDayNoWeekEnd(lf.getLeaveStartAt(), lf.getLeaveEndAt());
                Date start = DateUtils.addDays(new Date(), -1);
                start = DateUtils.setHours(start, 0);
                start = DateUtils.setMinutes(start, 0);
                start = DateUtils.setSeconds(start, 0);

                if (lf.getLeaveStartAt().before(start)) {
                    errors.rejectValue("leaveStartAt", "");
                }

                if (total == -1) {
                    errors.rejectValue("leaveEndAt", "");
                }
            }
        }
        if (lf.getTimeType() == LeaveTimeType.AFTERNOON.value() || lf.getTimeType() == LeaveTimeType.MORNING.value()) {

        }
    }

}
