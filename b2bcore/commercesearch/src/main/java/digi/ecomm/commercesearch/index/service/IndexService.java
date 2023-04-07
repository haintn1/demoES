package digi.ecomm.commercesearch.index.service;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;

import java.io.IOException;

public interface IndexService {

    /**
     * Full index. It will delete the existing indices and create them again.
     * Then all documents will be indexed in search engine.
     * @throws IOException
     */
    void fullIndexing() throws IOException, NoValidElasticsearchConfigException;

    /**
     * Update a document in search engine.
     *
     * @param productId
     * @throws IOException
     */
    void updateDocument(Long productId) throws IOException, NoValidElasticsearchConfigException;

    /**
     * Delete a document from search engine.
     *
     * @param productId
     * @throws IOException
     */
    void deleteDocument(Long productId) throws IOException, NoValidElasticsearchConfigException;
}
