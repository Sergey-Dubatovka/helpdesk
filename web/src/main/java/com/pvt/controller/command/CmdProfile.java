package com.pvt.controller.command;

import com.pvt.beans.*;
import com.pvt.services.*;
import com.pvt.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdProfile extends Action {

    @Autowired
    NotePriorityService notePriorityService;

    @Autowired
    NoteStatusService noteStatusService;

    @Autowired
    NoteService noteService;

    @Autowired
    GamingClubService gamingClubService;

    @Autowired
    UserService userService;


    @Override
    public Action execute(HttpServletRequest req) {

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
                NoteStatus noteStatus = null;
                try {
                    noteStatus = noteStatusService.find(newNoteStatus);

                    note.setNoteStatus(noteStatus);
//upd user
// if note status is resolved: >> "delete" note from sessionUser and set note for User.login "ResolvedNotes"
                    if (!newNoteStatus.equals("resolved")) {
                        note.setUser(sessionUser);
                    } else {
                        Long idUserForDeletedNotes = 1L;
                        User userForDeletedNotes = userService.get(User.class, idUserForDeletedNotes);

                        note.setUser(userForDeletedNotes);
                    }
//upd GamingClub
                    String clubFromJsp = req.getParameter("gamingClub");
                    GamingClub gc = gamingClubService.find(clubFromJsp);
                    note.setGamingClub(gc);

//upd priority
                    String notePriorityFromJsp = req.getParameter("notePriority");
                    NotePriority pr = notePriorityService.find(notePriorityFromJsp);
                    note.setNotePriority(pr);

                    //debug message
                    req.setAttribute(Messages.msgMessage, note);
//upd note in DB
                    noteService.saveOrUpdate(note);

                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                return Actions.PROFILE.action;

            }
            //Collections for selectButton on JSP profile.jsp

            List<GamingClub> gamingClubs = null;
            try {
                gamingClubs = gamingClubService.getAll(GamingClub.class);

                List<NoteStatus> statuses = noteStatusService.getAll(NoteStatus.class);
                List<NotePriority> priorities = notePriorityService.getAll(NotePriority.class);
                List<Note> notes = noteService.findUserNotes(sessionUser.getUserId());

                if (notes.size() > 0) {
                    req.setAttribute("notes", notes);

                    req.getSession().setAttribute("gamingClubs", gamingClubs);
                    req.getSession().setAttribute("statuses", statuses);
                    req.getSession().setAttribute("priorities", priorities);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            return null;
        } else return Actions.LOGIN.action;
    }
}