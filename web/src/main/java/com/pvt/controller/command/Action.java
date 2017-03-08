package com.pvt.controller.command;

import javax.servlet.http.HttpServletRequest;

abstract public class Action {
    public abstract Action execute(HttpServletRequest req);

    public Action getRedirectAction() {
        return redirectAction;
    }

    public void setRedirectAction(Action redirectAction) {
        this.redirectAction = redirectAction;
    }

    private Action redirectAction;

    @Override
    public String toString() {
        String name = this.getClass().getSimpleName();
        name = name.replace("Cmd", "");
        return name;
    }

    public String getJsp() {
        String name = this.toString().toLowerCase();
        return "/" + name + ".jsp";
    }


}
