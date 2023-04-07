package digi.ecomm.pcm.sync.push;

import lombok.Getter;

@Getter
public class PushResult {
    private boolean success;
    private String failedMessage;
    private String externalId;

    private PushResult(final boolean success) {
        this.success = success;
    }

    /**
     * Construct success result.
     *
     * @param externalId
     * @return
     */
    public static PushResult success(final String externalId) {
        final PushResult result = new PushResult(true);
        result.externalId = externalId;
        return result;
    }

    /**
     * Construct failed result.
     *
     * @param failedMessage
     * @return
     */
    public static PushResult failed(final String failedMessage) {
        final PushResult result = new PushResult(false);
        result.failedMessage = failedMessage;
        return result;
    }
}
