package com.pvt.command;


import com.pvt.NoteService;
import com.pvt.beans.Note;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class CmdShowAllNotes extends Action {
    @Override
    Action execute(HttpServletRequest req) {

        NoteService noteService = NoteService.getService();
        List<Note> openNotes = noteService.getAllOpen();
        req.setAttribute("openNotes", openNotes);

        return Actions.SHOWALLNOTES.action;
    }
}
