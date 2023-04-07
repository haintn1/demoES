package digi.ecomm.elasticpathpcm.api;

import digi.ecomm.elasticpathsdk.context.ApiContext;

public interface ApiContextFactory {

    /**
     * Create EP API context. Should get a new context for every API call to get the latest information.
     *
     * @return
     */
    ApiContext get();
}
