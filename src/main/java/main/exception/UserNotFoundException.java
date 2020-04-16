package main.exception;

/**
 * @author Adam Doliński
 * 09.04.2020
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }

}
