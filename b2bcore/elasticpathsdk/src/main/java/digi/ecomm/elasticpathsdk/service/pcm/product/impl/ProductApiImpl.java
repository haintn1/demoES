package digi.ecomm.elasticpathsdk.service.pcm.product.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.product.ProductsModel;
import digi.ecomm.elasticpathsdk.request.product.ProductRequest;
import digi.ecomm.elasticpathsdk.response.product.ProductResponse;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.Query;
import digi.ecomm.elasticpathsdk.service.pcm.product.ProductApi;
import org.apache.http.client.config.RequestConfig;

public class ProductApiImpl extends AbstractService implements ProductApi {
    private static final String PRODUCT_ID = ":productId";
    private static final String BASE_PRODUCTS_URL = "/pcm/products";
    private static final String GET_ALL_PRODUCTS_URL = BASE_PRODUCTS_URL;
    private static final String CREATE_PRODUCT_URL = BASE_PRODUCTS_URL;
    private static final String GET_PRODUCT_URL = "/pcm/products/:productId";

    public ProductApiImpl() {
    }

    public ProductApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public ProductApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public ProductsModel getAll(final ApiContext context) {
        return executeGetRequest(GET_ALL_PRODUCTS_URL, ProductsModel.class, context);
    }

    @Override
    public ProductsModel getAll(final Query query, final ApiContext context) {
        return executeGetRequest(GET_ALL_PRODUCTS_URL, query, ProductsModel.class, context);
    }

    @Override
    public ProductResponse create(final ProductRequest payload, final ApiContext context) {
        return executePostRequest(CREATE_PRODUCT_URL, payload, ProductResponse.class, context);
    }

    @Override
    public ProductResponse getById(final String id, final ApiContext context) {
        return executeGetRequest(GET_PRODUCT_URL.replace(PRODUCT_ID, id), ProductResponse.class, context);
    }
}