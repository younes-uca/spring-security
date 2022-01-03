package com.example.msspringsecurity;

import com.example.msspringsecurity.bean.Role;
import com.example.msspringsecurity.bean.User;
import com.example.msspringsecurity.service.facade.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class MsSpringSecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MsSpringSecurityApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if(true){
            User admin = new User("admin","admin");
            admin.setAuthorities(Arrays.asList(new Role("ROLE_ADMIN")));
            userService.save(admin);

            User chef = new User("chef","chef");
            chef.setAuthorities(Arrays.asList(new Role("ROLE_CHEF")));
            userService.save(chef);
        }

    }
    @Autowired
    private UserService userService;
}
