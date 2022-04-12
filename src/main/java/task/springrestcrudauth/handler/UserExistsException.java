package task.springrestcrudauth.handler;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String username){
        super("User exists: " + username);
    }
}
