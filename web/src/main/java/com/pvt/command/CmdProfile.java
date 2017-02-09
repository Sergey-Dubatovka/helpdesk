package com.pvt.command;

import com.pvt.GamingClubService;
import com.pvt.NotePriorityService;
import com.pvt.NoteService;
import com.pvt.NoteStatusService;
import com.pvt.beans.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class CmdProfile extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        if (req.getSession(false) != null) {
            User sessionUser = (User) req.getSession().getAttribute("user");

            NotePriorityService nps = NotePriorityService.getService();
            NoteStatusService nss = NoteStatusService.getService();
            NoteService ns = NoteService.getService();
            GamingClubService gamingClubService = GamingClubService.getService();

            Set<Note> notes = sessionUser.getNotes();

//Collections for selectButton on JSP profile.jsp

            Set<GamingClub> gamingClubs = gamingClubService.getAll();
            Set<NoteStatus> statuses = nss.getAll();
            Set<NotePriority> priorities = nps.getAll();


            if (notes.size() > 0) {
                req.setAttribute("notes", notes);

                req.getSession().setAttribute("gamingClubs", gamingClubs);
                req.getSession().setAttribute("statuses", statuses);
                req.getSession().setAttribute("priorities", priorities);
            }
// if Form isPost >> update note
            if (Form.isPost(req)) {
                Note note = new Note();
                note.setNoteId(Long.valueOf(req.getParameter("ID")));
                note.setSubject(req.getParameter("subject"));
                note.setDescription(req.getParameter("description"));
// upd Status
                String newNoteStatus = req.getParameter("noteStatus");
                for (NoteStatus noteStatus : statuses) {
                    if (noteStatus.getStatusName().equals(newNoteStatus)) {
                        note.setNoteStatus(noteStatus);
                        break;
                    }
                }
//upd user
// if note status is resolved: >> "delete" note from sessionUser and set note for User.login "ResolvedNotes"
                if (newNoteStatus != "resolved") {
                    note.setUser(sessionUser);
                } else {
                    User userForDeletedNotes = new User("ResolvedNotes", "123", null, null);
                    note.setUser(userForDeletedNotes);
                }
//upd GamingClub
                String clubFromJsp = req.getParameter("gamingClub");
                for (GamingClub gc : gamingClubs) {
                    if (gc.getGamingClubName().equals(clubFromJsp)) {
                        note.setGamingClub(gc);
                        break;
                    }
                }
//upd priority
                String notePriorityFromJsp = req.getParameter("notePriority");
                for (NotePriority pr : priorities) {
                    if (pr.getPriorityName().equals(notePriorityFromJsp)) {
                        note.setNotePriority(pr);
                        break;
                    }
                }
//debug message
                req.setAttribute(Messages.msgMessage, note);
//upd note in DB
                ns.saveOrUpdate(note);
                return Actions.PROFILE.action;

            }
            return null;
        } else return Actions.LOGIN.action;
    }
}