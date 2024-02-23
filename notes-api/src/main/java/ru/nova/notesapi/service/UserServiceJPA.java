package ru.nova.notesapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.exception.UserNotFoundException;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;
import ru.nova.notesapi.repository.UserRepository;
import ru.nova.notesapi.service.util.CopyNotNullField;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService{
    private final UserRepository userRepository;
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll(int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(pageRequest).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
    }

    @Override
    @Transactional
    public User create(User user) {
        if(user.getDateOfCreation() == null){
            user.setDateOfCreation(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User patchUpdate(User user, long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
        CopyNotNullField.copyNonNullProperties(user, existingUser);
        return userRepository.save(existingUser);
    }

    @Override
    @Transactional
    public User putUpdate(User user, long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
        userRepository.deleteById(userId);
    }
}
