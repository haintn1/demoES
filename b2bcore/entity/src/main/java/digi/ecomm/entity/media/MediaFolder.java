package digi.ecomm.entity.media;

import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "media_folders", uniqueConstraints = {
        @UniqueConstraint(columnNames = {MediaFolder.Fields.NAME})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class MediaFolder extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String path;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
