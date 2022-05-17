package az.developia.movieverse.model;

import lombok.*;

import java.time.Year;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(exclude = {"actors", "episodes", "directors"})
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie extends BaseModel {
    private Long id;
    private String keyId;
    private String title;
    private String plot;
    private Short year;
    private Long runtimeMins;
    private Set<Episode> episodes;
    private Rating rating;
    private Set<Actor> actors;
    private Set<Director> directors;
}
