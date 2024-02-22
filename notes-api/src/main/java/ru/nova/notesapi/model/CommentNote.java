package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CN")
@Getter
@Setter
@ToString
public class CommentNote extends Comment{
    @ManyToOne
    @JoinColumn(name = "to_note_id")
    @JsonIgnore
    private Note note;
}
