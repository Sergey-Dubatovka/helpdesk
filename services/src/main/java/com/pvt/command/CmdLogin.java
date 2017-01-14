package com.pvt.command;

import com.pvt.beans.User;
import com.pvt.dao.DAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CmdLogin extends Action {
    private static Logger log = Logger.getLogger(CmdLogin.class);
    @Override
    Action execute(HttpServletRequest req) {
        User user = new User();
        if (Form.isPost(req)) {
            try {
                user.setLogin(Form.getString(req, "Login", Patterns.LOGIN));
                user.setPassword(Form.getString(req, "Password", Patterns.PASSWORD));
            } catch (Exception e) {
                log.error(e);
                req.setAttribute(Messages.msgError, "NO VALID FIELDS");
                return null;
            }
            DAO dao = DAO.getDAO();
            List<User> users = dao.user.getAll(String.format("where login='%s' and password='%s'",
                    user.getLogin(), user.getPassword()
            ));

            if (users.size() > 0) {
                user = users.get(0);

                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userRole", user.getFk_role());
                session.setMaxInactiveInterval(60*5);
                return Actions.PROFILE.action;
            }
            req.setAttribute(Messages.msgError, "NO SUCH USER");

        }
        return null;
    }
}

