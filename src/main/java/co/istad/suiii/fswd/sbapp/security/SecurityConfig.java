package co.istad.suiii.fswd.sbapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor

public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;

    //Build data of User (in memory)
//    @Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
//        UserDetails userAdmin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("admin"))
//                .roles("ADMIN")
//                .build();
//        UserDetails userNormal = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("user"))
//                .roles("NORMAL")
//                .build();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(userAdmin);
//        manager.createUser(userNormal);
//        return manager;
//    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(
                userDetailsService
        );
        provider.setPasswordEncoder(passwordEncoder);
        return  provider;
    }



   //Build Firewall
    @Bean
    public SecurityFilterChain apiSecurity(HttpSecurity http) {
        //TODO: What you want to build
        http.authorizeHttpRequests(
                request -> request
                        .requestMatchers(HttpMethod.POST,"/api/v1/products/**").hasAnyRole("ADMIN","BUSINESS")
                        .requestMatchers(HttpMethod.PUT,"/api/v1/products/**").hasAnyRole("ADMIN","BUSINESS")
                        .requestMatchers(HttpMethod.PATCH,"/api/v1/products/**").hasAnyRole("ADMIN","BUSINESS")
                        .requestMatchers(HttpMethod.DELETE,"/api/v1/products/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/v1/products/**").authenticated()
                        .requestMatchers("/api/v1/orders/**").authenticated()
                        .anyRequest().permitAll()
        );
        // http.formLogin(Customizer.withDefaults());

        //Disable CSRF (Cross Site Request Forgery) token
        http.csrf(AbstractHttpConfigurer::disable);

        //Security Mechanism: HTTP Basic  Authentication
        http.httpBasic(Customizer.withDefaults());
        //session: configure to stateless
        http.sessionManagement( session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}
