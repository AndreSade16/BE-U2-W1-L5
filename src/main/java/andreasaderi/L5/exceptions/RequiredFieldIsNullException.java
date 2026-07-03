package andreasaderi.L5.exceptions;

public class RequiredFieldIsNullException extends RuntimeException {
    public RequiredFieldIsNullException(String message) {
        super(message);
    }
}
