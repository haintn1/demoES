package digi.ecomm.commercesearch.exception;

/**
 * Throws when {@link digi.ecomm.entity.commercesearch.EsFacetSearchConfig}
 * cannot resolve suitable Elasticsearch index config for the current.
 * context.
 */
public class NoValidElasticsearchConfigException extends Exception {

    /**
     * @param message
     */
    public NoValidElasticsearchConfigException(final String message) {
        super(message);
    }

}
