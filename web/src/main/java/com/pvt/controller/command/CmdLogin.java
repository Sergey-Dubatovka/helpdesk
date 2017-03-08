package com.pvt.controller.command;

import com.pvt.services.UserService;
import com.pvt.beans.User;
import com.pvt.services.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CmdLogin extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdLogin.class);

    @Autowired
    UserService userService;

    @Override
    public Action execute(HttpServletRequest req) {
        User userFromJsp = new User();
        if (Form.isPost(req)) {
            try {
                LOG.info("Post Form loggedUser");
                userFromJsp.setLogin(Form.getString(req, "Login", Patterns.LOGIN));
                userFromJsp.setPassword(Form.getString(req, "Password", Patterns.PASSWORD));
            } catch (Exception e) {
                LOG.error(e.getMessage());
                req.setAttribute(Messages.msgError, "NO VALID FIELDS");
                return null;
            }

            User loggedUser = null;
            try {
                loggedUser = userService.find(userFromJsp.getLogin());
            } catch (ServiceException e) {
                e.printStackTrace();
            }

            if (loggedUser != null) {
                if (loggedUser.getPassword().equals(userFromJsp.getPassword())) {

                    HttpSession session = req.getSession();
                    session.setAttribute("loggedUser", loggedUser);
                    session.setAttribute("userRole", loggedUser.getUserRole());
                    session.setMaxInactiveInterval(60 * 5);
                    return Actions.PROFILE.action;
                } else req.setAttribute(Messages.msgError, "NO SUCH USER");
            }

        }
        return null;
    }
}
