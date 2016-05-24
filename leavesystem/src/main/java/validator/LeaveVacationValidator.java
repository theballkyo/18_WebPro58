/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.model.LeaveForm;
import com.pl.model.LeaveRemainDao;
import com.pl.model.User;
import com.pl.model.UserDao;
import org.springframework.validation.Errors;

/**
 *
 * @author theba
 */
public class LeaveVacationValidator extends BaseLeaveValidator {

    private final UserDao userDao;
    
    public LeaveVacationValidator(User user, LeaveRemainDao leaveRemainDao, String year, UserDao userDao) {
        super(user, leaveRemainDao, year);
        this.userDao = userDao;
    }

    @Override
    public final void validate(Object o, Errors errors) {
 
        LeaveForm lf = (LeaveForm) o;
        lf.setTimeType(1);
        
        setInfo(lf);
        
        validTime(lf, errors);
        validStartAfterWorkingDay(lf, errors, 3);
        validRemain(user, lf, errors);

        String workRepresent = lf.getWorkRepresent();
        if (!workRepresent.isEmpty() && workRepresent.length() > 1) {
            User userWorkRepresent = userDao.findByUsernameAndSectionId(workRepresent, section.getSectionId());
            if (userWorkRepresent == null) {
                errors.reject("workrepresent.null", "");
            } else {
                lf.setUserWorkRepresent(userWorkRepresent);
            }
        }
    }

}
