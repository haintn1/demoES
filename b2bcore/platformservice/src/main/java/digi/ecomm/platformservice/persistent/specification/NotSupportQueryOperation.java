package digi.ecomm.platformservice.persistent.specification;

public class NotSupportQueryOperation extends RuntimeException {

    public NotSupportQueryOperation(final String s) {
        super(s);
    }

    public NotSupportQueryOperation(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
