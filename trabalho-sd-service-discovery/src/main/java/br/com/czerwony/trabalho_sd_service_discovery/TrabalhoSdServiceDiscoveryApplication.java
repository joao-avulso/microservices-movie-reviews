package br.com.czerwony.trabalho_sd_service_discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TrabalhoSdServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoSdServiceDiscoveryApplication.class, args);
	}

}
