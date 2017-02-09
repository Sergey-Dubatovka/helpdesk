package com.pvt.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTE_STATUS")

public class NoteStatus implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID", unique = true)
    private Long statusId;

    @Getter @Setter
    @Column(name = "STATUS_NAME")
    private String statusName;

    @Getter @Setter
    @OneToMany(mappedBy = "noteStatus")
    private List<Note> notes = new ArrayList<>();

    public NoteStatus(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public String toString() {
        return "NoteStatus{" +
                "statusId=" + statusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NoteStatus that = (NoteStatus) o;

        if (!statusId.equals(that.statusId)) return false;
        return statusName.equals(that.statusName);
    }

    @Override
    public int hashCode() {
        int result = statusId.hashCode();
        result = 31 * result + statusName.hashCode();
        return result;
    }
}
