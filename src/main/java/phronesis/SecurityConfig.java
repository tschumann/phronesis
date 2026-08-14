package phronesis;

import com.teamfortress.www.TeamFortress2AuthRequestFilter;
import org.natural_selection.www.NaturalSelectionAuthRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final NaturalSelectionAuthRequestFilter naturalSelectionAuthRequestFilter;

    private final TeamFortress2AuthRequestFilter teamFortress2AuthRequestFilter;

    public SecurityConfig(NaturalSelectionAuthRequestFilter naturalSelectionAuthRequestFilter, TeamFortress2AuthRequestFilter teamFortress2AuthRequestFilter) {
        this.naturalSelectionAuthRequestFilter = naturalSelectionAuthRequestFilter;
        this.teamFortress2AuthRequestFilter = teamFortress2AuthRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.csrf(AbstractHttpConfigurer::disable);
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(new UnauthorisedAccessHandler())
        );
        http.headers(headers -> headers
                // allow frames from the same origin so H2-console works
                .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
        );
        http.authorizeHttpRequests((auth) -> {
            auth
                    .requestMatchers("/auth.txt").authenticated()
                    .requestMatchers("/auth/version.txt").authenticated()
                    .requestMatchers("/auth.php").authenticated()
                    .requestMatchers("/auth/").authenticated()
                    .requestMatchers("/ISDK/GetEquipment/v0001").authenticated()
                    .requestMatchers("/ISDK/GetInventory/v0001").authenticated();
            auth.anyRequest().permitAll();
        });

        http.addFilterAfter(naturalSelectionAuthRequestFilter, BasicAuthenticationFilter.class);
        http.addFilterAfter(teamFortress2AuthRequestFilter, BasicAuthenticationFilter.class);

        return http.build();
    }
}
