/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validator;

import com.pl.model.User;
import com.pl.model.UserDao;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author theba
 */
@Component
@Scope("request")
public class UserLoginValidator implements Validator {

    private User user;

    @Override
    public boolean supports(Class clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        user = (User) o;
        if (user.getUsername().isEmpty() || user.getUsername().equalsIgnoreCase("")) {
            errors.reject("username.required", "");
        }
        if (user.getPassword().isEmpty() || user.getPassword().equalsIgnoreCase("")) {
            errors.reject("password.required", "");
        }
    }

    public void login(User user, Errors errors, UserDao userDao) {
        validate(user, errors);
        this.user = userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (this.user == null) {
            errors.reject("login.invalid", "");
        } else {
            Hibernate.initialize(this.user.getSection());
        }
    }

    public User getUser() {
        return user;
    }
}
