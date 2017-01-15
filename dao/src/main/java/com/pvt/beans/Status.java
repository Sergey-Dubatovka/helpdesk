package com.pvt.beans;

public class Status {

    private int ID;
    private String statusName;

    public Status() {
    }

    public Status(int ID, String statusName) {
        this.ID = ID;
        this.statusName = statusName;
    }

    public Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Status{" +
                "ID=" + ID +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Status status = (Status) o;

        if (ID != status.ID) return false;
        return statusName != null ? statusName.equals(status.statusName) : status.statusName == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (statusName != null ? statusName.hashCode() : 0);
        return result;
    }
}
