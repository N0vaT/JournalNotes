package ru.nova.notesapi.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nova.notesapi.model.Note;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOwnerUserId(long userId, PageRequest pageRequest);
    List<Note> findAllByOwnerUserIdAndNoteTag(long userId, Note.Tag tag, PageRequest pageRequest);
}
