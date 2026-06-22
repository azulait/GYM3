package gym.ms_nutricion.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPIentrenador(){
        return new OpenAPI()
                .info(new Info()
                        .title("API Plan Nutricion")
                        .version("2.0")
                        .description("Administrar Planes de nutricion"));
    }
}
