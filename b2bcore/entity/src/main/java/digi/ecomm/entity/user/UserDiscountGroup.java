package digi.ecomm.entity.user;

import digi.ecomm.entity.EnumEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class UserDiscountGroup extends EnumEntity {

    private static final long serialVersionUID = 1L;
}
