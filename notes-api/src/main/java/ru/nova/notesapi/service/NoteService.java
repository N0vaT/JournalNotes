package ru.nova.notesapi.service;

import ru.nova.notesapi.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> findAll(int pageNumber, int pageSize, String direction, String sortByField);
//    List<Note> findAllByOwnerId(long userId, int pageNumber, int pageSize, String direction, String sortByField);
    Note findById(long noteId);
    Note create(Note note);
    Note patchUpdate(Note note, long noteId);
    Note putUpdate(Note note, long noteId);
    boolean delete(long inviteId);
}
