package ru.nova.notesapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.repository.NoteRepository;
import ru.nova.notesapi.repository.UserRepository;
import ru.nova.notesapi.service.util.CopyNotNullField;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteServiceJPA implements NoteService {
    private final NoteRepository noteRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Note> findAll(int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return noteRepository.findAll(pageRequest).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> findAllByOwnerId(long userId, int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return noteRepository.findAllByOwnerUserId(userId, pageRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Note findById(long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new NoteNotFoundException("Note with id - " + noteId + " not found."));
    }

    @Override
    @Transactional
    public Note create(Note note) {
        if(note.getDateOfCreation() == null){
            note.setDateOfCreation(LocalDateTime.now());
        }
        if(note.getVisibilityModifier() == null){
            note.setVisibilityModifier(Note.VisibilityModifier.EVERYONE);
        }
        if(note.getNoteTag() == null){
            note.setNoteTag(Note.Tag.NORMAL);
        }
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public Note patchUpdate(Note note, long noteId) {
        Note existingNote = noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with id - " + noteId + " not found."));
        CopyNotNullField.copyNonNullProperties(note, existingNote);
        return noteRepository.save(existingNote);
    }

    @Override
    @Transactional
    public Note putUpdate(Note note, long noteId) {
        noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with id - " + noteId + " not found."));
        note.setNoteId(noteId);
        return noteRepository.save(note);
    }

    @Override
    @Transactional
    public void delete(long noteId) {
        noteRepository.findById(noteId).orElseThrow(() -> new NoteNotFoundException("Note with id - " + noteId + " not found."));
        noteRepository.deleteById(noteId);
    }
}
