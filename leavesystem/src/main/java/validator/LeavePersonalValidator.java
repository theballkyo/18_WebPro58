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
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

/**
 *
 * @author theba
 */
public class LeavePersonalValidator extends BaseLeaveValidator {

    public LeavePersonalValidator(User user, LeaveRemainDao leaveRemainDao, String year) {
        super(user, leaveRemainDao, year);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LeaveForm lf = (LeaveForm) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "reason", "reason.required", "Reason is required.");
        ValidationUtils.rejectIfEmpty(errors, "leaveStartAt", "startat.required", "");
        ValidationUtils.rejectIfEmpty(errors, "leaveEndAt", "endAt.required", "");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "leaveContact", "contact.required", "");

        setInfo(lf);
        
        validTime(lf, errors);
        validStartAfterWorkingDay(lf, errors, -1);
        validRemain(user, lf, errors);
    }
    
}
