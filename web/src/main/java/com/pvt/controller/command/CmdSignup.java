package com.pvt.controller.command;

import com.pvt.services.UserRoleService;
import com.pvt.services.UserService;
import com.pvt.beans.User;
import com.pvt.beans.UserRole;
import com.pvt.services.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class CmdSignup extends Action {
    private static Logger log = LoggerFactory.getLogger(CmdSignup.class);

    @Autowired
    UserService userService;

    @Autowired
    UserRoleService userRoleService;


    private User newUser;

    @Override
    public Action execute(HttpServletRequest req) {

        if (Form.isPost(req)) {
            saveNewUser(req);
            req.setAttribute(Messages.msgMessage, "USER ADDED");
            return Actions.LOGIN.action;
        } else {
            req.setAttribute(Messages.msgError, "FILL ERROR");
            return Actions.SIGNUP.action;
        }
    }

    /**
     * Save new user with role = Manager
     * Only Director can change User`s role.
     *
     * @param req
     */
    private void saveNewUser(HttpServletRequest req) {
        String pass = req.getParameter("passwordinput");
        String email = req.getParameter("email");

        String login = null;
        try {
            login = Form.getString(req, "login", Patterns.LOGIN);
        } catch (Exception e) {
            log.error("Error in Form getString", e);
            req.setAttribute(Messages.msgError, "NO VALID FIELDS");
        }

        if (login != null && pass != null && email != null) {
            log.info("creating new User");
            newUser = new User(login, pass, email, getManager());
            try {
                userService.saveOrUpdate(newUser);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @return User role = MANAGER
     */
    private UserRole getManager() {
        Long managerId = 1L;
        try {
            return userRoleService.get(UserRole.class, managerId);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

}

