package ru.nova.notesapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.notesapi.model.CheckList;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "Note entity")
public class NoteDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long noteId;
    private String noteText;
    private LocalDateTime dateOfCreation;
    private Note.VisibilityModifier visibilityModifier;
    private Note.Tag noteTag;
    private Long ownerId;
    private CheckList checkList;
    private List<CommentDTO> noteComments;
}
