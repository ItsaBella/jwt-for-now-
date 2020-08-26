package com.lubanzi.qlitygigs;

import com.lubanzi.qlitygigs.Model.User;
import com.lubanzi.qlitygigs.Repos.UsersRepo;
import com.lubanzi.qlitygigs.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * Application launch point
 * @Author Trust
 */
@SpringBootApplication
public class QlitygigsApplication {

    @Autowired
    UsersRepo usersRepo;

    public static void main(String[] args) {
        SpringApplication.run(QlitygigsApplication.class, args);
    }


    @Bean
    CommandLineRunner runner(){
        return args -> {

            User user = new User();

            user.setuName("Jeannot");
            user.setuSurname("MN");
            user.setuEmail("jmn@jmn.co.za");
            user.setId("JMN");

            //This Password is 'admin' in clear text
            user.setuPassword("$2a$10$mg6nIl4ml48cWwtzakiBtuTJq2MSnZutb61iAAy6j7bmUONi9rNw6");

            usersRepo.save(user);
        };
    }
}
