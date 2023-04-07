package digi.ecomm.commercesearch.search.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PageableData {
    private int pageSize;
    private int currentPage;
    private String sort;
}
