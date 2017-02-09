package com.pvt.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "GAMING_CLUB")
public class GamingClub implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GAMING_CLUB_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long gamingClubId;

    @Getter @Setter
    @Column(name = "GAMING_CLUB_NAME")
    private String gamingClubName;

    @Getter @Setter
    @OneToMany(mappedBy = "gamingClub")
    private Set<Note> notes = new HashSet<>();

    public GamingClub(String gamingClubName) {
        this.gamingClubName = gamingClubName;
    }

    @Override
    public String toString() {
        return "GamingClub{" +
                "gamingClubId=" + gamingClubId +
                ", gamingClubName='" + gamingClubName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GamingClub that = (GamingClub) o;

        if (!gamingClubId.equals(that.gamingClubId)) return false;
        return gamingClubName.equals(that.gamingClubName);
    }

    @Override
    public int hashCode() {
        int result = gamingClubId.hashCode();
        result = 31 * result + gamingClubName.hashCode();
        return result;
    }
}

