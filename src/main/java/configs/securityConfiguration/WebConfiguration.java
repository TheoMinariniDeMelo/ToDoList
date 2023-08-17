package configs.securityConfiguration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class WebConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests(
                        authorize -> {
                            authorize.anyRequest().permitAll();
                        }
                ).formLogin(Customizer.withDefaults()).build();
    }

    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
