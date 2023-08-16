package configs.securityConfiguration;

import jakarta.servlet.http.HttpServlet;
import org.apache.catalina.filters.HttpHeaderSecurityFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


//@Configuration
public class WebConfiguration {

    @Bean
    SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.anyRequest().authenticated();
                    authorizeConfig.requestMatchers("/users").permitAll();
                }
        ).formLogin(Customizer.withDefaults()).build();
    }
}
