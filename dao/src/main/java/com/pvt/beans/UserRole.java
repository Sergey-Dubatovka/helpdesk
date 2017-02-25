package com.pvt.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_ROLE")
//@org.hibernate.annotations.Cache(usage =CacheConcurrencyStrategy.READ_WRITE)
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", unique = true)
    private Long roleId;

    @Getter @Setter
    @Column(name = "ROLE_NAME")
    private String roleName;

    @Getter @Setter
    @OneToMany(mappedBy = "userRole")
    private List<User> users = new ArrayList<>();

    public UserRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        if (!roleId.equals(userRole.roleId)) return false;
        return roleName.equals(userRole.roleName);
    }

    @Override
    public int hashCode() {
        int result = roleId.hashCode();
        result = 31 * result + roleName.hashCode();
        return result;
    }
}
