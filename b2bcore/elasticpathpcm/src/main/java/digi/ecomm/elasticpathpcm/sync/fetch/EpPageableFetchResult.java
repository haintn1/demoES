package digi.ecomm.elasticpathpcm.sync.fetch;

import lombok.Getter;

import java.util.List;

@Getter
public class EpPageableFetchResult<T> {
    private List<T> results;
    private int currentPage;
    private long totalPages;

    public EpPageableFetchResult(final List<T> results, final int currentPage, final long totalPages) {
        this.results = results;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }
}
