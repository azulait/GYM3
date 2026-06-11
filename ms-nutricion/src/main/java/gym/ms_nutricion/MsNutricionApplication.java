package gym.ms_nutricion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsNutricionApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNutricionApplication.class, args);
	}

}
