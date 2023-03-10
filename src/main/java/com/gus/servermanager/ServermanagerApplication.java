package com.gus.servermanager;

import com.gus.servermanager.domain.Server;
import com.gus.servermanager.enumeration.Status;
import com.gus.servermanager.repo.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

}
