/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.model.LeaveForm;
import com.pl.model.LeaveRemainDao;
import com.pl.model.Section;
import com.pl.model.User;
import javax.servlet.http.HttpSession;
import org.springframework.validation.Errors;

/**
 *
 * @author theba
 */
public class LeaveWifeValidator extends BaseLeaveValidator {

    public LeaveWifeValidator(User user, LeaveRemainDao leaveRemainDao, String year) {
        super(user, leaveRemainDao, year);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LeaveForm lf = (LeaveForm) o;
        
        setInfo(lf);
        
        validTime(lf, errors);
        validStartAfterDay(lf, errors, 0);
        validRemain(user, lf, errors);
        
    }
    
}
