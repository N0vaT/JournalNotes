package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CC")
@Getter
@Setter
@ToString
public class CommentComment extends Comment{
}
