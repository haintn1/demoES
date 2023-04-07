package digi.ecomm.commercesearch.provider;

import digi.ecomm.datatransferobject.search.AbstractIndexedSourceItem;

import java.util.List;


public interface IndexedSourceProvider<T extends AbstractIndexedSourceItem> {

    /**
     * Get list of items for indexing.
     *
     * @return list of items or empty list
     */
    List<T> get();

    /**
     * Get an item for updating in the index.
     *
     * @param itemId
     * @return a item or null
     */
    T get(Long itemId);
}
