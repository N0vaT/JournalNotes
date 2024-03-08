package ru.nova.notesapi.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nova.notesapi.model.Note;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Schema(description = "User entity")
public class UserDTO {
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long userId;
    private String username;
    private LocalDateTime dateOfCreation;
    private List<NoteDTO> notes;
}
