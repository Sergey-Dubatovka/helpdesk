package com.pvt.controller.command;

import com.pvt.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CmdLogout extends Action {
    @Override
    public Action execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) req.setAttribute(Messages.msgMessage, "login");
        req.getSession();

        if (Form.isPost(req) && Integer.valueOf(req.getParameter("LogoutButton")) == 1) {
            req.getSession().invalidate();
        }
        return Actions.LOGIN.action;
    }
}
