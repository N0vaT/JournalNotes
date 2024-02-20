package ru.nova.notesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.nova.notesapi.model.Comment;

@NoRepositoryBean
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
