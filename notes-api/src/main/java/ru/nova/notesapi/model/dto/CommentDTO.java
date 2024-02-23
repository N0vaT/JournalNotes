package ru.nova.notesapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.notesapi.model.CommentComment;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class CommentDTO {
    private Long commentId;
    private String commentText;
    private LocalDateTime dateOfCreation;
    private Long ownerId;

    private List<CommentDTO> comments;
}
