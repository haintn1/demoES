package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BCustomer;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bCustomerRepository")
@RepositoryRestResource(path = B2BCustomerRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BCustomerRepository.ITEM_RESOURCE_REL)
public interface B2BCustomerRepository extends BaseRepository<B2BCustomer, Long> {

    String PATH = "b2b-customers";
    String ITEM_RESOURCE_REL = "b2b-customer";

}
