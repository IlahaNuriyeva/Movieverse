package az.developia.movieverse.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Episode extends BaseModel {
    private Long id;
    private String title;
    private String description;




}
