package digi.ecomm.pcm.service;

import digi.ecomm.entity.pcm.Product;

import java.util.Collection;
import java.util.List;

public interface ProductService {

    /**
     * Get all products by external ids.
     *
     * @param externalIds
     * @return
     */
    List<Product> getAllByExternalIds(Collection<String> externalIds);
}
