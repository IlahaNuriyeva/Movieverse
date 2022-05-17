package az.developia.movieverse.controller;

import az.developia.movieverse.dto.*;
import az.developia.movieverse.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.Valid;
import java.security.Principal;


@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<AddCommentResponse> addComment(
            @Valid @RequestBody AddCommentRequest request,
            Principal principal
    ) {
        var userDetails = (UsernamePasswordAuthenticationToken) principal;
        request.setUserId((Long) userDetails.getPrincipal());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.addComment(request));
    }

    @PostMapping("/reply")
    public ResponseEntity<ReplyCommentResponse> replyComment(
            @RequestHeader(HeaderKeys.USER_ID) Long userId,
            @RequestBody ReplyCommentRequest request
            ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.replyComment(userId, request));
    }


}
