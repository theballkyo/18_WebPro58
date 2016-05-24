/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.model.LeaveForm;
import com.pl.model.LeaveRemainDao;
import com.pl.model.User;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author theba
 */
public class LeaveSickValidator extends BaseLeaveValidator {

    public LeaveSickValidator(User user, LeaveRemainDao leaveRemainDao, String year) {
        super(user, leaveRemainDao, year);
    }

    @Override
    public void validate(Object o, Errors errors) {
        try {
            LeaveForm lf = (LeaveForm) o;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "reason.required", "Reason is required.");
            ValidationUtils.rejectIfEmpty(errors, "leaveStartAt", "startat.required", "");
            ValidationUtils.rejectIfEmpty(errors, "leaveEndAt", "endAt.required", "");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveContact", "contact.required", "");

            setInfo(lf);

            validTime(lf, errors);
            validStartAfterWorkingDay(lf, errors, -1);
            validRemain(user, lf, errors);
        } catch (Exception e) {

        }
    }

}
