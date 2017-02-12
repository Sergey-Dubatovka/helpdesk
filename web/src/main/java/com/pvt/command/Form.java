package com.pvt.command;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Set;

public class Form {
    static String getString(HttpServletRequest req,
                            String parameter,
                            String pattern) throws ParseException {
        String value = req.getParameter(parameter);
        if (value != null) {
            if (value.matches(pattern))
                return value;
            else
                throw new ParseException("Incorrect String: " + parameter, 0);
        }
        return null;
    }

    static long getLong(HttpServletRequest req,
                        String parameter) throws ParseException {
        String value = req.getParameter(parameter);
        if (value != null) {
            if (value.matches("[0-9-]+")) {
                return (Long.parseLong(value));
            }
        }
        throw new ParseException("Incorrect String: " + parameter, 0);
    }

    static boolean isPost(HttpServletRequest req) {
        return (req.getMethod() != null && req.getMethod().equalsIgnoreCase("post"));
    }
}
