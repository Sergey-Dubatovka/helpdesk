package com.pvt.command;

import com.pvt.GamingClubService;
import com.pvt.NotePriorityService;
import com.pvt.NoteService;
import com.pvt.NoteStatusService;
import com.pvt.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class CmdCreateNote extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdCreateNote.class);

    @Override
    Action execute(HttpServletRequest req) {

        NoteService noteService = new NoteService();
        Note note = new Note();
        NoteStatusService noteStatusService = new NoteStatusService();
        NotePriorityService notePriorityService = new NotePriorityService();
        GamingClubService gamingClubService = new GamingClubService();

        NoteStatus status = noteStatusService.get(1); //status = open
        NotePriority priority = notePriorityService.get(1);//priority = low

        Set<GamingClub> gamingClubs = gamingClubService.getAll();
        req.setAttribute("gamingClubs", gamingClubs);

        if (Form.isPost(req)) {
            try {
                User currentUser = (User) req.getSession().getAttribute("user");
                note.setNoteId(null);
                note.setSubject(req.getParameter("subject"));
                note.setDescription(req.getParameter("description"));
                note.setUser(currentUser);
                note.setNoteStatus(status);
                note.setNotePriority(priority);
                note.setGamingClub(gamingClubService.find(req.getParameter("gamingClub")));

                LOG.info("saveOrUpdate" + note);
                if (noteService.saveOrUpdate(note) != null) {
                    req.setAttribute(Messages.msgMessage, "Ваша заявка принята");
                    return Actions.SHOWALLNOTES.action;
                } else
                    req.setAttribute(Messages.msgError, "FILL ERROR");
                return null;
            } catch (Exception e) {
                LOG.error("Error in Note Post form " + e.getMessage());
            }
        }
        return null;
    }
}
