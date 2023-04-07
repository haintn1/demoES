package digi.ecomm.pcm.sync.push.exception;

public class FailedDataCreationException extends RuntimeException {

    public FailedDataCreationException() {
    }

    public FailedDataCreationException(final String message) {
        super(message);
    }

    public FailedDataCreationException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
