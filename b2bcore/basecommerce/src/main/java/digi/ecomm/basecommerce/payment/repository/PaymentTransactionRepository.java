package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.PaymentTransaction;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("paymentTransactionRepository")
@RepositoryRestResource(path = PaymentTransactionRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PaymentTransactionRepository.ITEM_RESOURCE_REL)
public interface PaymentTransactionRepository extends BaseRepository<PaymentTransaction, Long> {

    String PATH = "payment-transactions";
    String ITEM_RESOURCE_REL = "payment-transaction";

}
