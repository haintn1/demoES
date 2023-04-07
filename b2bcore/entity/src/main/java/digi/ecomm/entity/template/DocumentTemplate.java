package digi.ecomm.entity.template;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.entity.template.converter.TemplateTypeConverter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "document_templates", uniqueConstraints = {
        @UniqueConstraint(columnNames = {DocumentTemplate.Fields.UID})} //NOSONAR
)
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class DocumentTemplate extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String uid;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String content;

    @Column(nullable = false)
    @Convert(converter = TemplateTypeConverter.class)
    private TemplateType type;

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
