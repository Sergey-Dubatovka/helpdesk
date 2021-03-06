package com.pvt.command;

import com.pvt.beans.IRole;
import com.pvt.beans.User;
import com.pvt.dao.DAO;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;


public class CmdShowUsers extends Action {

    private String debugOut(List<User> users) {
        StringBuilder out = new StringBuilder();
        for (User user : users) {
            out.append(user.toString()).append("<br>");
        }
        return out.toString();
    }

    @Override
    Action execute(HttpServletRequest req) {
        if (req.getSession(false) != null) {

            User userSession = (User) req.getSession().getAttribute("user");

            DAO dao = DAO.getDAO();
            if (userSession.getFk_role() == IRole.roleDirector) {
                if (Form.isPost(req)) {
                    User user = new User();
                    try {
                        user.setID(Form.getInt(req, "ID"));
                        User userRead = dao.user.read(user.getID());
                        user.setLogin(Form.getString(req, "Login", Patterns.LOGIN));
                        user.setPassword(Form.getString(req, "Password", Patterns.PASSWORD));
                        user.setEmail(Form.getString(req, "Email", Patterns.EMAIL));
                        user.setFk_role(Form.getInt(req, "fk_Role"));

                        req.setAttribute(Messages.msgMessage, user);
                        if (user.getID() > 0) {
                            dao.user.update(user);
                        }
                        if (user.getID() < 0) {
                            user.setID(user.getID() * (-1));
                            dao.user.delete(user);
                        }
                        if (user.getID() == 0) {
                            dao.user.create(user);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                        req.setAttribute(Messages.msgMessage, "Ошибка!!");
                    }
                }
                req.setAttribute("users", dao.user.getAll(""));
                req.setAttribute("roles", dao.role.getAll(""));
                return Actions.SHOWUSERS.action;
            } else {
                req.setAttribute("userses", dao.user.getAll(""));
                req.setAttribute("roles", dao.role.getAll(""));
                return Actions.SHOWUSERS_OLD.action;
            }


        } else return Actions.LOGIN.action;
    }
}