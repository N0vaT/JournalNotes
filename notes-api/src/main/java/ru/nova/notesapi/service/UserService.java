package ru.nova.notesapi.service;

import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll(int pageNumber, int pageSize, String direction, String sortByField);
    User findById(long userId);
    User create(User User);
    User patchUpdate(User User, long userId);
    User putUpdate(User user, long userId);
    void delete(long userId);
}
