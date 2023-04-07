package digi.ecomm.elasticpathsdk.service.pcm.inventory.impl;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.inventory.*;
import digi.ecomm.elasticpathsdk.service.AbstractService;
import digi.ecomm.elasticpathsdk.service.pcm.inventory.InventoryApi;
import org.apache.http.client.config.RequestConfig;


public class InventoryApiImpl extends AbstractService implements InventoryApi {
    private static final String PRODUCT_ID = ":productId";
    private static final String TRANSACTION_ID = ":transactionId";
    private static final String CREATE_INVENTORY_URL = "/v2/inventories/:productId";
    private static final String GET_ALL_INVENTORY_URL = "/v2/inventories";
    private static final String DELETE_INVENTORY_URL = "/v2/inventories/:productId";
    private static final String UPDATE_INVENTORY_URL = "/v2/inventories/:productId/transactions";
    private static final String GET_SINGLE_STOCK_TRANSACTION = "/v2/inventories/:productId/transactions/:transactionId";
    private static final String GET_STOCK_BY_PRODUCT_ID = "/v2/inventories/:productId";
    private static final String GET_ALL_PRODUCT_STOCK_TRANSACTIONS = "/v2/inventories/:productId/transactions";

    public InventoryApiImpl() {
    }

    public InventoryApiImpl(final int connectTimeout, final int socketTimeout) {
        super(connectTimeout, socketTimeout);
    }

    public InventoryApiImpl(final RequestConfig requestConfig) {
        super(requestConfig);
    }

    @Override
    public CreateInventoryModel createInventory(final CreateInventoryModel createInventoryRequestModel,
                                                final String productId, final ApiContext context) {
        return executePostRequest(CREATE_INVENTORY_URL.replace(PRODUCT_ID, productId),
                createInventoryRequestModel, CreateInventoryModel.class, context);
    }

    @Override
    public StocksModel getAllInventory(final ApiContext context) {
        return executeGetRequest(GET_ALL_INVENTORY_URL, StocksModel.class, context);
    }

    @Override
    public void deleteInventory(final String productId, final ApiContext context) {
        executeDeleteRequest(DELETE_INVENTORY_URL.replace(PRODUCT_ID, productId), context);
    }

    @Override
    public StockTransactionModel updateInventory(final StockTransactionModel stockTransactionRequestModel,
                                                 final String productId, final ApiContext context) {
        return executePutRequest(UPDATE_INVENTORY_URL.replace(PRODUCT_ID, productId),
                stockTransactionRequestModel, StockTransactionModel.class, context);
    }

    @Override
    public StockTransactionModel getSingleStockTransaction(final String productId, final String transactionId,
                                                           final ApiContext context) {
        return executeGetRequest(GET_SINGLE_STOCK_TRANSACTION.replace(PRODUCT_ID, productId)
                .replace(TRANSACTION_ID, transactionId), StockTransactionModel.class, context);
    }

    @Override
    public StockModel getStockByProductId(final String productId, final ApiContext context) {
        return executeGetRequest(GET_STOCK_BY_PRODUCT_ID.replace(PRODUCT_ID, productId),
                StockModel.class, context);
    }

    @Override
    public StockTransactionsModel getAllProductStockTransactions(final String productId, final ApiContext context) {
        return executeGetRequest(GET_ALL_PRODUCT_STOCK_TRANSACTIONS.replace(PRODUCT_ID, productId),
                StockTransactionsModel.class, context);
    }
}
