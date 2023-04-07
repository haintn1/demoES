package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.PaymentTransactionEntry;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("paymentTransactionEntryRepository")
@RepositoryRestResource(path = PaymentTransactionEntryRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = PaymentTransactionEntryRepository.ITEM_RESOURCE_REL)
public interface PaymentTransactionEntryRepository extends BaseRepository<PaymentTransactionEntry, Long> {

    String PATH = "payment-transaction-entries";
    String ITEM_RESOURCE_REL = "payment-transaction-entry";

}
