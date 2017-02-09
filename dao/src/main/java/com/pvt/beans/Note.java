package com.pvt.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NOTE")
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTE_ID", unique = true)
    @Access(AccessType.PROPERTY)
    private Long noteId;

    @Getter
    @Setter
    @Column(name = "SUBJECT")
    private String subject;

    @Getter
    @Setter
    @Column(name = "DESCRIPTION")
    private String description;

    @Getter
    @Setter
    @Temporal(TemporalType.DATE)
    private Date date;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PRIORITY_ID")
    private NotePriority notePriority;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "STATUS_ID")
    private NoteStatus noteStatus;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "GAMING_CLUB_ID")
    private GamingClub gamingClub;


    public Note(String subject, String description, User user,
                NotePriority priority, NoteStatus status, GamingClub gamingClub) {
        this.subject = subject;
        this.description = description;
        this.user = user;
        this.notePriority = priority;
        this.noteStatus = status;
        this.gamingClub = gamingClub;
    }

    @Override
    public String toString() {
        return "Note{" +
                "noteId=" + noteId +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", user=" + user +
                ", notePriority=" + notePriority +
                ", noteStatus=" + noteStatus +
                ", gamingClub=" + gamingClub +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (noteId != null ? !noteId.equals(note.noteId) : note.noteId != null) return false;
        if (subject != null ? !subject.equals(note.subject) : note.subject != null) return false;
        if (description != null ? !description.equals(note.description) : note.description != null) return false;
        if (date != null ? !date.equals(note.date) : note.date != null) return false;
        if (user != null ? !user.equals(note.user) : note.user != null) return false;
        if (notePriority != null ? !notePriority.equals(note.notePriority) : note.notePriority != null) return false;
        if (noteStatus != null ? !noteStatus.equals(note.noteStatus) : note.noteStatus != null) return false;
        return gamingClub != null ? gamingClub.equals(note.gamingClub) : note.gamingClub == null;
    }

    @Override
    public int hashCode() {
        int result = noteId != null ? noteId.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (notePriority != null ? notePriority.hashCode() : 0);
        result = 31 * result + (noteStatus != null ? noteStatus.hashCode() : 0);
        result = 31 * result + (gamingClub != null ? gamingClub.hashCode() : 0);
        return result;
    }
}

