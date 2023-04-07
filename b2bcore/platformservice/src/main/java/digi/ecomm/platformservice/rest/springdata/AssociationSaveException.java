package digi.ecomm.platformservice.rest.springdata;

public class AssociationSaveException extends RuntimeException {
    public AssociationSaveException() {
    }

    public AssociationSaveException(final String s) {
        super(s);
    }

    public AssociationSaveException(final String s, final Throwable throwable) {
        super(s, throwable);
    }
}
