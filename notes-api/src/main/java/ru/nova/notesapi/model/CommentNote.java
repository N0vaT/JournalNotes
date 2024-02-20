package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("CN")
@Getter
@Setter
public class CommentNote extends Comment{
    @ManyToOne
    @JoinColumn(name = "to_note_id")
    @JsonIgnore
    private Note note;
}
