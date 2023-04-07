package digi.ecomm.entity.c2l;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import digi.ecomm.entity.localized.LocalizableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class C2LL10NAugmentation<E extends LocalizableEntity> extends AbstractL10NAugmentation<E> {

    private static final long serialVersionUID = 1L;

    @Column
    private String name;
}
