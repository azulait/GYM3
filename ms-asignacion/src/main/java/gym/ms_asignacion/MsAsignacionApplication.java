package gym.ms_asignacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class MsAsignacionApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsAsignacionApplication.class, args);
	}
}
