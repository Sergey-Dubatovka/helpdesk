package com.pvt.command;

import com.pvt.NoteService;
import com.pvt.UserService;

import javax.servlet.http.HttpServletRequest;

public class CmdMain extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        NoteService ns = NoteService.getService();
        UserService us = UserService.getService();

        req.setAttribute("allAds", ns.countAll());
        req.setAttribute("openAds", ns.countAllopen());
        req.setAttribute("workingAds", ns.countInProgress());
        req.setAttribute("closedAds", ns.countResolved());
        req.setAttribute("countOfUsers", us.allUserCount());
        req.setAttribute("countOfManagers", us.countUserByRole("Manager"));
        req.setAttribute("countOfWorkers", us.countUserByRole("Worker"));
        req.setAttribute("countOfDirector", us.countUserByRole("Director"));

        return Actions.MAIN.action;
    }
}
