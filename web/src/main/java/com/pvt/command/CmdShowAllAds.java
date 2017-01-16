package com.pvt.command;


import com.pvt.beans.*;
import com.pvt.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class CmdShowAllAds extends Action {
    @Override
    Action execute(HttpServletRequest req) {
        DAO dao = DAO.getDAO();
        List<Ad> openAdsList = dao.ad.getOpen();
        List<Ad> allAds = dao.ad.getAll("");
        List<User>users=dao.user.getAll("");
        req.setAttribute("adsList", openAdsList);
        req.setAttribute("users",users);

        if (Form.isPost(req) && Integer.valueOf(req.getParameter("LogoutButton")) == 1) {
            req.setAttribute("adsList", allAds);
        } else if (Form.isPost(req)) req.setAttribute("adsList", openAdsList);

        return Actions.SHOWALLADS.action;
    }
}
