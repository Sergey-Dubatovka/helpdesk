package com.pvt.beans;


public class Role {
    private int ID;
    private String roleName;

    public Role() {
    }

    public Role(int ID, String roleName) {
        this.ID = ID;
        this.roleName = roleName;
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Role{" +
                "ID=" + ID +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (ID != role.ID) return false;
        return roleName != null ? roleName.equals(role.roleName) : role.roleName == null;
    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        return result;
    }
}
