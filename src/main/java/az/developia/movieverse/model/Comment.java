package az.developia.movieverse.model;

import lombok.*;

import java.util.Objects;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment extends BaseModel {
    private Long id;
    private Comment parent;
    private User user;
    private Movie movie;
    private Episode episode;
    private String comment;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Comment{");
        sb.append("id=").append(id);
        sb.append(", parent=").append(parent == null ? null : parent.toString());
        sb.append(", user=").append(user);
        sb.append(", movie=").append(movie);
        sb.append(", episode=").append(episode);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
