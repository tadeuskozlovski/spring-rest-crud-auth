package task.springrestcrudauth;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepository;

    UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("user/get/all")
    List<User> getAll() {
        return userRepository.findAll();
    }

    @PostMapping("user/new")
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/user/get/{id}")
    User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping("/user/update/{id}")
    User update(@PathVariable Long id, @RequestBody User newUser) {

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

        return user;
    }

    @DeleteMapping("/user/delete/{id}")
    void deleteUser(@PathVariable Long id){
        // Check user existence before deletion
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        // Deletion can be done another way
        // userRepository.deleteById(id);
        userRepository.delete(user);
    }
}
