package com.pvt.dao;

import com.pvt.beans.GamingClub;
import com.pvt.beans.NotePriority;

/**
 * Created by sssergey83 on 14.02.2017.
 */
public class DAO {
    private static DAO dao;

    public UserDAO user;
    public UserRoleDAO role;
    public NoteDAO note;
    public NoteStatusDAO noteStatus;
    public NotePriorityDAO notePriority;
    public GamingClubDAO gamingClub;

    public static DAO getDAO() {
        if (dao == null) {
            synchronized (DAO.class) {
                if (dao == null) {
                    dao = new DAO();
                    dao.user = new UserDAO();
                    dao.role = new UserRoleDAO();
                    dao.noteStatus = new NoteStatusDAO();
                    dao.notePriority = new NotePriorityDAO();
                    dao.note = new NoteDAO();
                    dao.gamingClub = new GamingClubDAO();
                }
            }
        }
        return dao;
    }
}

