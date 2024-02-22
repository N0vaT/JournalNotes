package ru.nova.notesapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nova.notesapi.exception.CommentNotFoundException;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.repository.CommentNoteRepository;
import ru.nova.notesapi.repository.CommentRepository;
import ru.nova.notesapi.service.util.CopyNotNullField;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceJPA implements CommentService {
    private final CommentNoteRepository commentNoteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentNote> findAllByNoteId(long noteId, int pageNumber, int pageSize, String direction, String sortByField) {
        return commentNoteRepository.findAllByNoteNoteId(noteId);
    }
    @Override
    @Transactional(readOnly = true)
    public Comment findById(long commentId) {
        return commentNoteRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
    }

    @Override
    @Transactional
    public Comment create(CommentNote commentNote) {
        if(commentNote.getDateOfCreation() == null){
            commentNote.setDateOfCreation(LocalDateTime.now());
        }
        return commentNoteRepository.save(commentNote);
    }

    @Override
    @Transactional
    public Comment patchUpdate(CommentNote commentNote, long commentId) {
        Comment existingComment = commentNoteRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        CopyNotNullField.copyNonNullProperties(commentNote, existingComment);
        return commentNoteRepository.save(existingComment);
    }

    @Override
    @Transactional
    public Comment putUpdate(CommentNote commentNote, long commentId) {
        commentNoteRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        commentNote.setCommentId(commentId);
        return commentNoteRepository.save(commentNote);
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        commentNoteRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        commentNoteRepository.deleteById(commentId);
    }
}
