package com.pvt.beans;

public class Priority {
    private int ID;
    private String priorityName;

    public Priority() {
    }

    public Priority(int ID, String priorityName) {
        this.ID = ID;
        this.priorityName = priorityName;
    }

    public Priority(String priorityName) {
        this.priorityName = priorityName;
    }

    public String getPriorityName() {
        return priorityName;
    }

    public void setPriorityName(String priorityName) {
        this.priorityName = priorityName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Priority{" +
                "ID=" + ID +
                ", priorityName='" + priorityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Priority priority = (Priority) o;

        if (ID != priority.ID) return false;
        return priorityName != null ? priorityName.equals(priority.priorityName) : priority.priorityName == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (priorityName != null ? priorityName.hashCode() : 0);
        return result;
    }
}
