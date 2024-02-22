package ru.nova.notesapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.exception.UserNotFoundException;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.dto.CommentDTO;
import ru.nova.notesapi.model.dto.NoteDTO;
import ru.nova.notesapi.model.mapper.CommentMapper;
import ru.nova.notesapi.model.mapper.NoteMapper;
import ru.nova.notesapi.service.CommentService;
import ru.nova.notesapi.service.NoteService;
import ru.nova.notesapi.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/notes-api/v1/users/{userId}/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;
    private final CommentService commentService;
    private final NoteMapper noteMapper;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(@PathVariable long userId,
                                                  @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "25") int pageSize,
                                                  @RequestParam(required = false, defaultValue = "DESC") String direction,
                                                  @RequestParam(required = false, defaultValue = "dateOfCreation") String sortByField,
                                                  @RequestParam(required = false) String tags
    ){
        List<Note> notes = noteService.findAllByOwnerId(userId, pageNumber, pageSize, direction, sortByField);
        return ResponseEntity.ok(notes.stream().map(noteMapper::toDto).toList());
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable long userId,
                                           @PathVariable long noteId
    ){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteService.findById(noteId);
            response = new ResponseEntity<>(noteMapper.toDto(note), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    public ResponseEntity<NoteDTO> addNote(@PathVariable long userId,
                                           @RequestBody NoteDTO noteDTO
    ){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteMapper.toNote(noteDTO);
            note.setOwner(userService.findById(userId));
            response = new ResponseEntity<>(noteMapper.toDto(noteService.create(note)), HttpStatus.OK);
        }catch (NoteNotFoundException | UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<NoteDTO> patchUpdateNote(@PathVariable long userId,
                                                   @PathVariable long noteId,
                                                   @RequestBody NoteDTO noteDTO){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteMapper.toNote(noteDTO);
            note.setOwner(userService.findById(userId));
            response = new ResponseEntity<>(noteMapper.toDto(noteService.patchUpdate(note, noteId)), HttpStatus.OK);
        }catch (NoteNotFoundException | UserNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> putUpdateNote(@PathVariable long userId,
                                                 @PathVariable long noteId,
                                                 @RequestBody NoteDTO noteDTO){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteMapper.toNote(noteDTO);
            note.setOwner(userService.findById(userId));
            response = new ResponseEntity<>(noteMapper.toDto(noteService.putUpdate(note, noteId)), HttpStatus.OK);
        }catch (UserNotFoundException | NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Long> deleteNote(@PathVariable long userId,
                                           @PathVariable long noteId){
        try {
            noteService.delete(noteId);
            return new ResponseEntity<>(noteId, HttpStatus.OK);
        }catch (NoteNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
