package az.developia.movieverse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mail {
    @Builder.Default
    private String from = "movieverse@gmail.com";
    private String to;
    private String subject;
    private String body;
    @Builder.Default
    private boolean html = false;
    private Template template;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Template{
        private String path;
        private Map<String, Object> params;
    }
}

