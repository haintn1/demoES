package digi.ecomm.commercesearch.provider.impl;

import digi.ecomm.commercesearch.SearchProperties;
import digi.ecomm.commercesearch.index.provider.data.IndexedProduct;
import digi.ecomm.commercesearch.provider.IndexedSourceProvider;
import digi.ecomm.datatransferobject.product.response.B2bProduct;
import digi.ecomm.platformservice.converter.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
public class ExternalIndexedProductProviderImpl implements IndexedSourceProvider<IndexedProduct> {

    private final RestTemplate restTemplate;
    private final SearchProperties searchProperties;
    private final Converter<B2bProduct, IndexedProduct> indexedProductSourceConverter;

    @Override
    public List<IndexedProduct> get() {
        final B2bProduct[] products = restTemplate.getForObject(searchProperties.getProduct().getIndexEndpoint(), B2bProduct[].class);
        if (Objects.isNull(products)) {
            return Collections.emptyList();
        }
        return indexedProductSourceConverter.convertAll(Stream.of(products).collect(Collectors.toList()));
    }

    @Override
    public IndexedProduct get(final Long productId) {
        String getProductEndpoint = searchProperties.getProduct().getIndexEndpoint() + "/" + productId;
        return indexedProductSourceConverter.convert(restTemplate.getForObject(getProductEndpoint, B2bProduct.class));
    }
}
