package digi.ecomm.platformservice.initialdata.setup;

import digi.ecomm.platformservice.PlatformProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;

@RequiredArgsConstructor
public class InitialDataSetup {

    private final List<CoreDataCreator> coreDataCreators;
    private final List<SampleDataCreator> sampleDataCreators;
    private final PlatformProperties platformProperties;

    /**
     * Create data when application context gets refreshed.
     *
     * @param event
     */
    @EventListener
    public void handleApplicationReadyEvent(final ApplicationReadyEvent event) {
        if (platformProperties.getInitialData().getCore().isAutoImport() && CollectionUtils.isNotEmpty(coreDataCreators)) {
            coreDataCreators.forEach(CoreDataCreator::createData);
        }
        if (platformProperties.getInitialData().getSample().isAutoImport() && CollectionUtils.isNotEmpty(sampleDataCreators)) {
            sampleDataCreators.forEach(SampleDataCreator::createData);
        }
    }
}
