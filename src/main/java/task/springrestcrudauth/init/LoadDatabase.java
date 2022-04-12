package task.springrestcrudauth.init;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import task.springrestcrudauth.entity.Role;
import task.springrestcrudauth.entity.User;
import task.springrestcrudauth.service.UserService;

import java.util.Collections;
import java.util.Set;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //spring boot run all CommandLineRunner beans once after app context is loaded
    @Bean
    CommandLineRunner initDatabase(UserService userService){
        return args -> {

            log.info(userService.create(new User("user", "user", Collections.singleton(new Role("ROLE_USER")))).toString());
            log.info(userService.create(new User("admin", "admin", Collections.singleton(new Role("ROLE_ADMIN")))).toString());
            log.info(userService.create(new User("editor", "editor", Collections.singleton(new Role("ROLE_EDITOR")))).toString());
        };
    }
}
