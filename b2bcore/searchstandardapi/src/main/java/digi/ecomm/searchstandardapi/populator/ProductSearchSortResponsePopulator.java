package digi.ecomm.searchstandardapi.populator;

import digi.ecomm.commercesearch.repository.EsSortRepository;
import digi.ecomm.commercesearch.search.data.SearchResponseWrapper;
import digi.ecomm.datatransferobject.search.response.ProductSearchResponse;
import digi.ecomm.datatransferobject.search.response.SearchSort;
import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchSortResponsePopulator implements Populator<SearchResponseWrapper, ProductSearchResponse> {

    private final EsSortRepository sortRepository;

    public ProductSearchSortResponsePopulator(final EsSortRepository sortRepository) {
        this.sortRepository = sortRepository;
    }

    @Override
    public void populate(final SearchResponseWrapper source, final ProductSearchResponse target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);
        ServicesUtils.validateParameterNotNullStandardMessage("response", source.getResponse());
        ServicesUtils.validateParameterNotNullStandardMessage("context", source.getContext());

        final List<EsSort> sorts = sortRepository.findByIndex(source.getContext().getIndex());
        if (CollectionUtils.isNotEmpty(sorts)) {
            target.setSorts(sorts.stream().map(sort -> {
                final SearchSort searchSort = new SearchSort();
                searchSort.setCode(sort.getCode());
                searchSort.setName(sort.getName());
                return searchSort;
            }).collect(Collectors.toList()));
        }
    }
}
