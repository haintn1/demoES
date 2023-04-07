package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.Company;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("companyRepository")
@RepositoryRestResource(path = CompanyRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CompanyRepository.ITEM_RESOURCE_REL)
public interface CompanyRepository extends BaseRepository<Company, Long> {

    String PATH = "companies";
    String ITEM_RESOURCE_REL = "company";

}
