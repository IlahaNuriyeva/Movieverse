package az.developia.movieverse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplyCommentRequest {
    @NotNull
    private Long parentId;
    @NotNull
    private Long movieId;

    @NotBlank
    @Size(min = 1, max = 140)
    private String comment;
}
