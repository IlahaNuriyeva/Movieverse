package az.developia.movieverse.mapper;

import az.developia.movieverse.dto.AddCommentRequest;
import az.developia.movieverse.dto.ReplyCommentRequest;
import az.developia.movieverse.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class CommentMapper {
    public static final CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "user.id", source = "request.userId")
    @Mapping(target = "movie.id", source = "request.movieId")
    @Mapping(target = "comment", source = "request.comment")
    public abstract Comment addCommentToComment(AddCommentRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parent.id", source = "request.parentId")
    @Mapping(target = "user.id", source = "userId")
    @Mapping(target = "movie.id", source = "movieId")
    @Mapping(target = "comment", source = "request.comment")
    public abstract Comment toEntity(ReplyCommentRequest request, Long userId, Long movieId);


}
