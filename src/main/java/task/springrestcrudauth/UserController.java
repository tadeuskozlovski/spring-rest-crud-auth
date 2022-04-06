package task.springrestcrudauth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/")
    ResponseEntity<String> index() {
        return new ResponseEntity<String>("Greetings!", HttpStatus.OK);
    }

    @GetMapping("user/get/all")
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping("user/new")
    ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userRepository.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/get/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<User>(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id)),
                HttpStatus.OK);
    }

    @PutMapping("/user/update/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User newUser) {

        User user = userRepository.findById(id)
                /*.map(user -> {
                    user.setName(newUser.getName());
                    user.setRole(newUser.getRole());
                    return userRepository.save(user);
                })*/
                .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(newUser.getName());
        user.setRole(newUser.getRole());
        userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/delete/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        // Check user existence before deletion
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        // Deletion can be done another way
        // userRepository.deleteById(id);
        userRepository.delete(user);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
