package web2.tp3;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import web2.tp3.service.UserService;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceUtilisateurApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceUtilisateurApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(UserService userService){
        return args -> {
            userService.createDefaultUsers();
        };
    }
}