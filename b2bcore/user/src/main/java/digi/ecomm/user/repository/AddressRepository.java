package digi.ecomm.user.repository;

import digi.ecomm.entity.user.Address;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("addressRepository")
@RepositoryRestResource(path = AddressRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = AddressRepository.ITEM_RESOURCE_REL)
public interface AddressRepository extends BaseRepository<Address, Long> {

    String PATH = "addresses";
    String ITEM_RESOURCE_REL = "address";

}
