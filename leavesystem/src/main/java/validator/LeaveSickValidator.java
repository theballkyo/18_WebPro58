/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.leave.LeaveTimeType;
import com.pl.model.LeaveForm;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author theba
 */
public class LeaveSickValidator implements Validator{

    @Override
    public boolean supports(Class clazz) {
        return LeaveForm.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LeaveForm lf = (LeaveForm) o;
        if (lf.getReason() == null || lf.getReason().equals("")) {
            errors.rejectValue("reason", "Reason is null value.");
        }
        if (lf.getTimeType() == 0) {
            errors.rejectValue("type", "");
        }
        if (lf.getTimeType() == LeaveTimeType.FULL.value()) {
            
        }
        if (lf.getTimeType() == LeaveTimeType.AFTERNOON.value() || lf.getTimeType() == LeaveTimeType.MORNING.value()) {
           
        }
    }
    
}
