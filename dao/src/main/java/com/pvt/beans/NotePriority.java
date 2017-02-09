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
@Table(name = "NOTE_PRIORITY")
public class NotePriority implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Getter @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRIORITY_ID", unique = true)
    private Long priorityId;

    @Getter @Setter
    @Column(name = "PRIORITY_NAME")
    private String priorityName;

    @Getter @Setter
    @OneToMany(mappedBy = "notePriority")
    private List<Note> notes = new ArrayList<>();

    public NotePriority(String priorityName) {
        this.priorityName = priorityName;
    }

    @Override
    public String toString() {
        return "NotePriority{" +
                "priorityId=" + priorityId +
                ", priorityName='" + priorityName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotePriority that = (NotePriority) o;

        if (!priorityId.equals(that.priorityId)) return false;
        return priorityName.equals(that.priorityName);
    }

    @Override
    public int hashCode() {
        int result = priorityId.hashCode();
        result = 31 * result + priorityName.hashCode();
        return result;
    }
}
