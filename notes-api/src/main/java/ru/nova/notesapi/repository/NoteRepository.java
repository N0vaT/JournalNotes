package ru.nova.notesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nova.notesapi.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

//    Page<Note> findAll(PageRequest pageRequest);
//    List<Note> findAllByOwnerUserId(long userId, PageRequest pageRequest);
}
