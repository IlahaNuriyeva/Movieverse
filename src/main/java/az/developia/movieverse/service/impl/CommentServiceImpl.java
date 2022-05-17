package az.developia.movieverse.service.impl;

import az.developia.movieverse.service.CommentService;
import az.developia.movieverse.dto.AddCommentRequest;
import az.developia.movieverse.dto.AddCommentResponse;
import az.developia.movieverse.dto.ReplyCommentRequest;
import az.developia.movieverse.dto.ReplyCommentResponse;
import az.developia.movieverse.exception.NotFoundException;
import az.developia.movieverse.mapper.CommentMapper;
import az.developia.movieverse.repository.MovieRepository;
import az.developia.movieverse.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static az.developia.movieverse.exception.ExceptionCode.COMMENT_NOT_FOUND;
@Service
@RequiredArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {
    private MovieRepository movieRepository;
    private CommentRepository commentRepository;


    @Override
    public AddCommentResponse addComment(AddCommentRequest request) {
        log.info("ActionLog.CommentServiceImpl.addComment.start - request: {}", request);

        movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("exception.movie.not-found"));

        var comment = commentRepository
                .save(CommentMapper.INSTANCE.addCommentToComment(request));

        log.info("ActionLog.CommentServiceImpl.addComment.end - request: {}", request);
        return new AddCommentResponse(comment.getId(), comment.getMovie().getId());
    }

    @Transactional
    @Override
    public ReplyCommentResponse replyComment(Long userId, ReplyCommentRequest request) {
        log.info("ActionLog.CommentServiceImpl.replyComment.start - userId: {}, request: {}", userId, request);
        var parentComment = commentRepository.findById(request.getParentId())
                        .orElseThrow(() -> new NotFoundException(COMMENT_NOT_FOUND));

        var comment = CommentMapper.INSTANCE.toEntity(request, userId, parentComment.getMovie().getId());

        log.info("ActionLog.CommentServiceImpl.replyComment.end - userId: {}, request: {}", userId, request);
        return new ReplyCommentResponse(comment.getId());
    }
}
