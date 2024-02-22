package ru.nova.notesapi.model.mapper;

import org.springframework.stereotype.Component;
import ru.nova.notesapi.model.*;
import ru.nova.notesapi.model.dto.CommentDTO;
import ru.nova.notesapi.model.dto.NoteDTO;

@Component
public class CommentMapper {
    public CommentDTO toDto(Comment comment){
        return CommentDTO.builder()
                .commentId(comment.getCommentId())
                .commentText(comment.getCommentText())
                .dateOfCreation(comment.getDateOfCreation())
                .ownerId(comment.getOwner().getUserId())
                .comments(comment.getComments() == null ? null : comment.getComments().stream().map(this::toDto).toList())
                .build();
    }

    public CommentComment toCommentComment(CommentDTO commentDTO){
        CommentComment comment = new CommentComment();
        comment.setCommentId(commentDTO.getCommentId());
        comment.setCommentText(commentDTO.getCommentText());
        comment.setDateOfCreation(commentDTO.getDateOfCreation());
        comment.setOwner(commentDTO.getOwnerId() == null ? null : User.builder()
                        .userId(commentDTO.getCommentId())
                        .build());
        comment.setComments(commentDTO.getComments() == null ? null : commentDTO.getComments().stream()
                .map(this::toCommentComment)
                .toList());
        return comment;
    }

    public CommentNote toCommentNote(CommentDTO commentDTO){
        CommentNote comment = new CommentNote();
        comment.setCommentId(commentDTO.getCommentId());
        comment.setCommentText(commentDTO.getCommentText());
        comment.setDateOfCreation(commentDTO.getDateOfCreation());
        comment.setOwner(commentDTO.getOwnerId() == null ? null : User.builder()
                .userId(commentDTO.getOwnerId())
                .build());
        comment.setComments(commentDTO.getComments() == null ? null : commentDTO.getComments().stream()
                .map(this::toCommentComment)
                .toList());
        return comment;
    }
}
