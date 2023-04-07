package digi.ecomm.commercesearch.search.data;

import digi.ecomm.commercesearch.client.EsContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class SearchQueryPageableData {
    private SearchQueryData searchQueryData;
    private PageableData pageableData;
    private EsContext context;
}
