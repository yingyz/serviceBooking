package com.cpp.servicebooking;

import com.cpp.servicebooking.util.CommentComparator;
import com.cpp.servicebooking.util.DistanceCalculator;
import com.cpp.servicebooking.util.RequestOrderComparator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ServicebookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicebookingApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public DistanceCalculator distanceCalculator() {return new DistanceCalculator();}

    @Bean
    public RequestOrderComparator requestOrderComparator() {return new RequestOrderComparator();}

    @Bean
    public CommentComparator commentComparator() {return new CommentComparator();}
}
