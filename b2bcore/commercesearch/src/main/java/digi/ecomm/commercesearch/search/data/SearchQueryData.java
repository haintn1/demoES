package digi.ecomm.commercesearch.search.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class SearchQueryData {
    private String freeTextSearch;
    private List<SearchQueryTermData> filterTerms = new ArrayList<>();
    private String sort;
}

