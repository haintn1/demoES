package digi.ecomm.platformservice.web;

public interface RequestAttributeService {

    String L10N_MODE_ENABLED = "l10nModeEnabled";

    /**
     * Get request attribute.
     *
     * @param name
     * @param <T>
     * @return
     */
    <T> T getAttribute(String name);

    /**
     * Set attribute.
     *
     * @param name
     * @param value
     */
    void setAttribute(String name, Object value);

    /**
     * Remove an attribute.
     *
     * @param name
     */
    void removeAttribute(String name);
}
