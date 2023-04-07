package digi.ecomm.platformservice.media.service;

import digi.ecomm.entity.media.Media;

import java.util.Collection;
import java.util.List;

public interface MediaService {

    /**
     * Get medias by external ids.
     *
     * @param externalIds
     * @return
     */
    List<Media> getByExternalIds(Collection<String> externalIds);

    /**
     * Get media by external id.
     *
     * @param externalId
     * @param mediaType
     * @return
     */
    <T extends Media> T getByExternalId(String externalId, Class<? extends Media> mediaType);
}
