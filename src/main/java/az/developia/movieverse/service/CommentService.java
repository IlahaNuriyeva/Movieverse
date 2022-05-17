package az.developia.movieverse.service;

import az.developia.movieverse.dto.AddCommentRequest;
import az.developia.movieverse.dto.AddCommentResponse;
import az.developia.movieverse.dto.ReplyCommentRequest;
import az.developia.movieverse.dto.ReplyCommentResponse;

public interface CommentService {
    AddCommentResponse addComment(AddCommentRequest request);

    ReplyCommentResponse replyComment(Long userId, ReplyCommentRequest request);

}
