package digi.ecomm.pcm.service.impl;

import digi.ecomm.entity.pcm.Product;
import digi.ecomm.pcm.repository.ProductRepository;
import digi.ecomm.pcm.service.ProductService;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> getAllByExternalIds(final Collection<String> externalIds) {
        ServicesUtils.validateParameterNotEmptyStandardMessage("externalIds", externalIds);

        return productRepository.findByExternalIds(externalIds);
    }
}
