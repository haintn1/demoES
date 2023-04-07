package digi.ecomm.commercesearch.index.exception;

public class FieldValueProviderException extends FacetConfigServiceException {

    public FieldValueProviderException(final String message, final Throwable nested) {
        super(message, nested);
    }

    public FieldValueProviderException(final String message) {
        super(message);
    }
}
