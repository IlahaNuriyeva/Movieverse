package az.developia.movieverse.model;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseModel{
    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private LoginDetail loginDetail;

}
