package com.pvt.command;

import com.pvt.UserService;
import com.pvt.beans.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdLogin extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdLogin.class);

    @Override
    Action execute(HttpServletRequest req) {
        User loggedUser = new User();
        if (Form.isPost(req)) {
            try {
                LOG.info("Post Form loggedUser");
                loggedUser.setLogin(Form.getString(req, "Login", Patterns.LOGIN));
                loggedUser.setPassword(Form.getString(req, "Password", Patterns.PASSWORD));

            } catch (Exception e) {
                LOG.error(e.getMessage());
                req.setAttribute(Messages.msgError, "NO VALID FIELDS");
                return null;
            }
            UserService userService = new UserService();
            //
            List<User> users = userService.find(loggedUser.getLogin());


            if (users.size() > 0) {
                if (users.get(0).getPassword().equals(loggedUser.getPassword())) {
                    loggedUser = users.get(0);
                    HttpSession session = req.getSession();
                    session.setAttribute("user", loggedUser);
                    session.setAttribute("userRole", loggedUser.getUserRole());
                    session.setMaxInactiveInterval(60 * 5);
                    return Actions.PROFILE.action;
                } else req.setAttribute(Messages.msgError, "NO SUCH USER");
            }

        }return null;
    }
}

