package digi.ecomm.platformservice.media.service.impl;

import digi.ecomm.entity.media.Media;
import digi.ecomm.platformservice.media.repository.MediaRepository;
import digi.ecomm.platformservice.media.service.MediaService;
import digi.ecomm.platformservice.util.ServicesUtils;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public List<Media> getByExternalIds(final Collection<String> externalIds) {
        ServicesUtils.validateParameterNotEmptyStandardMessage("externalIds", externalIds);
        return mediaRepository.findByExternalIds(externalIds);
    }

    @Override
    public <T extends Media> T getByExternalId(final String externalId, final Class<? extends Media> mediaType) {
        ServicesUtils.validateParameterNotNullStandardMessage("externalId", externalId);
        ServicesUtils.validateParameterNotNullStandardMessage("mediaType", mediaType);

        final Optional<Media> mediaOpt = mediaRepository.findByExternalId(externalId);
        if (!mediaOpt.isPresent()) {
            return null;
        }

        final Media media = mediaOpt.get();
        if (!mediaType.isInstance(media)) {
            throw new IllegalArgumentException(String.format("[%s] must be an instance of [%s]", media, mediaType));
        }

        return (T) media;
    }
}
