package com.cpp.servicebooking.controllers;

import com.cpp.servicebooking.Request.CommentRequest.CommentRequest;
import com.cpp.servicebooking.models.dto.CommentDto;
import com.cpp.servicebooking.services.CommentService;
import com.cpp.servicebooking.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private MapValidationErrorService mapValidationErrorService;

    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{RequestOrderId}")
    public ResponseEntity<?> createComment(@PathVariable String RequestOrderId, @Valid @RequestBody CommentRequest commentRequest, BindingResult result, Principal principal) {
        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
        if(errorMap != null) return errorMap;

        CommentDto commentDto = commentService.saveComment(RequestOrderId, commentRequest, principal.getName());
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{RequestOrderId}")
    public List<CommentDto> getCommentsByRequestId(@PathVariable String RequestOrderId, Principal principal) {
        return commentService.getCommentsByRequestId(RequestOrderId, principal.getName());
    }

    @GetMapping("/id/{CommentId}")
    public ResponseEntity<?> getCommentById(@PathVariable String CommentId, Principal principal) {
        CommentDto commentDto = commentService.findById(CommentId, principal.getName());
        return new ResponseEntity<>(commentDto, HttpStatus.OK);
    }

    @DeleteMapping("/id/{CommentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String CommentId, Principal principal) {
        commentService.deleteComment(CommentId, principal.getName());
        return new ResponseEntity<>("Request with ID: '"+CommentId+"' was deleted", HttpStatus.OK);
    }

    @GetMapping("/check/{RequestOrderId}")
    public ResponseEntity<?> checkDuplicateComment(@PathVariable String RequestOrderId, Principal principal){
        CommentDto commentDto = commentService.checkDuplicateComment(RequestOrderId, principal.getName());
        return ResponseEntity.ok(commentDto);
    }
}
