package digi.ecomm.searchstandardapi.populator;

import com.fasterxml.jackson.databind.ObjectMapper;
import digi.ecomm.commercesearch.provider.FieldNameResolver;
import digi.ecomm.commercesearch.search.data.SearchResponseWrapper;
import digi.ecomm.datatransferobject.Pageable;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.entity.commercesearch.EsIndexedProperty;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.lucene.search.TotalHits;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor
public class ProductSearchResponsePopulator implements Populator<SearchResponseWrapper, ProductSearchResponse> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final FieldNameResolver fieldNameResolver;

    @Override
    public void populate(final SearchResponseWrapper source, final ProductSearchResponse target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);
        ServicesUtils.validateParameterNotNullStandardMessage("response", source.getResponse());
        ServicesUtils.validateParameterNotNullStandardMessage("context", source.getContext());

        final SearchResponse response = source.getResponse();
        final SearchHits hits = response.getHits();
        final Pageable pageable = new Pageable();
        if (hits != null) {
            final TotalHits totalHits = hits.getTotalHits();
            if (totalHits != null) {
                pageable.setTotalElements(totalHits.value);
            }
            final Map<String, String> resolvedPropertyMappings =
                    createResolvedPropertyMappings(source.getContext().getIndexedProperties());
            target.setResults(Stream.of(hits.getHits())
                    .map(SearchHit::getSourceAsMap)
                    .map(sourceMap -> objectMapper.convertValue(sourceMap, Map.class))
                    .map(map -> {
                        final Map<Object, Object> resultMap = new LinkedHashMap<>();
                        map.forEach((fieldName, fieldValue) -> {
                            final String originalFieldName = resolvedPropertyMappings.get(fieldName);
                            // Return the original field name when serialize
                            if (!fieldName.equals(originalFieldName)) {
                                resultMap.put(originalFieldName, fieldValue);
                            } else {
                                resultMap.put(fieldName, fieldValue);
                            }
                        });
                        return resultMap;
                    })
                    .collect(Collectors.toList()));
        }
        target.setPage(pageable);
    }

    private Map<String, String> createResolvedPropertyMappings(final List<EsIndexedProperty> properties) {
        return properties.stream()
                .collect(Collectors.toMap(fieldNameResolver::resolve, EsIndexedProperty::getName));
    }
}
