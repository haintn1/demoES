package digi.ecomm.platformservice.c2l.repository;

import digi.ecomm.entity.c2l.Country;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("countryRepository")
@RepositoryRestResource(path = CountryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CountryRepository.ITEM_RESOURCE_REL)
public interface CountryRepository extends BaseRepository<Country, Long> {

    String PATH = "countries";
    String ITEM_RESOURCE_REL = "country";

    /**
     * Find country by isocode.
     *
     * @param isocode
     * @return
     */
    Optional<Country> findByIsocode(String isocode);
}
