package todolist.list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@SpringBootApplication
@EntityScan(basePackages = "models")
@EnableJpaRepositories(basePackages = "repositories")
@ComponentScan(basePackages = "controllers")
public class ListApplication {
    public static void main(String[] args) {
        SpringApplication.run(ListApplication.class, args);
    }
}
