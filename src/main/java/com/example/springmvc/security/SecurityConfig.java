package com.example.springmvc.security;

import com.example.springmvc.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private PasswordEncoder passwordEncoder;
    private UserDetailsServiceImpl userDetailsServiceImpl;


 //  @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }


    //@Bean
//    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
//        return  new InMemoryUserDetailsManager(
//                User.withUsername("user1").password(passwordEncoder.encode("1234")).roles("USER").build(),
//                User.withUsername("user2").password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build()
//        );
//    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.formLogin();
//        // pour personnaliser l'authentification
////        httpSecurity.formLogin().loginPage("/login").permitAll();
//        httpSecurity.rememberMe();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/webjars/**","/h2-console/**").permitAll();
//        httpSecurity.authorizeHttpRequests().requestMatchers("/user/**").hasRole("USER");
//        httpSecurity.authorizeHttpRequests().requestMatchers("/admin/**").hasRole("ADMIN");
//        httpSecurity.authorizeHttpRequests().anyRequest().authenticated();
//        httpSecurity.exceptionHandling().accessDeniedPage("/notAuthorized");
//        httpSecurity.userDetailsService(userDetailsServiceImpl);
//        return httpSecurity.build();
//    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(Customizer.withDefaults())
                .authorizeHttpRequests(ar->ar.requestMatchers("/","/webjars/**","/h2-console/**").permitAll())
                .authorizeHttpRequests(ar->ar.anyRequest().authenticated())
                .oauth2Login(Customizer.withDefaults())
                .logout((logout)->logout
                        .logoutSuccessUrl("/").permitAll().deleteCookies("JSESSIONID"))
                .build();
    }
}
