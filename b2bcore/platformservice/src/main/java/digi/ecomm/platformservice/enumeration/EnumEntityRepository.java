package digi.ecomm.platformservice.enumeration;

import digi.ecomm.entity.EnumEntity;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository("enumRepository")
@RepositoryRestResource(path = EnumEntityRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = EnumEntityRepository.ITEM_RESOURCE_REL)
public interface EnumEntityRepository extends BaseRepository<EnumEntity, Long> {

    String PATH = "enums";
    String ITEM_RESOURCE_REL = "enum";
}
