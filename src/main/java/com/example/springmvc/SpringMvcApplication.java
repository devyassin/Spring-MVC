package com.example.springmvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class SpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApplication.class, args);
    }

    //@Bean
//    CommandLineRunner start(PatientRepositery patientRepositery){
//        return  args -> {
//        patientRepositery.save(new Patient(null,"Hassan",
//                new Date(),false,120));
//        patientRepositery.save(new Patient(null,"Wahid",
//                    new Date(),false,122));
//        patientRepositery.save(new Patient(null,"karim",
//                    new Date(),false,123));
//        };
//    }

    @Bean
    CommandLineRunner start(JdbcUserDetailsManager jdbcUserDetailsManager){
        PasswordEncoder passwordEncoder=passwordEncoder();
        return  args -> {

            UserDetails user1=jdbcUserDetailsManager.loadUserByUsername("yassine");
            UserDetails user2=jdbcUserDetailsManager.loadUserByUsername("ahmed");
            if(user1==null){
                jdbcUserDetailsManager.createUser(User.withUsername("yassine").
                        password(passwordEncoder.encode("1234")).roles("USER","ADMIN").build());
            }

            if(user2==null){
                jdbcUserDetailsManager.createUser(User.withUsername("ahmed").
                        password(passwordEncoder.encode("1234")).roles("USER").build());
            }

        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
