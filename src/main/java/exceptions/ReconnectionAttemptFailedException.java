package exceptions;

public class ReconnectionAttemptFailedException extends Throwable {
    public ReconnectionAttemptFailedException() {
        super("RECONNECTION_ATTEMPT_FAILED");
    }
}
