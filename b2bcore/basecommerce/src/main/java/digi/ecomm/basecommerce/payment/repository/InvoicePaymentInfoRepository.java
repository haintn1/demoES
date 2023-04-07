package digi.ecomm.basecommerce.payment.repository;

import digi.ecomm.entity.payment.CreditCardPaymentInfo;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("invoicePaymentInfoRepository")
@RepositoryRestResource(path = InvoicePaymentInfoRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = InvoicePaymentInfoRepository.ITEM_RESOURCE_REL)
public interface InvoicePaymentInfoRepository extends BaseRepository<CreditCardPaymentInfo, Long> {

    String PATH = "invoice-infos";
    String ITEM_RESOURCE_REL = "invoice-info";

}
