package com.pvt.controller.command;

import com.pvt.services.NoteService;
import com.pvt.services.UserService;
import com.pvt.services.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class CmdMain extends Action {
    @Autowired
    NoteService ns;

    @Autowired
    UserService us;

    @Override
    public Action execute(HttpServletRequest req) {

        try {
            req.setAttribute("allAds", ns.countAll());
            req.setAttribute("openAds", ns.countAllOpen());
            req.setAttribute("workingAds", ns.countInProgress());
            req.setAttribute("closedAds", ns.countResolved());
            req.setAttribute("countOfUsers", us.countAllUsers());
            req.setAttribute("countOfManagers", us.countUserByRole("Manager"));
            req.setAttribute("countOfWorkers", us.countUserByRole("Worker"));
            req.setAttribute("countOfDirector", us.countUserByRole("Director"));

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return Actions.MAIN.action;
    }
}
