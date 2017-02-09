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
    private Integer managerId = 1;

    @Override
    Action execute(HttpServletRequest req) {
        User newUser;

        if (Form.isPost(req)) {
            UserService userService = new UserService();

            UserRoleService userRoleService = new UserRoleService();
            UserRole manager = userRoleService.get(managerId);

            String pass = req.getParameter("passwordinput");
            String email = req.getParameter("email");

            try {
                String login = Form.getString(req, "login", Patterns.LOGIN);

                newUser = new User(login, pass, email, manager);

            } catch (Exception e) {
                log.error("", e);
                req.setAttribute(Messages.msgError, "NO VALID FIELDS");
                return null;
            }
            if (newUser != null && pass != null && email != null) {
                log.info("creating new User");
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

