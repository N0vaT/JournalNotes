package ru.nova.notesapi.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;
import ru.nova.notesapi.model.dto.NoteDTO;

@Component
@RequiredArgsConstructor
public class NoteMapper {
    private final CommentMapper commentMapper;
    public NoteDTO toDto(Note note){
        return NoteDTO.builder()
                .noteId(note.getNoteId())
                .noteText(note.getNoteText())
                .dateOfCreation(note.getDateOfCreation())
                .visibilityModifier(note.getVisibilityModifier())
                .noteTag(note.getNoteTag())
                .ownerId(note.getOwner().getUserId())
                .noteComments(note.getNoteComments() == null ? null : note.getNoteComments().stream()
                        .map(commentMapper::toDto)
                        .toList())
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
                .noteComments(noteDTO.getNoteComments() == null ? null : noteDTO.getNoteComments().stream()
                        .map(commentMapper::toCommentNote)
                        .toList())
                .build();
    }
}
