package digi.ecomm.basecommerce.organization.repository;

import digi.ecomm.entity.organization.B2BBudget;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("b2bBudgetRepository")
@RepositoryRestResource(path = B2BBudgetRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = B2BBudgetRepository.ITEM_RESOURCE_REL)
public interface B2BBudgetRepository extends BaseRepository<B2BBudget, Long> {

    String PATH = "budgets";
    String ITEM_RESOURCE_REL = "budget";

}
