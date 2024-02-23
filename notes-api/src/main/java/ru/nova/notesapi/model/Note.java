package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "jn_notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long noteId;
    @Column(name = "note_text")
    private String noteText;
    @Column(name = "date_of_creation")
    private LocalDateTime dateOfCreation;
    @Column(name = "visibility_modifier")
    @Enumerated(value = EnumType.STRING)
    private VisibilityModifier visibilityModifier;
    @Column(name = "note_tag")
    @Enumerated(value = EnumType.STRING)
    private Tag noteTag;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "check_list_id", referencedColumnName = "check_list_id")
    private CheckList checkList;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "owner_id")
    @JsonIgnore
    private User owner;
    @OneToMany(mappedBy = "note")
    private List<CommentNote> noteComments;
    public enum VisibilityModifier{
        EVERYONE, NO_ONE
    }
    public enum Tag{
        NORMAL, IMPORTANT, OBSOLETE, ARCHIVE
    }
}
