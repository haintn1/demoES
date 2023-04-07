package digi.ecomm.commercesearch.search.populator;

import digi.ecomm.commercesearch.provider.SortFieldNameResolver;
import digi.ecomm.commercesearch.repository.EsSortFieldRepository;
import digi.ecomm.commercesearch.repository.EsSortRepository;
import digi.ecomm.commercesearch.search.data.SearchQueryPageableData;
import digi.ecomm.entity.commercesearch.EsSort;
import digi.ecomm.entity.commercesearch.EsSortField;
import digi.ecomm.platformservice.converter.ConversionException;
import digi.ecomm.platformservice.converter.Populator;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class SearchSourceBuilderSortPopulator implements Populator<SearchQueryPageableData, SearchSourceBuilder> {

    private final EsSortRepository sortRepository;
    private final EsSortFieldRepository sortFieldRepository;
    private final SortFieldNameResolver sortFieldNameResolver;

    @Override
    public void populate(final SearchQueryPageableData source, final SearchSourceBuilder target) throws ConversionException {
        ServicesUtils.validateParameterNotNullStandardMessage("source", source);

        String sortCode = null;

        // Try to get the sort from the pageableData
        if (source.getPageableData() != null && StringUtils.isNotBlank(source.getPageableData().getSort())) {
            sortCode = source.getPageableData().getSort();
        }

        // Fall-back to the last sort used in the searchQueryData
        if (StringUtils.isNotBlank(source.getSearchQueryData().getSort())) {
            sortCode = source.getSearchQueryData().getSort();
        }

        final EsSort sort = sortRepository.findByCodeAndIndex(sortCode, source.getContext().getIndex());
        if (Objects.nonNull(sort)) {
            final List<EsSortField> sortFields = sortFieldRepository.findBySort(sort);
            if (CollectionUtils.isNotEmpty(sortFields)) {
                sortFields.forEach(sortField -> {
                            final String fieldName = sortFieldNameResolver.resolve(sortField);
                            target.sort(new FieldSortBuilder(fieldName)
                                    .order(BooleanUtils.isTrue(sortField.getAscending()) ? SortOrder.ASC : SortOrder.DESC));
                        }
                );
            }
        }
        target.sort(new ScoreSortBuilder().order(SortOrder.DESC));
    }
}
