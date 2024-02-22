package ru.nova.notesapi.service;

import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;

import java.util.List;

public interface CommentService {

    List<CommentNote> findAllByNoteId(long noteId, int pageNumber, int pageSize, String direction, String sortByField);

    Comment findById(long commentId);

    Comment create(CommentNote commentNote);
    Comment patchUpdate(CommentNote commentNote, long commentId);
    Comment putUpdate(CommentNote commentNote, long commentId);
    void delete(long commentId);
}
