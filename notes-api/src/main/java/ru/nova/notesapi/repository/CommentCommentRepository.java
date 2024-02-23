package ru.nova.notesapi.repository;

import org.springframework.stereotype.Repository;
import ru.nova.notesapi.model.CommentComment;

import java.util.List;

@Repository
public interface CommentCommentRepository extends CommentRepository{
    List<CommentComment> findByCommentCommentId(long commentBeforeId);
}
