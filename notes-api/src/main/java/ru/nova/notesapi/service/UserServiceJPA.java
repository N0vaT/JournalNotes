package ru.nova.notesapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nova.notesapi.exception.UserNotFoundException;
import ru.nova.notesapi.model.User;
import ru.nova.notesapi.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceJPA implements UserService{
    private final UserRepository userRepository;
    @Override
    public List<User> findAll(int pageNumber, int pageSize, String direction, String sortByField) {
        Sort.Direction sortDirection = direction.equals("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortDirection, sortByField);
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        return userRepository.findAll(pageRequest).toList();
    }

    @Override
    public User findById(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
    }

    @Override
    public User create(User user) {
        if(user.getDateOfCreation() == null){
            user.setDateOfCreation(LocalDateTime.now());
        }
        return userRepository.save(user);
    }

    @Override
    public User patchUpdate(User User, long userId) {
        return null;
    }

    @Override
    public User putUpdate(User user, long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public void delete(long userId) {
        userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User with id - " + userId + " not found."));
        userRepository.deleteById(userId);
    }
}
