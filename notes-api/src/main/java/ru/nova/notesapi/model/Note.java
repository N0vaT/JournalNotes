package ru.nova.notesapi.model;

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
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "check_list_id", referencedColumnName = "checkListId")
//    private CheckList checkList;

//    private User owner;
    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL, mappedBy = "note")
    private List<CommentNote> noteComments = new ArrayList<>();
    public enum VisibilityModifier{
        EVERYONE, NO_ONE
    }
    public enum Tag{
        NORMAL, IMPORTANT, OBSOLETE, ARCHIVE
    }
}