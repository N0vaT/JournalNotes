package ru.nova.notesapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nova.notesapi.exception.CommentNotFoundException;
import ru.nova.notesapi.exception.NoteNotFoundException;
import ru.nova.notesapi.model.Comment;
import ru.nova.notesapi.model.CommentComment;
import ru.nova.notesapi.model.CommentNote;
import ru.nova.notesapi.model.Note;
import ru.nova.notesapi.model.dto.CommentDTO;
import ru.nova.notesapi.model.mapper.CommentMapper;
import ru.nova.notesapi.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/notes-api/v1/comments/{commentBeforeId}/comments")
@RequiredArgsConstructor
public class CommentCommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    @GetMapping
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable long commentBeforeId,
                                                        @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                        @RequestParam(required = false, defaultValue = "25") int pageSize,
                                                        @RequestParam(required = false, defaultValue = "DESC") String direction,
                                                        @RequestParam(required = false, defaultValue = "dateOfCreation") String sortByField
    ){
        List<CommentComment> comments = commentService.findAllByCommentBeforeId(commentBeforeId, pageNumber, pageSize, direction, sortByField);
        return ResponseEntity.ok(comments.stream().map(commentMapper::toDto).toList());
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentDTO> getComment(@PathVariable long commentBeforeId,
                                                 @PathVariable long commentId){
        ResponseEntity<CommentDTO> response;
        try {
            Comment commentBefore = commentService.findById(commentBeforeId);
            if(commentBefore.getComments() == null || commentBefore.getComments().stream()
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
    public ResponseEntity<CommentDTO> addComment(@PathVariable long commentBeforeId,
                                                 @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try{
            Comment commentBefore = commentService.findById(commentBeforeId);
            CommentComment comment = commentMapper.toCommentComment(commentDTO);
            comment.setComment(commentBefore);
            response = new ResponseEntity<>(commentMapper.toDto(commentService.create(comment)), HttpStatus.OK);
        }catch (CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentDTO> patchUpdateComment(@PathVariable long commentBeforeId,
                                                         @PathVariable long commentId,
                                                         @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try {
            CommentComment comment = commentMapper.toCommentComment(commentDTO);
            comment.setComment(commentService.findById(commentBeforeId));
            response = new ResponseEntity<>(commentMapper.toDto(commentService.patchUpdate(comment, commentId)), HttpStatus.OK);
        }catch (CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDTO> putUpdateComment(@PathVariable long commentBeforeId,
                                                       @PathVariable long commentId,
                                                       @RequestBody CommentDTO commentDTO){
        ResponseEntity<CommentDTO> response;
        try {
            CommentComment comment = commentMapper.toCommentComment(commentDTO);
            comment.setComment(commentService.findById(commentBeforeId));
            response = new ResponseEntity<>(commentMapper.toDto(commentService.putUpdate(comment, commentId)), HttpStatus.OK);
        }catch (CommentNotFoundException e){
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable long commentBeforeId,
                                              @PathVariable long commentId){
        try {
            commentService.findById(commentBeforeId);
            commentService.delete(commentId);
            return new ResponseEntity<>(commentId, HttpStatus.OK);
        }catch (CommentNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
