package digi.ecomm.elasticpathsdk.exception;

public class ApiCommunicationException extends RuntimeException {

    public ApiCommunicationException(final String msg) {
        super(msg);
    }

    public ApiCommunicationException(final String msg, final Throwable exception) {
        super(msg, exception);
    }
}