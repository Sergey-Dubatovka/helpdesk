package com.pvt.command;

import com.pvt.beans.User;
import com.pvt.dao.DAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class CmdSignup extends Action {
    private static Logger log = Logger.getLogger(CmdSignup.class);
    @Override
    Action execute(HttpServletRequest req) {

        User user = new User();
        try {
            user.setID(0);
            user.setLogin(Form.getString(req, "login", Patterns.LOGIN));
            user.setPassword(req.getParameter("passwordinput"));
            user.setEmail(req.getParameter("email"));
            user.setFk_role(1);

        } catch (Exception e) {
            log.error(e);
            req.setAttribute(Messages.msgError, "NO VALID FIELDS");
            return null;
        }
        DAO dao = DAO.getDAO();
        if (user.getLogin() != null && user.getPassword() != null && dao.user.create(user)) {
            req.setAttribute(Messages.msgMessage, "USER ADDED");
            return Actions.LOGIN.action;
        } else
            req.setAttribute(Messages.msgError, "FILL ERROR");
        //return Actions.SIGNUP.action;
        return null;
    }
}
