package ru.nova.notesapi.service;

import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentComment;
import ru.nova.notesapi.model.CommentNote;

import java.util.List;

public interface CommentService {
    List<CommentNote> findAllByNoteId(long noteId, int pageNumber, int pageSize, String direction, String sortByField);
    List<CommentComment> findAllByCommentBeforeId(long commentBeforeId, int pageNumber, int pageSize, String direction, String sortByField);
    Comment findById(long commentId);
    Comment create(CommentNote commentNote);
    Comment create(CommentComment commentComment);
    Comment patchUpdate(CommentNote commentNote, long commentId);
    Comment patchUpdate(CommentComment commentComment, long commentId);
    Comment putUpdate(CommentNote commentNote, long commentId);
    Comment putUpdate(CommentComment commentComment, long commentId);
    void delete(long commentId);
}
