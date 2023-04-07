package digi.ecomm.searchstandardapi.controller;

import digi.ecomm.commercesearch.exception.NoValidElasticsearchConfigException;
import digi.ecomm.datatransferobject.search.response.ProductDeleteResponse;
import digi.ecomm.datatransferobject.search.response.ProductFullIndexResponse;
import digi.ecomm.datatransferobject.search.response.ProductUpdateResponse;
import digi.ecomm.searchstandardapi.facade.ProductIndexFacade;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/products")
public class ProductIndexController {

    @Resource(name = "productIndexFacade")
    private ProductIndexFacade indexFacade;

    @PostMapping
    public ProductFullIndexResponse fullIndex() throws IOException, NoValidElasticsearchConfigException {
        return indexFacade.fullIndexing();
    }

    @PutMapping(value = "/{productId}")
    public ProductUpdateResponse updateProduct(@PathVariable("productId") final Long productId)
            throws IOException, NoValidElasticsearchConfigException {
        return indexFacade.updateDocument(productId);
    }

    @DeleteMapping(value = "/{productId}")
    public ProductDeleteResponse deleteProduct(@PathVariable("productId") final Long productId)
            throws IOException, NoValidElasticsearchConfigException {
        return indexFacade.deleteDocument(productId);
    }
}
