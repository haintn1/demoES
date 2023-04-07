package digi.ecomm.platformservice.cronjob.repository;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.platformservice.persistent.repository.BaseRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("cronJobRepository")
@RepositoryRestResource(path = CronJobRepository.PATH, collectionResourceRel = BaseRepository.REST_COLLECTION_RESOURCE_REL,
        itemResourceRel = CronJobRepository.ITEM_RESOURCE_REL)
public interface CronJobRepository extends BaseRepository<CronJob, Long> {

    String PATH = "cronjobs";
    String ITEM_RESOURCE_REL = "cronjob";

    /**
     * Find {@link CronJob}s by its name.
     *
     * @param jobName
     * @return
     */
    List<CronJob> findByJobName(String jobName);

    /**
     * Find {@link CronJob} by its name and group.
     *
     * @param jobName
     * @param jobGroup
     * @return
     */
    CronJob findByJobNameAndJobGroup(String jobName, String jobGroup);
}
