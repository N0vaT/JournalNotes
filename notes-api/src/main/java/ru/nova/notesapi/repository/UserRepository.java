package ru.nova.notesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nova.notesapi.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
