package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.CreditCardPaymentInfo;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("creditCardPaymentInfoRepository")
@RepositoryRestResource(path = CreditCardPaymentInfoRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CreditCardPaymentInfoRepository.ITEM_RESOURCE_REL)
public interface CreditCardPaymentInfoRepository extends BaseRepository<CreditCardPaymentInfo, Long> {

    String PATH = "cc-infos";
    String ITEM_RESOURCE_REL = "cc-info";

}
