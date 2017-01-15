package com.pvt.dao;

import com.pvt.beans.Priority;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PriorityDAO extends AbstractDAO implements InterfaceDAO<Priority> {
    private static Logger log = Logger.getLogger(PriorityDAO.class);
    @Override
    public Priority read(int id) {
        List<Priority> priorities = getAll("WHERE ID=" + id + " LIMIT 0,1");
        if (priorities.size() > 0) {
            return priorities.get(0);
        } else
            return null;
    }

    @Override
    public boolean create(Priority priority) {
        String createPrioritySQL = String.format("insert into priority(priorityName) values ('%s')", priority.getPriorityName());
        priority.setID(executeUpdate(createPrioritySQL));
        return (priority.getID() > 0);
    }

    @Override
    public boolean update(Priority priority) {
        String updatePrioritySQL = String.format("UPDATE priority SET priorityName = '%s' WHERE priority.ID='%d'",
                priority.getPriorityName(), priority.getID());

        return (0 < executeUpdate(updatePrioritySQL));
    }

    @Override
    public boolean delete(Priority priority) {
        String deletePrioritySQL = String.format("DELETE FROM priority Where priority.ID = '%d'", priority.getID());
        return (0 < executeUpdate(deletePrioritySQL));
    }

    @Override
    public List<Priority> getAll(String WhereAndOrder) {
        List<Priority> priorities = new ArrayList<>();
        String sql = "SELECT * FROM priority " + WhereAndOrder + " ;";
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Priority priority = new Priority();
                priority.setID(resultSet.getInt("ID"));
                priority.setPriorityName(resultSet.getString("priorityName"));
                priorities.add(priority);
            }

        } catch (SQLException sqle) {
            log.error(sqle);
        } catch (ClassNotFoundException e) {
          log.error(e);
        }
        return priorities;
    }
}
