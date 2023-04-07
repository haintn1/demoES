package digi.ecomm.pcm.sync.push.exception;

public class UntrackedDataDeletionException extends RuntimeException {

    public UntrackedDataDeletionException() {
    }

    public UntrackedDataDeletionException(final String message) {
        super(message);
    }

    public UntrackedDataDeletionException(final String message, final Throwable throwable) {
        super(message, throwable);
    }
}
