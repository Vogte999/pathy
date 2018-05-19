// custon Exception to throw when user tries to connect an edge that already exists
public class IllegalEdgeException extends Exception {
    public IllegalEdgeException() { super(); }
    public IllegalEdgeException(String message) { super(message); }
    public IllegalEdgeException(String message, Throwable cause) { super(message, cause); }
    public IllegalEdgeException(Throwable cause) { super(cause); }
}