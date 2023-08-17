package todolist.list;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication()
@EntityScan(basePackages = {"models"})//models
@EnableJpaRepositories(basePackages = {"repositories"})
@ComponentScan(basePackages = {"controllers", "services", "configs/securityConfiguration"})//controllers
public class ListApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListApplication.class, args);
    }
}
