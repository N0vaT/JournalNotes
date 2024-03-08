package ru.nova.notesapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Comment entity")
public class CommentDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long commentId;
    private String commentText;
    private LocalDateTime dateOfCreation;
    private Long ownerId;

    private List<CommentDTO> comments;
}
