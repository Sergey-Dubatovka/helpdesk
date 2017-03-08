package com.pvt.controller.command;

import com.pvt.services.GamingClubService;
import com.pvt.services.NotePriorityService;
import com.pvt.services.NoteService;
import com.pvt.services.NoteStatusService;
import com.pvt.beans.*;
import com.pvt.services.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public Action execute(HttpServletRequest req) {

        List<GamingClub> gamingClubs = null;
        try {
            gamingClubs = gamingClubService.getAll(GamingClub.class);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("gamingClubs", gamingClubs);

        if (Form.isPost(req)) {
            try {
                NoteStatus openStatus = noteStatusService.get(Note.class, OPEN_ID); //status = open
                NotePriority lowPriority = notePriorityService.get(NotePriority.class, LOW_ID);//priority = low
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
