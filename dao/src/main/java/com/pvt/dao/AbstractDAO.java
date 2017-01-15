package com.pvt.dao;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDAO {
    private static Logger log = Logger.getLogger(AbstractDAO.class);
    public String lastSQL = "";

    //общая команда для Create Update Delete
    protected int executeUpdate(String sql) {
        int result = -1;
        lastSQL = "executeUpdate:" + sql;
        try (Connection connection = ConnectionCreator.getConnection();
             Statement statement = connection.createStatement()) {
            result = statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            //если это была вставка вернем ключ, а иначе вернем число записей
            if (sql.trim().toUpperCase().startsWith("INSERT")) {
                ResultSet resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) result = resultSet.getInt(1);
            }
        } catch (SQLException sqle) {
            log.error(sqle);
        } catch (ClassNotFoundException e) {
            log.error(e);
        }
        return result;
    }


}
