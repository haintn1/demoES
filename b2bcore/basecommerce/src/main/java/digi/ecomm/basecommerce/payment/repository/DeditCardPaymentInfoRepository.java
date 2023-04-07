package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.DebitCardPaymentInfo;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("debitCardPaymentInfoRepository")
@RepositoryRestResource(path = DeditCardPaymentInfoRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = DeditCardPaymentInfoRepository.ITEM_RESOURCE_REL)
public interface DeditCardPaymentInfoRepository extends BaseRepository<DebitCardPaymentInfo, Long> {

    String PATH = "db-infos";
    String ITEM_RESOURCE_REL = "db-info";

}
