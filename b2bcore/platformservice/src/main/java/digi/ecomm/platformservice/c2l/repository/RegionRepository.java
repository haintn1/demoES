package digi.ecomm.platformservice.c2l.repository;

import digi.ecomm.entity.c2l.Country;
import digi.ecomm.entity.c2l.Region;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("regionRepository")
@RepositoryRestResource(path = RegionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = RegionRepository.ITEM_RESOURCE_REL)
public interface RegionRepository extends BaseRepository<Region, Long> {

    String PATH = "regions";
    String ITEM_RESOURCE_REL = "region";

    /**
     * Find region by isocode.
     *
     * @param isocode
     * @return
     */
    Optional<Region> findByIsocode(String isocode);

    /**
     * Find region by country it belongs to.
     *
     * @param country
     * @return
     */
    List<Region> findByCountry(Country country);
}
