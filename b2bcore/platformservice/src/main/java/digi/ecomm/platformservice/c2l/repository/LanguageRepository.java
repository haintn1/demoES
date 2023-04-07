package digi.ecomm.platformservice.c2l.repository;

import digi.ecomm.entity.c2l.Language;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("languageRepository")
@RepositoryRestResource(path = LanguageRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = LanguageRepository.ITEM_RESOURCE_REL)
public interface LanguageRepository extends BaseRepository<Language, Long> {

    String PATH = "languages";
    String ITEM_RESOURCE_REL = "language";

    /**
     * Find language by isocode.
     *
     * @param isocode
     * @return
     */
    Optional<Language> findByIsocode(String isocode);
}
