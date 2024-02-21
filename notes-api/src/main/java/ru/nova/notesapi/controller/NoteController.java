package ru.nova.notesapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes-api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getNotes(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            @RequestParam(required = false, defaultValue = "dateOfCreation") String sortByField,
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false) String tags
    ){
        if(ownerId == null){
            return ResponseEntity.ok(noteService.findAll(pageNumber, pageSize, direction, sortByField));
        }
        return null;
//        return ResponseEntity.ok(noteService.findAllByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNote(@PathVariable long noteId){
        ResponseEntity<Note> response;
        try {
            response = new ResponseEntity<>(noteService.findById(noteId), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }


}
