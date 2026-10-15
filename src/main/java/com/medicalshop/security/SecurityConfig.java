//package com.medicalshop.security;
////import com.medicalshop.repository.UserRepository; import org.springframework.context.annotation.*; import org.springframework.security.authentication.*;
////import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
////import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
////@Configuration public class SecurityConfig{
//// @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
//// @Bean UserDetailsService uds(UserRepository r){return u->r.findByUsername(u).map(x->User.withUsername(x.getUsername()).password(x.getPassword()).roles(x.getRole().name()).build()).orElseThrow();}
//// @Bean AuthenticationProvider provider(UserDetailsService u,PasswordEncoder p){DaoAuthenticationProvider a=new DaoAuthenticationProvider();a.setUserDetailsService(u);a.setPasswordEncoder(p);return a;}
//// @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
//// @Bean SecurityFilterChain filterChain(HttpSecurity h,JwtAuthenticationFilter f,AuthenticationProvider p)throws Exception{
////  return h.csrf(x->x.disable()).sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(p)
////  .authorizeHttpRequests(x->x.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()).addFilterBefore(f,UsernamePasswordAuthenticationFilter.class).build();
//// }
////}
//import java.util.List;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import com.medicalshop.entity.User;
//import com.medicalshop.repository.UserRepository;
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    UserDetailsService uds(UserRepository repository) {
//        return username -> {
//            User user = repository.findByUsername(username)
//                    .orElseThrow(() -> new UsernameNotFoundException(
//                            "User not found: " + username
//                    ));
//
//            return org.springframework.security.core.userdetails.User
//                    .withUsername(user.getUsername())
//                    .password(user.getPassword())
//                    .roles(user.getRole().name())
//                    .build();
//        };
//    }
//
//    @Bean
//    AuthenticationProvider provider(
//            UserDetailsService u,
//            PasswordEncoder p) {
//
//        DaoAuthenticationProvider a = new DaoAuthenticationProvider();
//        a.setUserDetailsService(u);
//        a.setPasswordEncoder(p);
//        return a;
//    }
//
//    @Bean
//    AuthenticationManager authenticationManager(
//            AuthenticationConfiguration c) throws Exception {
//        return c.getAuthenticationManager();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//
//        CorsConfiguration configuration = new CorsConfiguration();
//
//        configuration.setAllowedOriginPatterns(List.of("*"));
//        configuration.setAllowedMethods(
//                List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")
//        );
//        configuration.setAllowedHeaders(List.of("*"));
//        configuration.setAllowCredentials(false);
//
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//
//        source.registerCorsConfiguration("/**", configuration);
//
//        return source;
//    }
//
//    @Bean
//    SecurityFilterChain filterChain(
//            HttpSecurity http,
//            JwtAuthenticationFilter jwtFilter,
//            AuthenticationProvider provider) throws Exception {
//
//        return http
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(session ->
//                        session.sessionCreationPolicy(
//                                SessionCreationPolicy.STATELESS
//                        )
//                )
//                .authenticationProvider(provider)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/auth/**").permitAll()
//                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .addFilterBefore(
//                        jwtFilter,
//                        UsernamePasswordAuthenticationFilter.class
//                )
//                .build();
//    }
//}
package com.medicalshop.security;
import com.medicalshop.repository.UserRepository; import org.springframework.context.annotation.*; import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy; import org.springframework.security.core.userdetails.*; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.security.web.SecurityFilterChain; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableMethodSecurity public class SecurityConfig{
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService uds(UserRepository r){return u->r.findByUsername(u).map(x->User.withUsername(x.getUsername()).password(x.getPassword()).roles(x.getRole().name()).build()).orElseThrow();}
 @Bean AuthenticationProvider provider(UserDetailsService u,PasswordEncoder p){DaoAuthenticationProvider a=new DaoAuthenticationProvider();a.setUserDetailsService(u);a.setPasswordEncoder(p);return a;}
 @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration c)throws Exception{return c.getAuthenticationManager();}
 @Bean SecurityFilterChain filterChain(HttpSecurity h,JwtAuthenticationFilter f,AuthenticationProvider p)throws Exception{
  return h.csrf(x->x.disable()).sessionManagement(x->x.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).authenticationProvider(p)
  .authorizeHttpRequests(x->x.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated()).addFilterBefore(f,UsernamePasswordAuthenticationFilter.class).build();
 }
}
