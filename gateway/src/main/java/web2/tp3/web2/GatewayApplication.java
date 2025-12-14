package web2.tp3.web2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.path;
import static org.springframework.web.servlet.function.RouterFunctions.route;


@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
  /*  @Bean
    public RouterFunction<ServerResponse> getRoutes() {
        return route()
                // Route 1 : Vers Service Critères
                .route(path("/criteres/**"), http("http://service-criteres:8083"))

                // Route 2 : Vers Service Statistiques (AJOUTÉE)
                .route(path("/statistiques/**"), http("http://service-statistiques:8081"))

                .build();
    }*/


}