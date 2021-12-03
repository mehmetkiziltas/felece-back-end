package com.felece.ticketapi.backend;

import com.felece.ticketapi.backend.entity.User;
import com.felece.ticketapi.backend.entity.UserRole;
import com.felece.ticketapi.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class BackEndApplication {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BackEndApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            User inDB = userService.findByEmail("mehmet13kiziltas@gmail.com");
            if (inDB == null) {
                User user = User.builder()
                        .email("mehmet13kiziltas@gmail.com")
                        .firstName("Mehmet")
                        .lastName("Kızıltas")
                        .password("P4ssword")
                        .username("mehmet13kiziltas")
                        .role(UserRole.ADMIN)
                        .phoneNumber("05555555555")
                        .build();
                userService.saveUser(user);
            }
        };
    }

    @Bean
    public Docket api() {
        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.felece.ticketapi.backend"))
                .build();
    }

}
