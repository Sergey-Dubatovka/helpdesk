package com.pvt.controller.command;

import com.pvt.*;
import com.pvt.beans.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class CmdProfile extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        NotePriorityService nps = NotePriorityService.getService();
        NoteStatusService nss = NoteStatusService.getService();
        NoteService ns = NoteService.getService();
        GamingClubService gamingClubService = GamingClubService.getService();
        User sessionUser;

        if (req.getSession(false) != null) {
            sessionUser = (User) req.getSession().getAttribute("loggedUser");

// if Form isPost >> update note
            if (Form.isPost(req)) {
                Note note = new Note();
                note.setNoteId(Long.valueOf(req.getParameter("ID")));
                note.setSubject(req.getParameter("subject"));
                note.setDescription(req.getParameter("description"));
// upd Status
                String newNoteStatus = req.getParameter("noteStatus");
                NoteStatus noteStatus = nss.find(newNoteStatus);
                note.setNoteStatus(noteStatus);
//upd user
// if note status is resolved: >> "delete" note from sessionUser and set note for User.login "ResolvedNotes"
                if (!newNoteStatus.equals("resolved")) {
                    note.setUser(sessionUser);
                } else {
                    UserService us = UserService.getService();
                    Long idUserForDeletedNotes = 1L;
                    User userForDeletedNotes = us.get(idUserForDeletedNotes);
                    note.setUser(userForDeletedNotes);
                }
//upd GamingClub
                String clubFromJsp = req.getParameter("gamingClub");
                GamingClub gc = gamingClubService.find(clubFromJsp);
                note.setGamingClub(gc);

//upd priority
                String notePriorityFromJsp = req.getParameter("notePriority");
                NotePriority pr = nps.find(notePriorityFromJsp);
                note.setNotePriority(pr);

                //debug message
                req.setAttribute(Messages.msgMessage, note);
//upd note in DB
                ns.saveOrUpdate(note);
                return Actions.PROFILE.action;

            }
            //Collections for selectButton on JSP profile.jsp

            Set<GamingClub> gamingClubs = gamingClubService.getAll();
            Set<NoteStatus> statuses = nss.getAll();
            Set<NotePriority> priorities = nps.getAll();
            List<Note> notes = ns.findUserNotes(sessionUser.getUserId());

            if (notes.size() > 0) {
                req.setAttribute("notes", notes);

                req.getSession().setAttribute("gamingClubs", gamingClubs);
                req.getSession().setAttribute("statuses", statuses);
                req.getSession().setAttribute("priorities", priorities);
            }
            return null;
        } else return Actions.LOGIN.action;
    }
}