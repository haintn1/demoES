package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.PaymentInfo;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("paymentInfoRepository")
@RepositoryRestResource(path = PaymentInfoRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PaymentInfoRepository.ITEM_RESOURCE_REL)
public interface PaymentInfoRepository extends BaseRepository<PaymentInfo, Long> {

    String PATH = "payment-infos";
    String ITEM_RESOURCE_REL = "payment-info";

}
