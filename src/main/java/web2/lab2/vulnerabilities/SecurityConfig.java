package web2.lab2.vulnerabilities;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/userData").authenticated()  // Samo prijavljeni korisnici mogu pristupiti
                        .anyRequest().permitAll()  // Ostale rute su otvorene za sve
                )
                .oauth2Login(Customizer.withDefaults());  // Koristi OAuth2 login za autentifikaciju

        return http.build();
    }
}
