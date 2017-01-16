package com.pvt.command;



import com.pvt.dao.DAO;

import javax.servlet.http.HttpServletRequest;

public class CmdMain extends Action {

    @Override
    Action execute(HttpServletRequest req) {
        DAO dao= DAO.getDAO();
        req.setAttribute("allAds",dao.ad.getAll("").size());
        req.setAttribute("openAds",dao.ad.getCountOfOpenAds());
        req.setAttribute("workingAds",dao.ad.getCountOfWorkingAds());
        req.setAttribute("closedAds",dao.ad.getCountOfClosedAds());
        req.setAttribute("countOfUsers",dao.user.getCountOfUsers());
        req.setAttribute("countOfManagers",dao.user.getCountOfManagers());
        req.setAttribute("countOfWorkers",dao.user.getCountOfWorker());
        req.setAttribute("director",
                (dao.user.getAll("").size()-
                (dao.user.getCountOfManagers()+
                dao.user.getCountOfWorker())));

        return Actions.MAIN.action;
    }
}
