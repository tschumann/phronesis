package phronesis;

import org.natural_selection.www.NaturalSelectionAuthRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private NaturalSelectionAuthRequestFilter naturalSelectionAuthRequestFilter;

    public SecurityConfig(NaturalSelectionAuthRequestFilter naturalSelectionAuthRequestFilter) {
        this.naturalSelectionAuthRequestFilter = naturalSelectionAuthRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(csrf -> csrf.disable());
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(new UnauthorisedAccessHandler())
        );

        http
                .authorizeHttpRequests((auth) -> {
                    auth
                            .requestMatchers("/auth.txt").authenticated()
                            .requestMatchers("/auth/version.txt").authenticated()
                            .requestMatchers("/auth.php").authenticated()
                            .requestMatchers("/auth/").authenticated();

                    auth.anyRequest().permitAll();
                });

        http.addFilterAfter(naturalSelectionAuthRequestFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
