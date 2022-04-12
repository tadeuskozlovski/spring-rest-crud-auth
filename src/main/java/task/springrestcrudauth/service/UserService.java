package task.springrestcrudauth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import task.springrestcrudauth.entity.User;
import task.springrestcrudauth.handler.UserExistsException;
import task.springrestcrudauth.handler.UserNotFoundException;
import task.springrestcrudauth.init.LoadDatabase;
import task.springrestcrudauth.repository.RoleRepository;
import task.springrestcrudauth.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User create(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            throw new UserExistsException(user.getUsername());

        return userRepository.save(user);
    }

    public User edit(Long id, User user) {
        User userToChange = userRepository.getById(id);

        if (userToChange == null)
            throw new UserNotFoundException(id);

        userToChange
                .setUsername(user.getUsername())
                .setPassword(user.getPassword())
                .setRoles(user.getRoles());

        userRepository.save(userToChange);

        return userToChange;
    }

    public void delete(Long id) {
        userRepository.delete(userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFoundException(id))
        );
    }
}
