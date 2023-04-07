package digi.ecomm.elasticpathsdk.service.pcm.inventory;

import digi.ecomm.elasticpathsdk.context.ApiContext;
import digi.ecomm.elasticpathsdk.model.inventory.*;


public interface InventoryApi {

    /**
     * Create inventory.
     *
     * @param createInventoryRequestModel
     * @param productId
     * @param context
     * @return
     */
    CreateInventoryModel createInventory(CreateInventoryModel createInventoryRequestModel, String productId, ApiContext context);

    /**
     * Get all inventories.
     *
     * @param context
     * @return
     */
    StocksModel getAllInventory(ApiContext context);

    /**
     * Delete product's inventory.
     *
     * @param productId
     * @param context
     */
    void deleteInventory(String productId, ApiContext context);

    /**
     * Update product's inventory.
     *
     * @param stockTransactionRequestModel
     * @param productId
     * @param context
     * @return
     */
    StockTransactionModel updateInventory(StockTransactionModel stockTransactionRequestModel, String productId,
                                          ApiContext context);

    /**
     * Get stock transacstion.
     *
     * @param productId
     * @param transactionId
     * @param context
     * @return
     */
    StockTransactionModel getSingleStockTransaction(String productId, String transactionId, ApiContext context);

    /**
     * Get stock by product.
     *
     * @param productId
     * @param context
     * @return
     */
    StockModel getStockByProductId(String productId, ApiContext context);

    /**
     * Get all stock transacstions.
     *
     * @param productId
     * @param context
     * @return
     */
    StockTransactionsModel getAllProductStockTransactions(String productId, ApiContext context);
}
