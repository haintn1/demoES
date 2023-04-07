package digi.ecomm.pcm.sync.push.exception;

public class FailedDataDeletionException extends RuntimeException {

    public FailedDataDeletionException() {
    }

    public FailedDataDeletionException(final String message) {
        super(message);
    }

    public FailedDataDeletionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
