package digi.ecomm.platformservice.email.repository;

import digi.ecomm.entity.email.EmailMessage;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("emailMessageRepository")
@RepositoryRestResource(path = EmaiMessageRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EmaiMessageRepository.ITEM_RESOURCE_REL)
public interface EmaiMessageRepository extends BaseRepository<EmailMessage, Long> {

    String PATH = "email-messages";
    String ITEM_RESOURCE_REL = "email-message";

}
