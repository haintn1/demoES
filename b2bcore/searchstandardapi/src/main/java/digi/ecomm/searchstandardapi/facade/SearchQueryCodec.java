package digi.ecomm.searchstandardapi.facade;

public interface SearchQueryCodec<QUERY> { //NOSONAR

    /**
     * Decode query string to data.
     *
     * @param query
     * @return
     */
    QUERY decodeQuery(String query);

    /**
     * Encode query data to string.
     *
     * @param query
     * @return
     */
    String encodeQuery(QUERY query);
}
