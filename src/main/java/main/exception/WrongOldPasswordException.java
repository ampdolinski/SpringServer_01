package main.exception;

/**
 * @author Adam Doliński
 * 09.04.2020
 */
public class WrongOldPasswordException extends RuntimeException {
    public WrongOldPasswordException() {
        super();
    }

    @Override
    public String getMessage() {
        return "Wrong old password";
    }
}