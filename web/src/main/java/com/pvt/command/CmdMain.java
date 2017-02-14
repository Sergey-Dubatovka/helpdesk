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
        req.setAttribute("closedAds",ns.countResolved());

       req.setAttribute("countOfUsers",us.allUserCount());
       req.setAttribute("countOfManagers",us.managerCount());

 //      req.setAttribute("countOfWorkers",dao.user.getCountOfWorker());
//        req.setAttribute("director",
//                (dao.user.getAll("").size()-
//                (dao.user.getCountOfManagers()+
//                dao.user.getCountOfWorker())));

        return Actions.MAIN.action;
    }
}
