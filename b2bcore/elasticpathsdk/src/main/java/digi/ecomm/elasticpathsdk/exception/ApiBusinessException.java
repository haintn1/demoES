package digi.ecomm.elasticpathsdk.exception;

import digi.ecomm.elasticpathsdk.model.base.ErrorModel;

public class ApiBusinessException extends RuntimeException {
    private final transient ErrorModel error;

    public ApiBusinessException(final ErrorModel error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return error != null ? error.toString() : "{}";
    }
}