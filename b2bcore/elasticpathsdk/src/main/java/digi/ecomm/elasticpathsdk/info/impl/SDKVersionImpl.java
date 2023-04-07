package digi.ecomm.elasticpathsdk.info.impl;

import digi.ecomm.elasticpathsdk.info.SDKVersion;

public class SDKVersionImpl implements SDKVersion {

    private static final String SDK_ID = "Elastic-Path-Commerce-Cloud-SDK";

    private static final String SDK_VERSION = "2/22/2022";

    /**
     * SDK id.
     *
     * @return
     */
    public String getSDKId() {
        return SDK_ID;
    }

    /**
     * SDK version.
     *
     * @return
     */
    public String getSDKVersion() {
        return SDK_VERSION;
    }
}
