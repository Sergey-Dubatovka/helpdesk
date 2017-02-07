package com.pvt.command;

import com.pvt.UserService;
import com.pvt.beans.Note;
import com.pvt.beans.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class CmdProfile extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        if (req.getSession(false) != null) {
            UserService userService = new UserService();
            User sessionUser = (User) req.getSession().getAttribute("user");
            Set<Note> notes = sessionUser.getNotes();
            if (notes.size() > 0) {
                req.setAttribute("notes", notes);
            }


            //DAO dao = DAO.getDAO();
//            User user = (User) req.getSession().getAttribute("user");
//
//            List<Note> ads = dao.ad.getAll("where fk_user=" + user.getID() + ";");
//            if (ads.size() > 0) {
//                req.setAttribute("ads", ads);
//            }
//            List<NoteStatus> statuses = dao.status.getAll("");
//            List<NotePriority> priorities = dao.priority.getAll("");
//            List<GamingClub> objectses = dao.objects.getAll("");
//            List<User> usersList = dao.user.getAll("");
//            req.getSession().setAttribute("statuses", statuses);
//            req.getSession().setAttribute("priorities", priorities);
//            req.getSession().setAttribute("objectses", objectses);
//            //   req.getSession().setAttribute("users", usersList);
//
//            if (Form.isPost(req)) {
//                Note note = new Note();
//                note.setNoteId(Integer.valueOf(req.getParameter("ID")));
//                note.setSubject(req.getParameter("subject"));
//                note.setDescription(req.getParameter("description"));
//                int stat = Integer.valueOf(req.getParameter("fk_status"));
//                note.setFk_status(stat);
//                if (stat != 3) {
//                    note.setFk_user(user.getID());
//                } else {
//                    note.setFk_user(1);
//                }
//                note.setFk_zia(Integer.valueOf(req.getParameter("fk_zia")));
//                note.setFk_priority(Integer.valueOf(req.getParameter("fk_priority")));
//                req.setAttribute(Messages.msgMessage, note);
//                if (dao.ad.update(note)) {
//                    return Actions.PROFILE.action;
//                }
//            }
            return null;
        } else return Actions.LOGIN.action;
    }
}