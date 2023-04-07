package digi.ecomm.searchstandardapi.exception;

public abstract class WebserviceException extends RuntimeException {
    private final String reason;
    private final String subject;

    protected WebserviceException(final String message) {
        super(message);
        this.reason = null;
        this.subject = null;
    }

    protected WebserviceException(final String message, final String reason) {
        super(message);
        this.reason = reason;
        this.subject = null;
    }

    protected WebserviceException(final String message, final String reason, final Throwable cause) {
        super(message, cause);
        this.reason = reason;
        this.subject = null;
    }

    protected WebserviceException(final String message, final String reason, final String subject) {
        super(message);
        this.reason = reason;
        this.subject = subject;
    }

    protected WebserviceException(final String message, final String reason, final String subject, final Throwable cause) {
        super(message, cause);
        this.reason = reason;
        this.subject = subject;
    }

    public String getReason() {
        return this.reason;
    }

    public String getSubject() {
        return this.subject;
    }

    /**
     * Get type.
     *
     * @return
     */
    public abstract String getType();

    /**
     * get subject type.
     *
     * @return
     */
    public abstract String getSubjectType();
}
