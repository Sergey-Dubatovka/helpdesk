package com.pvt.command;

import com.pvt.GamingClubService;
import com.pvt.NotePriorityService;
import com.pvt.NoteService;
import com.pvt.NoteStatusService;
import com.pvt.beans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

public class CmdCreateNote extends Action {
    private static final Logger LOG = LoggerFactory.getLogger(CmdCreateNote.class);
    private static final Long OPEN_ID = 1L;
    private static final Long LOW_ID = 1L;

    private NoteService noteService = new NoteService();
    private NoteStatusService noteStatusService = new NoteStatusService();
    private NotePriorityService notePriorityService = new NotePriorityService();
    private GamingClubService gamingClubService = new GamingClubService();
    private Note note;

    @Override
    Action execute(HttpServletRequest req) {

        Set<GamingClub> gamingClubs = gamingClubService.getAll();
        req.setAttribute("gamingClubs", gamingClubs);

        if (Form.isPost(req)) {
            try {
                NoteStatus openStatus = noteStatusService.get(OPEN_ID); //status = open
                NotePriority lowPriority = notePriorityService.get(LOW_ID);//priority = low
                User currentUser = (User) req.getSession().getAttribute("loggedUser");

                String subject = req.getParameter("subject");
                String description = req.getParameter("description");
                String club = req.getParameter("gamingClub");
                GamingClub gamingClub = gamingClubService.find(club);

                note = new Note(subject, description, currentUser,
                        lowPriority, openStatus, gamingClub);

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
