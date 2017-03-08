package com.pvt.controller.command;

import com.pvt.services.UserRoleService;
import com.pvt.services.UserService;
import com.pvt.beans.User;
import com.pvt.beans.UserRole;
import com.pvt.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class CmdShowUsers extends Action {
    User loggedUser;
    User updatedUser = new User();

    @Autowired
    UserRoleService userRoleService;
    @Autowired
    UserService userService;

    @Override
    public Action execute(HttpServletRequest req) {

        if (req.getSession(false) == null) {
            return Actions.LOGIN.action;
        }

        loggedUser = (User) req.getSession().getAttribute("loggedUser");

        if (NotDirector(loggedUser)) {
            req.setAttribute("userses", allUsersList());
            req.setAttribute("roles", allRolesList());
            return Actions.SHOWUSERS_OLD.action;
        }

        if (Form.isPost(req)) {
            updatedUser = updateUser(req);
            editUser(updatedUser);
            req.setAttribute(Messages.msgMessage, updatedUser);
        }

        req.setAttribute("setOfUsers", allUsersList());
        req.setAttribute("roles", allRolesList());
        return Actions.SHOWUSERS.action;
    }


    /**
     * delete or update user
     *
     * @param user
     */
    private void editUser(User user) {
        try {
            if (user.getUserId() != null && user.getUserId() < 0) {
                user.setUserId(user.getUserId() * (-1));
                userService.delete(user);
            } else {
                userService.saveOrUpdate(user);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * check user role not equals DIRECTOR
     *
     * @return boolean
     */
    private boolean NotDirector(User user) {
        return user != null && !user.getUserRole().getRoleName().equals("Director");
    }

    /**
     * @param req HttpServletRequest
     * @return updated user
     */
    private User updateUser(HttpServletRequest req) {
        try {
            // updatedUser.setUserId(Form.getLong(req, "ID"));
            String loginFromForm = Form.getString(req, "Login", Patterns.LOGIN);
            String passFromForm = Form.getString(req, "Password", Patterns.PASSWORD);
            String emailFromForm = Form.getString(req, "Email", Patterns.EMAIL);
            String userRoleFromJsp = req.getParameter("userRole");
            Long userIdFromJsp = Form.getLong(req, "userId");
            UserRole ur = null;
            try {
                ur = userRoleService.find(userRoleFromJsp);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            updatedUser.setUserId(userIdFromJsp);
            updatedUser.setLogin(loginFromForm);
            updatedUser.setPassword(passFromForm);
            updatedUser.setEmail(emailFromForm);
            updatedUser.setUserRole(ur);
            return updatedUser;
        } catch (ParseException e) {
            e.printStackTrace();
            req.setAttribute(Messages.msgMessage, "Ошибка!!");
        }
        return null;
    }

    /**
     * @return List of all users
     */
    private List allUsersList() {
        try {
            return userService.getAll(User.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * List of all roles
     *
     * @return
     */
    private List allRolesList() {
        try {
            return userRoleService.getAll(UserRole.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return null;
    }
}