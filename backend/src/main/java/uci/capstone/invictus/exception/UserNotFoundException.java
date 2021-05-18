package uci.capstone.invictus.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String queryParameter, String value) {
        super("No user found for the query <" + queryParameter + ": " + value + ">");
    }
}
