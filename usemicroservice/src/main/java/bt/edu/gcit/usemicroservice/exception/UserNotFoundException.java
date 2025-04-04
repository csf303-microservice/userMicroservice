package bt.edu.gcit.usemicroservice.exception;

public class UserNotFoundException extends RuntimeException {
 public UserNotFoundException(String message) {
 super(message);
 }
}
