package digi.ecomm.entity.c2l;

import com.fasterxml.jackson.annotation.JsonIgnore;
import digi.ecomm.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Locale;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
@FieldNameConstants
public abstract class C2LItem extends AbstractEntity {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String isocode;

    @Column(nullable = false)
    private Boolean active;

    /**
     * Get name.
     *
     * @return
     */
    @JsonIgnore
    public abstract String getName();

    /**
     * Get name.
     *
     * @param locale
     * @return
     */
    public abstract String getName(Locale locale);

    /**
     * Set name.
     *
     * @param name
     * @return
     */
    public abstract void setName(String name);

    /**
     * Set name.
     *
     * @param locale
     * @param name
     * @return
     */
    public abstract void setName(Locale locale, String name);

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
