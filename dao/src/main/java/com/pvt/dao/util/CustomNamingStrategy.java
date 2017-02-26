package com.pvt.dao.util;

import org.hibernate.cfg.DefaultNamingStrategy;

/**
 * Created by sssergey83 on 25.02.2017.
 */
public class CustomNamingStrategy extends DefaultNamingStrategy{
    public String classToTableName(String className) {
        return "T_" + super.classToTableName(className).toUpperCase();
    }

    public String propertyToColumnName(String propName) {
        return "F_" + super.propertyToColumnName(propName);
    }

    public String columnName(String columnName) {
        return columnName;
    }

    public String tableName(String tablename) {
        return tablename;
    }
}
