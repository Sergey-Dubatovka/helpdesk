package com.pvt.controller.command;


import com.pvt.NoteService;
import com.pvt.beans.Note;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CmdShowAllNotes extends Action {
    private static long countNotes;
    private static long pageCount;
    private static int pageSize = 3;
    private static int activePageIndex = 1;
    private static List<Note> openNotes;
    private static NoteService noteService = NoteService.getService();

    @Override
    Action execute(HttpServletRequest req) {
        countNotes = noteService.countAll();
        pageCount = (long) Math.ceil(countNotes / pageSize);
        req.getSession().setAttribute("pageCount", pageCount);

        String api = req.getParameter("activePageIndex");

        if (api == null) {
            api = Integer.toString(activePageIndex);
        }

        openNotes = noteService.getPage(activePageIndex, pageSize);
        activePageIndex = Integer.parseInt(api);

        req.setAttribute("activePageIndex", activePageIndex);
        req.setAttribute("activePageIndex", activePageIndex);
        req.setAttribute("openNotes", openNotes);

        return Actions.SHOWALLNOTES.action;
    }
}
