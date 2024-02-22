package ru.nova.notesapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.notesapi.exception.CommentNotFoundException;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.exception.UserNotFoundException;
import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.dto.CommentDTO;
import ru.nova.notesapi.model.dto.NoteDTO;
import ru.nova.notesapi.model.mapper.CommentMapper;
import ru.nova.notesapi.service.CommentService;
import ru.nova.notesapi.service.NoteService;

import java.util.List;

@RestController
@RequestMapping("/notes-api/v1/notes/{noteId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final NoteService noteService;
    private final CommentMapper commentMapper;

    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable long noteId,
                                                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                        @RequestParam(required = false, defaultValue = "25") int pageSize,
                                                        @RequestParam(required = false, defaultValue = "DESC") String direction,
                                                        @RequestParam(required = false, defaultValue = "dateOfCreation") String sortByField
    ){
        List<CommentNote> comments = commentService.findAllByNoteId(noteId, pageNumber, pageSize, direction, sortByField);
        return ResponseEntity.ok(comments.stream().map(commentMapper::toDto).toList());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable long noteId,
                                                 @PathVariable long commentId){
        ResponseEntity<CommentDTO> response;
        try {
            Note note = noteService.findById(noteId);
            if(note.getNoteComments() == null || note.getNoteComments().stream()
                    .filter( c ->  c.getCommentId().equals(commentId))
                    .findAny().isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            response = new ResponseEntity<>(commentMapper.toDto(commentService.findById(commentId)), HttpStatus.OK);
        }catch (NoteNotFoundException | CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }
    @PostMapping
    public ResponseEntity<CommentDTO> addComment(@PathVariable long noteId,
                                 @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try{
            Note note = noteService.findById(noteId);
            CommentNote comment = commentMapper.toCommentNote(commentDTO);
            comment.setNote(note);
            response = new ResponseEntity<>(commentMapper.toDto(commentService.create(comment)), HttpStatus.OK);
        }catch (NoteNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> patchUpdateComment(@PathVariable long noteId,
                                                         @PathVariable long commentId,
                                                         @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try {
            CommentNote comment = commentMapper.toCommentNote(commentDTO);
            comment.setNote(noteService.findById(noteId));
            response = new ResponseEntity<>(commentMapper.toDto(commentService.patchUpdate(comment, commentId)), HttpStatus.OK);
        }catch (NoteNotFoundException | CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> putUpdateComment(@PathVariable long noteId,
                                                       @PathVariable long commentId,
                                                       @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try {
            CommentNote comment = commentMapper.toCommentNote(commentDTO);
            comment.setNote(noteService.findById(noteId));
            response = new ResponseEntity<>(commentMapper.toDto(commentService.putUpdate(comment, commentId)), HttpStatus.OK);
        }catch (NoteNotFoundException | CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long noteId,
                                              @PathVariable long commentId){
        try {
            commentService.delete(commentId);
            return new ResponseEntity<>(commentId, HttpStatus.OK);
        }catch (CommentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
