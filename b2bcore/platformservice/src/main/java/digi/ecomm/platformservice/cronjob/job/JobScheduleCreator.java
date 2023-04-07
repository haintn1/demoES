package digi.ecomm.platformservice.cronjob.job;

import digi.ecomm.platformservice.cronjob.exception.InvalidCronJobException;
import org.quartz.CronTrigger;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.text.ParseException;
import java.util.Date;

public class JobScheduleCreator {

    /**
     * Create a <code>JobDetail</code>.
     *
     * @param jobClass
     * @param isDurable
     * @param context
     * @param jobName
     * @param jobGroup
     * @return
     */
    public JobDetail createJob(final Class<? extends QuartzJobBean> jobClass, final boolean isDurable,
                               final ApplicationContext context, final String jobName, final String jobGroup) {
        final JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(jobClass);
        factoryBean.setDurability(isDurable);
        factoryBean.setApplicationContext(context);
        factoryBean.setName(jobName);
        factoryBean.setGroup(jobGroup);

        // set job data map
        final JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put(jobName + jobGroup, jobClass.getName());
        factoryBean.setJobDataMap(jobDataMap);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    /**
     * Create a <code>CronTrigger</code>.
     *
     * @param triggerName
     * @param startTime
     * @param cronExpression
     * @param misFireInstruction
     * @return
     */
    public CronTrigger createCronTrigger(final String triggerName, final Date startTime, final String cronExpression,
                                         final int misFireInstruction) {
        final CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setName(triggerName);
        factoryBean.setStartTime(startTime);
        factoryBean.setCronExpression(cronExpression);
        factoryBean.setMisfireInstruction(misFireInstruction);
        try {
            factoryBean.afterPropertiesSet();
        } catch (ParseException e) {
            throw new InvalidCronJobException(String.format("Cannot parse cron expression %s", cronExpression), e);
        }
        return factoryBean.getObject();
    }
}
