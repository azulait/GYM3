package gym.ms_horario.Config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPIhorario(){
        return new OpenAPI()
                .info(new Info()
                        .title("API horario")
                        .version("2.0")
                        .description("Administrar horarios"));
    }
}
