package com.pvt.controller.command;


import com.pvt.services.NoteService;
import com.pvt.beans.Note;
import com.pvt.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdShowAllNotes extends Action {
    private static long countNotes;
    private static long pageCount;
    private static int pageSize = 3;
    private static int activePageIndex = 1;
    private static List<Note> openNotes;
    @Autowired
    NoteService noteService;

    @Override
    public Action execute(HttpServletRequest req) {
        try {
            countNotes = noteService.countAll();
            pageCount = (long) Math.ceil(countNotes / pageSize);
            req.getSession().setAttribute("pageCount", pageCount);

            String api = req.getParameter("activePageIndex");
            if (api == null) {
                api = Integer.toString(activePageIndex);
            }
            openNotes = noteService.getPage(activePageIndex, pageSize);

            activePageIndex = Integer.parseInt(api);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        req.setAttribute("activePageIndex", activePageIndex);
        req.setAttribute("activePageIndex", activePageIndex);
        req.setAttribute("openNotes", openNotes);

        return Actions.SHOWALLNOTES.action;
    }
}
