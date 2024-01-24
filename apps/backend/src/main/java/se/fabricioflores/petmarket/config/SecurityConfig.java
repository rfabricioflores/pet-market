package se.fabricioflores.petmarket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  private final JwtAuthFilter jwtAuthFilter;
  private final UnauthorizedEntryPoint unauthorizedEntryPoint;

  public SecurityConfig(JwtAuthFilter jwtAuthFilter, UnauthorizedEntryPoint unauthorizedEntryPoint) {
    this.jwtAuthFilter = jwtAuthFilter;
    this.unauthorizedEntryPoint = unauthorizedEntryPoint;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .cors(AbstractHttpConfigurer::disable)
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
      .authorizeHttpRequests(authorize ->
        authorize
        .requestMatchers(HttpMethod.POST, "/api/login").permitAll()
        .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
        .anyRequest().authenticated()
      )
      .exceptionHandling(customizer -> customizer.authenticationEntryPoint(unauthorizedEntryPoint));

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
