package ru.nova.notesapi.model.mapper;

import org.springframework.stereotype.Component;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;
import ru.nova.notesapi.model.dto.NoteDTO;

@Component
public class NoteMapper {
    public NoteDTO toDto(Note note){
        return NoteDTO.builder()
                .noteId(note.getNoteId())
                .noteText(note.getNoteText())
                .dateOfCreation(note.getDateOfCreation())
                .visibilityModifier(note.getVisibilityModifier())
                .noteTag(note.getNoteTag())
                .ownerId(note.getOwner().getUserId())
                .noteComments(note.getNoteComments())
                .build();
    }

    public Note toNote(NoteDTO noteDTO){
        return Note.builder()
                .noteId(noteDTO.getNoteId())
                .noteText(noteDTO.getNoteText())
                .dateOfCreation(noteDTO.getDateOfCreation())
                .visibilityModifier(noteDTO.getVisibilityModifier())
                .noteTag(noteDTO.getNoteTag())
                .owner(User.builder()
                        .userId(noteDTO.getOwnerId())
                        .build())
                .noteComments(noteDTO.getNoteComments())
                .build();
    }
}
