package digi.ecomm.platformservice.c2l.repository;

import digi.ecomm.entity.c2l.Currency;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("currencyRepository")
@RepositoryRestResource(path = CurrencyRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CurrencyRepository.ITEM_RESOURCE_REL)
public interface CurrencyRepository extends BaseRepository<Currency, Long> {

    String PATH = "currencies";
    String ITEM_RESOURCE_REL = "currency";

    /**
     * Find currency by isocode.
     *
     * @param isocode
     * @return
     */
    Optional<Currency> findByIsocode(String isocode);
}
