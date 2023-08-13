package exceptions;

public class NotFoundRequestException extends Exception {
    public NotFoundRequestException(String message) {
        super(message);
    }
}
