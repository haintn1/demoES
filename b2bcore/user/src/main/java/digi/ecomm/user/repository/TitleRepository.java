package digi.ecomm.user.repository;

import digi.ecomm.entity.user.Title;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("titleRepository")
@RepositoryRestResource(path = TitleRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = TitleRepository.ITEM_RESOURCE_REL)
public interface TitleRepository extends BaseRepository<Title, Long> {

    String PATH = "titles";
    String ITEM_RESOURCE_REL = "title";

}
