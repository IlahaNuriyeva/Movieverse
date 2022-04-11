package az.developia.movieverse.model;

import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Actor {
    private Long id;
    private String keyId;
    private String name;
    private LocalDate birthDate;
    private String summary;
}
