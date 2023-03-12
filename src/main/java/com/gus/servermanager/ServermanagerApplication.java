package com.gus.servermanager;

import com.gus.servermanager.domain.Server;
import com.gus.servermanager.enumeration.Status;
import com.gus.servermanager.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ServermanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServermanagerApplication.class, args);
	}

	@Bean
	CommandLineRunner run(ServerRepository repository) {
		return args -> {
			repository.save(new Server(null,
					"192.168.0.11",
					"Main Ubuntu",
					"16GB",
					"Personal PC",
					"http://localhost:8080/server/image/server1.png",
					Status.SERVER_UP));
			repository.save(new Server(null,
					"192.168.84.145",
					"Xiaomi",
					"8GB",
					"Cellphone",
					"http://localhost:8080/server/image/server2.png",
					Status.SERVER_DOWN));
			repository.save(new Server(null,
					"192.168.84.149",
					"Ubuntu Sound",
					"1GB",
					"Sound",
					"http://localhost:8080/server/image/server3.png",
					Status.SERVER_UP));
		};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
