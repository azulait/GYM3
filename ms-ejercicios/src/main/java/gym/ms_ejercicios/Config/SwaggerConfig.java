package gym.ms_ejercicios.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPIejercicios(){
        return new OpenAPI()
                .info(new Info()
                        .title("API ejercicios")
                        .version("2.0")
                        .description("Administrar ejercicios"));
    }
}
