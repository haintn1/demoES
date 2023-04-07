package digi.ecomm.platformservice.cronjob.exception;

public class InvalidCronJobException extends RuntimeException {

    /**
     * @param message
     */
    public InvalidCronJobException(final String message) {
        super(message);
    }

    public InvalidCronJobException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
