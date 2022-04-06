package task.springrestcrudauth;

public class UserNotFoundException extends RuntimeException {
    UserNotFoundException(Long id){
        super("User not found with id: " + id);
    }
}
