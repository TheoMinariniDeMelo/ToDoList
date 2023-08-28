package application;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Locale;
@SpringBootApplication
public class ListApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        SpringApplication.run(ListApplication.class, args);
    }
}