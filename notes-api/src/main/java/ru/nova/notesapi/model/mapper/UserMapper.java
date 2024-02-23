package ru.nova.notesapi.model.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.User;
import ru.nova.notesapi.model.dto.NoteDTO;
import ru.nova.notesapi.model.dto.UserDTO;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final NoteMapper noteMapper;
    public UserDTO toDto(User user){
        return UserDTO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .dateOfCreation(user.getDateOfCreation())
                .notes(user.getNotes() == null ? null : user.getNotes().stream()
                        .map(noteMapper::toDto)
                        .toList())
                .build();
    }

    public User toUser(UserDTO userDTO){
        return User.builder()
                .userId(userDTO.getUserId())
                .username(userDTO.getUsername())
                .dateOfCreation(userDTO.getDateOfCreation())
                .notes(userDTO.getNotes() == null ? null : userDTO.getNotes().stream()
                        .map(noteMapper::toNote)
                        .toList())
                .build();
    }
}
