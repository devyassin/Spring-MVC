package com.example.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
