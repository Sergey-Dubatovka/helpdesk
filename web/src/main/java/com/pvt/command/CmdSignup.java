package com.pvt.command;

import com.pvt.UserRoleService;
import com.pvt.UserService;
import com.pvt.beans.User;
import com.pvt.beans.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class CmdSignup extends Action {
    private static Logger log = LoggerFactory.getLogger(CmdSignup.class);
    private Long managerId = 1L;
    private UserService userService = new UserService();
    private UserRoleService userRoleService = new UserRoleService();
    private UserRole manager = userRoleService.get(managerId);
    User newUser;

    @Override
    Action execute(HttpServletRequest req) {


        if (Form.isPost(req)) {
            String pass = req.getParameter("passwordinput");
            String email = req.getParameter("email");

            String login;
            try {
                login = Form.getString(req, "login", Patterns.LOGIN);
            } catch (Exception e) {
                log.error("Error in Form getString", e);
                req.setAttribute(Messages.msgError, "NO VALID FIELDS");
                return null;
            }

            if (login != null && pass != null && email != null) {
                log.info("creating new User");
                newUser = new User(login, pass, email, manager);
                userService.saveOrUpdate(newUser);
                req.setAttribute(Messages.msgMessage, "USER ADDED");
                return Actions.LOGIN.action;
            } else {
                req.setAttribute(Messages.msgError, "FILL ERROR");
                return null;
            }
        }
        return null;
    }
}

