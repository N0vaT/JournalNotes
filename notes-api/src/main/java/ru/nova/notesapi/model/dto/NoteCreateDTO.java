package ru.nova.notesapi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.notesapi.model.Note;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NoteCreateDTO {
    private String noteText;
    private LocalDateTime dateOfCreation;
    private Note.VisibilityModifier visibilityModifier;
    private Note.Tag noteTag;
}
