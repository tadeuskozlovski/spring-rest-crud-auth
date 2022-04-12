package task.springrestcrudauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.springrestcrudauth.handler.UserNotFoundException;
import task.springrestcrudauth.repository.UserRepository;
import task.springrestcrudauth.entity.User;
import task.springrestcrudauth.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    ResponseEntity<String> index() {
        return new ResponseEntity<String>("Greetings!", HttpStatus.OK);
    }

    @GetMapping("user/get/all")
    ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
    }

    @PutMapping("user/new")
    ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.create(user), HttpStatus.CREATED);
    }

    @GetMapping("/user/get/{id}")
    ResponseEntity<User> getUserById(@PathVariable Long id) {
        return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
    }

    @PutMapping("/user/update/{id}")
    ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<User>(userService.edit(id, user), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/user/delete/{id}")
    ResponseEntity deleteUser(@PathVariable Long id){
        userService.delete(id);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
