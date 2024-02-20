package ru.nova.notesapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@DiscriminatorValue("CC")
@Getter
@Setter
public class CommentComment extends Comment{
//    @MapsId
//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "to_comment_id")
//    @JsonIgnore
//    private CommentComment comment;
}
