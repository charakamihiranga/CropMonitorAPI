package lk.ijse.springboot.cropmonitorapi.config;

import lk.ijse.springboot.cropmonitorapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtFilter jwtFilter;
    private final UserService userService;

    /*
   This method configures the security settings for the Spring Boot application.
   It disables CSRF protection, enables CORS with default settings, and sets up authorization rules.
   Specifically, it allows unauthenticated access to the `/api/v1/auth/**` and `/api/v1/healthTest` endpoints,
   while requiring authentication for all other requests. It also configures the session management to be stateless and
   adds a custom JWT filter (`jwtFilter`) before the `UsernamePasswordAuthenticationFilter` to handle JWT-based
   authentication.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("api/v1/auth/**").permitAll()
                        // crop management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/crop/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.GET, "/api/v1/crop/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/crop/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/crop/**").hasAnyRole("MANAGER", "SCIENTIST")
                        // equipment management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/equipment/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/equipment/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/equipment/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/equipment/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        // field management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/field/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.GET, "/api/v1/field/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/field/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/field/**").hasAnyRole("MANAGER", "SCIENTIST")
                        // log management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/monitoringlog/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.GET, "/api/v1/monitoringlog/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/monitoringlog/**").hasAnyRole("MANAGER", "SCIENTIST")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/monitoringlog/**").hasAnyRole("MANAGER", "SCIENTIST")
                        // staff management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/staff/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/staff/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/staff/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/staff/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        // vehicle management endpoints
                        .requestMatchers(HttpMethod.POST, "/api/v1/vehicle/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.GET, "/api/v1/vehicle/**").hasAnyRole("MANAGER", "SCIENTIST", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.PATCH, "/api/v1/vehicle/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/vehicle/**").hasAnyRole("MANAGER", "ADMINISTRATIVE")
                        .anyRequest().authenticated())
                        .sessionManagement(session -> session
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                        .build();
    }
    /**
     * This method configures the authentication provider for the application.
     * It uses a `DaoAuthenticationProvider` which retrieves user details from the `UserService`.
     * The password encoder used is `BCryptPasswordEncoder` with a strength of 12.
     * This setup ensures that user authentication is handled securely.
     */
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService.userDetailsService());
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return provider;
    }
    /**
     * This method provides the `AuthenticationManager` bean for the application.
     * It retrieves the `AuthenticationManager` from the `AuthenticationConfiguration`.
     * This manager is used to handle authentication processes in the application.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
