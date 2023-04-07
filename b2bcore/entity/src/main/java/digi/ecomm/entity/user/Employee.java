package digi.ecomm.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "employess", uniqueConstraints = {
        @UniqueConstraint(columnNames = {Principal.Fields.UID})}) //NOSONAR
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public class Employee extends User {

    private static final long serialVersionUID = 1L;

}
