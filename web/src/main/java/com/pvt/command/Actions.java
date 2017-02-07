package com.pvt.command;

import javax.servlet.http.HttpServletRequest;

public enum Actions {
    MAIN {{this.action=new CmdMain();}},
    PROFILE{{this.action=new CmdProfile();}},
    SHOWUSERS_OLD{{this.action=new CmdSHOWUSERS_OLD();}},
    SHOWALLNOTES {{this.action=new CmdShowAllNotes();}},
    CREATENOTE {{this.action=new CmdCreateNote();}},
    SHOWUSERS {{this.action=new CmdShowUsers();}},
    SIGNUP {{this.action=new CmdSignup();}},
    LOGIN {{this.action=new CmdLogin();}},
    LOGOUT {{this.action=new CmdLogout();}},
    ERROR  {{this.action=new CmdError();}};

protected Action action=null;

static Action defineFrom(HttpServletRequest req){

    Action result;

    String cmdName = req.getParameter("command").toUpperCase();

    try {
        result=Actions.valueOf(cmdName).action;
    }
    catch (IllegalArgumentException e) {
        result=Actions.ERROR.action;
    }
    return result;
}
}
