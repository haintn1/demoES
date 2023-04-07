package digi.ecomm.entity.commercesearch.projection;

import digi.ecomm.entity.AbstractEntityProjection;
import digi.ecomm.entity.Constants;
import digi.ecomm.entity.commercesearch.*;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = Constants.Rest.EXPAND_ALL, types = {EsIndexedProperty.class})
public interface EsIndexedPropertyProjection extends AbstractEntityProjection {

    String getName();

    String getDisplayName();

    EsPropertyType getType();

    EsPropertyType getMultiFieldsType();

    boolean isFacet();

    String getFacetDisplayNameProvider();

    EsIndexedPropertyFacetType getFacetType();

    String getFormat();

    String getFieldValueProvider();

    int getPriority();

    boolean isIndexed();

    boolean isIncludeInResponse();

    Boolean getUseForHighlighting();

    Boolean getUseForAutoCompletion();

    boolean isFtsQuery();

    Float getFtsQueryBoost();

    boolean isFtsFuzzyQuery();

    Integer getFtsFuzzyQueryFuzziness();

    EsIndexedProperty getOuterProperty();

    EsValueRangeSet getValueRangeSet();

    EsIndex getIndex();
}
