package digi.ecomm.elasticpathsdk.service.pcm.product;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.product.ProductsModel;
import digi.ecomm.elasticpathsdk.request.product.ProductRequest;
import digi.ecomm.elasticpathsdk.response.product.ProductResponse;
import digi.ecomm.elasticpathsdk.service.Query;

public interface ProductApi {
    String MAIN_IMAGES = "main_images";

    /**
     * Get all products.
     *
     * @param context
     * @return
     */
    ProductsModel getAll(ApiContext context);

    /**
     * Get all products.
     *
     * @param query
     * @param context
     * @return
     */
    ProductsModel getAll(Query query, ApiContext context);

    /**
     * Create a product.
     *
     * @param request
     * @param context
     * @return
     */
    ProductResponse create(ProductRequest request, ApiContext context);

    /**
     * Get a product.
     *
     * @param id
     * @param context
     * @return
     */
    ProductResponse getById(String id, ApiContext context);
}
