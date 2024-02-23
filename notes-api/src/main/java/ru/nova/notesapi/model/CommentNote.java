package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CN")
@Getter
@Setter
@ToString
public class CommentNote extends Comment{
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "to_note_id")
    @JsonIgnore
    private Note note;
}
