package pl.hollow.yourdiet.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SpoonacularConfig {

    @Value("${spoonacular.api.endpoint}")
    private String spoonEndpoint;

    @Value("${spoonacular.api.key}")
    private String spoonKey;
}
