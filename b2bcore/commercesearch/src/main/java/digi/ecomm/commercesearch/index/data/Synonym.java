package digi.ecomm.commercesearch.index.data;

import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.Getter;

@Getter
public class Synonym {
    private final String from;
    private final String to;

    public Synonym(final String from, final String to) {
        ServicesUtils.validateParameterNotNullStandardMessage("from", from);
        ServicesUtils.validateParameterNotNullStandardMessage("to", to);
        this.from = from;
        this.to = to;
    }
}
