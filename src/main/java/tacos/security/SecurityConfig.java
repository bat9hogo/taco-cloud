package tacos.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import tacos.User;
import tacos.data.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User ‘" + username + "’ not found");
        };
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                        .requestMatchers("/design", "/orders", "/design").hasRole("USER")  // Вместо antMatchers()
                        .anyRequest().permitAll()  // Все остальные запросы разрешены
                )
                .formLogin(form -> form
                        .loginPage("/login")  // Указание страницы логина
                        .permitAll()  // Разрешение всем пользователям доступ к странице логина
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")  // URL после успешного выхода
                        .permitAll()  // Разрешение всем пользователям доступ к выходу
                )
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")  // Отключение CSRF для H2
                )
                .headers(headers -> headers
                        // do not use any default headers unless explicitly listed
                        .defaultsDisabled()
                        .cacheControl(withDefaults())
                );


        return http.build();
    }


}