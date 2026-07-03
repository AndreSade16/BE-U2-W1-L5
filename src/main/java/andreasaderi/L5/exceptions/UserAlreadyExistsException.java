package andreasaderi.L5.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String email) {
        super("Email " + email + " already in use.");
    }
}
