package digi.ecomm.platformservice.email.repository;

import digi.ecomm.entity.email.EmailAddress;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("emailAddressRepository")
@RepositoryRestResource(path = EmailAddressRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EmailAddressRepository.ITEM_RESOURCE_REL)
public interface EmailAddressRepository extends BaseRepository<EmailAddress, Long> {

    String PATH = "email-addresses";
    String ITEM_RESOURCE_REL = "email-address";

}
