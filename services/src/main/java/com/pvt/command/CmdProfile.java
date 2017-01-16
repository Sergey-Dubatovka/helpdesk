package com.pvt.command;


import com.pvt.beans.*;
import com.pvt.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdProfile extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        if (req.getSession(false) != null) {
            DAO dao = DAO.getDAO();
            User user = (User) req.getSession().getAttribute("user");

            List<Ad> ads = dao.ad.getAll("where fk_user=" + user.getID() + ";");
            if (ads.size() > 0) {
                req.setAttribute("ads", ads);
            }
            List<Status> statuses = dao.status.getAll("");
            List<Priority> priorities = dao.priority.getAll("");
            List<Objects> objectses = dao.objects.getAll("");
            List<User> usersList = dao.user.getAll("");
            req.getSession().setAttribute("statuses", statuses);
            req.getSession().setAttribute("priorities", priorities);
            req.getSession().setAttribute("objectses", objectses);
            //   req.getSession().setAttribute("users", usersList);

            if (Form.isPost(req)) {
                Ad ad = new Ad();
                ad.setId(Integer.valueOf(req.getParameter("ID")));
                ad.setSubject(req.getParameter("subject"));
                ad.setDescription(req.getParameter("description"));
                int stat = Integer.valueOf(req.getParameter("fk_status"));
                ad.setFk_status(stat);
                if (stat != 3) {
                    ad.setFk_user(user.getID());
                } else {
                    ad.setFk_user(1);
                }
                ad.setFk_zia(Integer.valueOf(req.getParameter("fk_zia")));
                ad.setFk_priority(Integer.valueOf(req.getParameter("fk_priority")));
                req.setAttribute(Messages.msgMessage, ad);
                if (dao.ad.update(ad)) {
                    return Actions.PROFILE.action;
                }
            }
            return null;
        } else return Actions.LOGIN.action;
    }
}