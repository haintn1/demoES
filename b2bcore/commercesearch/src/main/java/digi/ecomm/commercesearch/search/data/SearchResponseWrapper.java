package digi.ecomm.commercesearch.search.data;

import digi.ecomm.commercesearch.client.EsContext;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.elasticsearch.action.search.SearchResponse;

@NoArgsConstructor
@Getter
@Setter
public class SearchResponseWrapper {
    private SearchResponse response;
    private EsContext context;
}
