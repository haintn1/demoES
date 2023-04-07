package digi.ecomm.platformservice.cronjob;

import digi.ecomm.entity.cronjob.CronJob;
import digi.ecomm.platformservice.cronjob.facade.CronJobFacade;
import digi.ecomm.platformservice.cronjob.facade.impl.CronJobFacadeImpl;
import digi.ecomm.platformservice.cronjob.interceptor.CronJobLoadInterceptor;
import digi.ecomm.platformservice.cronjob.job.CronJobFactory;
import digi.ecomm.platformservice.cronjob.job.JobScheduleCreator;
import digi.ecomm.platformservice.cronjob.listener.JobStateListener;
import digi.ecomm.platformservice.cronjob.repository.CronJobRepository;
import digi.ecomm.platformservice.cronjob.service.CronJobService;
import digi.ecomm.platformservice.cronjob.service.impl.CronJobServiceImpl;
import digi.ecomm.platformservice.persistent.interceptor.Interceptor;
import digi.ecomm.platformservice.persistent.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@RequiredArgsConstructor
@Configuration
@ComponentScan(basePackageClasses = CronJobConfiguration.class)
public class CronJobConfiguration {

    private final DataSource dataSource;
    private final ApplicationContext applicationContext;
    private final QuartzProperties quartzProperties;

    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() {
        final CronJobFactory jobFactory = new CronJobFactory();
        jobFactory.setApplicationContext(applicationContext);

        final Properties properties = new Properties();
        properties.putAll(quartzProperties.getProperties());

        final SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setQuartzProperties(properties);
        factory.setJobFactory(jobFactory);
        return factory;
    }

    @Bean("jobScheduleCreator")
    public JobScheduleCreator jobScheduleCreator() {
        return new JobScheduleCreator();
    }

    @Bean("jobStateListener")
    public JobStateListener jobStateListener(final EntityService entityService,
                                             final CronJobRepository cronJobRepository) {
        return new JobStateListener(entityService, cronJobRepository);
    }

    @Bean("cronJobService")
    public CronJobService cronJobService(@Qualifier("schedulerFactoryBean") final Scheduler scheduler,
                                         final ApplicationContext context, final JobScheduleCreator scheduleCreator,
                                         final EntityService entityService, final CronJobRepository cronJobRepository,
                                         final JobStateListener jobStateListener) {
        return new CronJobServiceImpl(scheduler, context, scheduleCreator,
                entityService, cronJobRepository, jobStateListener);
    }

    @Bean("cronJobLoadInterceptor")
    public Interceptor<CronJob> cronJobLoadInterceptor() {
        return new CronJobLoadInterceptor();
    }

    @Bean("cronJobFacade")
    public CronJobFacade cronJobFacade(final CronJobService cronJobService) {
        return new CronJobFacadeImpl(cronJobService);
    }
}
