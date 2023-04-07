package digi.ecomm.commercesearch.index.exception;

public class FacetConfigServiceException extends RuntimeException {

    private final String configName;
    private final String message;

    public FacetConfigServiceException(final String configName, final String message, final Throwable nested) {
        super(message, nested);
        this.message = "Elasticsearch configuration:" + configName + " ," + message;
        this.configName = configName;
    }

    public FacetConfigServiceException(final String configName, final String message) {
        super(message);
        this.configName = configName;
        this.message = "Elasticsearch configuration:" + configName + " ," + message;
    }

    public FacetConfigServiceException(final String message, final Throwable nested) {
        super(message, nested);
        this.configName = "";
        this.message = message;
    }

    public FacetConfigServiceException(final String message) {
        super(message);
        this.configName = "";
        this.message = message;
    }

    public String getConfigName() {
        return this.configName;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}