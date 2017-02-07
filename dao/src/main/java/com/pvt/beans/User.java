package com.pvt.beans;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long userId;

    @Getter @Setter
    @Column(name = "LOGIN")
    private String login;

    @Getter @Setter
    @Column(name = "PASSWORD")
    private String password;

    @Getter @Setter
    @Column(name = "EMAIL")
    private String email;

    @Getter @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "ROLE_ID")
    private UserRole userRole;

    @Getter @Setter
    @OneToMany(mappedBy = "user")
    private Set<Note> notes = new HashSet<>();

    public User(String login, String password, String email, UserRole userRole) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}