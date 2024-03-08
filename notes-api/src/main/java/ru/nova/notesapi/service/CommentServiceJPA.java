package ru.nova.notesapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nova.notesapi.exception.CommentNotFoundException;
import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentComment;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.repository.CommentCommentRepository;
import ru.nova.notesapi.repository.CommentNoteRepository;
import ru.nova.notesapi.service.util.CopyNotNullField;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceJPA implements CommentService {
    private final CommentNoteRepository commentNoteRepository;
    private final CommentCommentRepository commentCommentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CommentNote> findAllByNoteId(long noteId, int pageNumber, int pageSize, String direction, String sortByField) {
        return commentNoteRepository.findAllByNoteNoteId(noteId);
    }

    @Override
    public List<CommentComment> findAllByCommentBeforeId(long commentBeforeId, int pageNumber, int pageSize, String direction, String sortByField) {
        return commentCommentRepository.findByCommentCommentId(commentBeforeId);
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
    public Comment create(CommentComment commentComment) {
        if(commentComment.getDateOfCreation() == null){
            commentComment.setDateOfCreation(LocalDateTime.now());
        }
        return commentCommentRepository.save(commentComment);
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
    public Comment patchUpdate(CommentComment commentComment, long commentId) {
        Comment existingComment = commentCommentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        CopyNotNullField.copyNonNullProperties(commentComment, existingComment);
        return commentCommentRepository.save(existingComment);
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
    public Comment putUpdate(CommentComment commentComment, long commentId) {
        commentCommentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        commentComment.setCommentId(commentId);
        return commentCommentRepository.save(commentComment);
    }

    @Override
    @Transactional
    public void delete(long commentId) {
        commentNoteRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment with id - " + commentId + " not found."));
        commentNoteRepository.deleteById(commentId);
    }
}
