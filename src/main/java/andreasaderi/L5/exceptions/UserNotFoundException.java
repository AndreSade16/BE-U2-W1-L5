package andreasaderi.L5.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("No user with id " + userId + " found.");
    }
}
