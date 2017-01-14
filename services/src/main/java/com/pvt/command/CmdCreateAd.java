package com.pvt.command;


import com.pvt.beans.Ad;
import com.pvt.beans.Objects;
import com.pvt.beans.User;
import com.pvt.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class CmdCreateAd extends Action {
    @Override
    Action execute(HttpServletRequest req) {
//        HttpSession session=req.getSession();
//        try{
//            if () {
//                session.invalidate();
//                return Actions.LOGIN.action;
//            }
        Ad ad = new Ad();
        List<Objects> objects = DAO.getDAO().objects.getAll("");
        req.setAttribute("objects", objects);

        if (Form.isPost(req)) {
            try {
                User user = (User) req.getSession().getAttribute("user");
                ad.setId(0);
                ad.setSubject(req.getParameter("subject"));
                ad.setDescription(req.getParameter("description"));
                ad.setFk_user(user.getID());
                ad.setFk_status(1);
                ad.setFk_priority(1);
                ad.setFk_zia(Form.getInt(req,"objects"));

                DAO dao = DAO.getDAO();
                if (dao.ad.create(ad)) {
                    req.setAttribute(Messages.msgMessage, "Ваша заявка принята");
                    return Actions.SHOWALLADS.action;
                } else
                    req.setAttribute(Messages.msgError, "FILL ERROR");
                return null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
