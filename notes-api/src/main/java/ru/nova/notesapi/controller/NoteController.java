package ru.nova.notesapi.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.dto.NoteCreateDTO;
import ru.nova.notesapi.model.dto.NoteDTO;
import ru.nova.notesapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes-api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getNotes(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize,
            @RequestParam(required = false, defaultValue = "DESC") String direction,
            @RequestParam(required = false, defaultValue = "dateOfCreation") String sortByField,
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false) String tags
    ){
        if(ownerId == null){
            return ResponseEntity.ok(noteService.findAll(pageNumber, pageSize, direction, sortByField).stream()
                            .map(n -> modelMapper.map(n, NoteDTO.class))
                    .toList());
        }
        return null;
//        return ResponseEntity.ok(noteService.findAllByOwnerId(ownerId, pageNumber, pageSize, direction, sortByField));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<NoteDTO> getNote(@PathVariable long noteId){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteService.findById(noteId);
            response = new ResponseEntity<>(modelMapper.map(note, NoteDTO.class), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public NoteDTO addNote(@RequestBody NoteCreateDTO noteCreateDTO){
        Note note = noteService.create(modelMapper.map(noteCreateDTO, Note.class));
        return modelMapper.map(note, NoteDTO.class);
    }

    @PatchMapping("/{noteId}")
    public ResponseEntity<NoteDTO> patchUpdateNote(@PathVariable long noteId,
                                                   @RequestBody NoteDTO noteDTO){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteService.patchUpdate(modelMapper.map(noteDTO, Note.class), noteId);
            response = new ResponseEntity<>(modelMapper.map(note, NoteDTO.class), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<NoteDTO> putUpdateNote(@PathVariable long noteId,
                                                 @RequestBody NoteDTO noteDTO){
        ResponseEntity<NoteDTO> response;
        try {
            Note note = noteService.putUpdate(modelMapper.map(noteDTO, Note.class), noteId);
            response = new ResponseEntity<>(modelMapper.map(note, NoteDTO.class), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Long> deleteNote(@PathVariable long noteId){
        try {
            noteService.delete(noteId);
            return new ResponseEntity<>(noteId, HttpStatus.OK);
        }catch (NoteNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
