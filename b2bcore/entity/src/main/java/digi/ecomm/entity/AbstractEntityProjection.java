package digi.ecomm.entity;

import java.util.Date;

public interface AbstractEntityProjection {

    Long getId();

    String getCreatedBy();

    Date getCreatedDate();

    String getLastModifiedBy();

    Date getLastModifiedDate();
}
