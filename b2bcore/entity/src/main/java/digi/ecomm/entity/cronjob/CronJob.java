package digi.ecomm.entity.cronjob;

import com.fasterxml.jackson.annotation.JsonIgnore;
import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "cron_jobs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {CronJob.Fields.JOB_NAME, CronJob.Fields.JOB_GROUP})}) //NOSONAR
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class CronJob extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String jobName;

    @JsonIgnore
    @Column(nullable = false)
    private String jobGroup;

    @Enumerated(EnumType.STRING)
    private JobState jobStatus;

    @JsonIgnore
    @Column(nullable = false)
    private String jobClass;

    @Column(nullable = false)
    private String cronExpression;

    @Column
    private String description;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
