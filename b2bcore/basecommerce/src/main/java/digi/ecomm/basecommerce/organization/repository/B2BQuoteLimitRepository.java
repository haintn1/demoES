package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BQuoteLimit;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bQuoteLimitRepository")
@RepositoryRestResource(path = B2BQuoteLimitRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BQuoteLimitRepository.ITEM_RESOURCE_REL)
public interface B2BQuoteLimitRepository extends BaseRepository<B2BQuoteLimit, Long> {

    String PATH = "quote-limits";
    String ITEM_RESOURCE_REL = "quote-limit";

}
