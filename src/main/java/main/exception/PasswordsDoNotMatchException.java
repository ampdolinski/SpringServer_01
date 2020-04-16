package main.exception;

/**
 * @author Adam Doliński
 * 09.04.2020
 */

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Passwords do not match";
    }
}
