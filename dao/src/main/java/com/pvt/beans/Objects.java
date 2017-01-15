package com.pvt.beans;


public class Objects {
    private int ID;
    private String zia;

    public Objects() {
    }

    public Objects(int ID, String zia) {
        this.ID = ID;
        this.zia = zia;
    }

    public Objects(String zia) {
        this.zia = zia;
    }

    public String getZia() {
        return zia;
    }

    public void setZia(String zia) {
        this.zia = zia;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Objects{" +
                "ID=" + ID +
                ", zia='" + zia + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Objects objects = (Objects) o;

        if (ID != objects.ID) return false;
        return zia != null ? zia.equals(objects.zia) : objects.zia == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (zia != null ? zia.hashCode() : 0);
        return result;
    }
}
