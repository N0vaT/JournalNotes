package ru.nova.notesapi.repository;

import org.springframework.stereotype.Repository;
import ru.nova.notesapi.model.CommentNote;

import java.util.List;

@Repository
public interface CommentNoteRepository extends CommentRepository{
    List<CommentNote> findAllByNoteNoteId(long noteId);
    void deleteByCommentId(long commentId);
}
