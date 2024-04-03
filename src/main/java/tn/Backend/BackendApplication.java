package tn.Backend;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tn.Backend.auth.AuthenticationService;
import tn.Backend.entites.Administrateur;

@SpringBootApplication
@RequiredArgsConstructor
public class BackendApplication implements CommandLineRunner {
private final AuthenticationService service;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		service.createdefeultadm();
	}
}
