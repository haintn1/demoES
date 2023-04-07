package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.Customer;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
@RepositoryRestResource(path = CustomerRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CustomerRepository.ITEM_RESOURCE_REL)
public interface CustomerRepository extends BaseRepository<Customer, Long> {

    String PATH = "customers";
    String ITEM_RESOURCE_REL = "customer";

}
