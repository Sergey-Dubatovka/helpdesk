package com.pvt.command;


import com.pvt.UserRoleService;
import com.pvt.UserService;
import com.pvt.beans.User;
import com.pvt.beans.UserRole;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;


public class CmdShowUsers extends Action {
    UserRoleService urs = UserRoleService.getService();
    UserService us = UserService.getService();

    //  Set<UserRole> roles = urs.getAll();


    @Override
    Action execute(HttpServletRequest req) {

        if (req.getSession(false) != null) {
            User loggedUser = (User) req.getSession().getAttribute("loggedUser");

            if (loggedUser.getUserRole().getRoleName().equals("Director")) {
                if (Form.isPost(req)) {
                    User updatedUser = new User();
                    try {
                        //           updatedUser.setUserId(Form.getLong(req, "ID"));
                        String loginFromForm = Form.getString(req, "Login", Patterns.LOGIN);
                        String passFromForm = Form.getString(req, "Password", Patterns.PASSWORD);
                        String emailFromForm = Form.getString(req, "Email", Patterns.EMAIL);
                        String userRoleFromJsp = req.getParameter("userRole");
                        Long userIdFromJsp = Form.getLong(req,"userId");
                        UserRole ur = urs.find(userRoleFromJsp);

                        updatedUser.setUserId(userIdFromJsp);
                        updatedUser.setLogin(loginFromForm);
                        updatedUser.setPassword(passFromForm);
                        updatedUser.setEmail(emailFromForm);

                        updatedUser.setUserRole(ur);
//                        for (UserRole userRole : roles) {
//                            if (userRoleFromJsp.equals(userRole.getRoleName())) {
//                                updatedUser.setUserRole(userRole);
//                                break;
//                            }
                        //}

                        req.setAttribute(Messages.msgMessage, updatedUser);

                        if (updatedUser.getUserId() >= 0) {
                            us.saveOrUpdate(updatedUser);
                        }
                        if (updatedUser.getUserId() != null && updatedUser.getUserId() < 0) {
                            updatedUser.setUserId(updatedUser.getUserId() * (-1));
                            us.delete(updatedUser);
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                        req.setAttribute(Messages.msgMessage, "Ошибка!!");
                    }
                }
                Set<User> userSet = us.getAll();
                Set<UserRole> userRoleSet = urs.getAll();
                req.setAttribute("setOfUsers", userSet);
                req.setAttribute("roles", userRoleSet);
                return Actions.SHOWUSERS.action;
            } else {
                Set<User> userSet = us.getAll();
                Set<UserRole> userRoleSet = urs.getAll();
                req.setAttribute("userses", userSet);
                req.setAttribute("roles", userRoleSet);
                return Actions.SHOWUSERS_OLD.action;
            }


        } /*else*/
        return Actions.LOGIN.action;
    }
}