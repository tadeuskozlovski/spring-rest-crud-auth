package task.springrestcrudauth;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //spring boot run all CommandLineRunner beans once after app context is loaded
    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            // userRepository.save method return created user and log.info uses User.toString method to convert user to String representation
            log.info("Creating and saving: " + userRepository.save(new User("User1", "USER")));
            log.info("Creating and saving: " + userRepository.save(new User("User2", "USER")));
            log.info("Creating and saving: " + userRepository.save(new User("User3", "USER")));
            log.info("Creating and saving: " + userRepository.save(new User("User4", "ADMIN")));
            log.info("Creating and saving: " + userRepository.save(new User("User5", "MODERATOR")));
            log.info("Creating and saving: " + userRepository.save(new User("User6", "GUEST")));
        };
    }
}
