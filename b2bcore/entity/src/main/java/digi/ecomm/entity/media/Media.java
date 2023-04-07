package digi.ecomm.entity.media;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.catalog.Catalog;
import digi.ecomm.entity.sync.SynchronizableEntity;
import digi.ecomm.entity.sync.SynchronizationEmbedding;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;
import org.hibernate.annotations.LazyGroup;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

@Entity
@Table(name = "medias", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Media.Fields.CODE, Media.CATALOG_ID})}, //NOSONAR
        indexes = {
                @Index(columnList = SynchronizationEmbedding.Fields.EXTERNAL_ID)} //NOSONAR
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Media extends AbstractEntity implements SynchronizableEntity {

    private static final long serialVersionUID = 1L;

    static final String CATALOG_ID = "catalog_id";

    @Embedded
    private SynchronizationEmbedding sync = new SynchronizationEmbedding();

    @Column(nullable = false)
    private String code;

    @Column
    private String fileName;

    @Column
    private String altText;

    @Column
    private String description;

    @Column
    private String realFileName;

    @Column
    private String mimeType;

    @Column
    private Long fileSizeInByte;

    @ManyToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    private MediaFolder mediaFolder;

    @OneToOne(fetch = FetchType.LAZY)
    @LazyToOne(LazyToOneOption.NO_PROXY)
    @LazyGroup(Fields.CATALOG)
    @JoinColumn(name = CATALOG_ID, referencedColumnName = AbstractEntity.Fields.ID)
    private Catalog catalog;

    @Override
    public SynchronizationEmbedding getSync() {
        return sync;
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
