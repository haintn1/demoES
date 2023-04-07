package digi.ecomm.platformservice.persistent.interceptor;

public class InterceptorException extends RuntimeException {

    public InterceptorException() {
    }

    public InterceptorException(final String s) {
        super(s);
    }

    public InterceptorException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
