package digi.ecomm.platformservice.persistent.hibernate;

import org.hibernate.Interceptor;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;

import java.util.Map;

public class HibernatePropertiesCustomization implements HibernatePropertiesCustomizer {

    private final Interceptor interceptor;

    public HibernatePropertiesCustomization(final Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override
    public void customize(final Map<String, Object> hibernateProperties) {
        hibernateProperties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
        hibernateProperties.put(AvailableSettings.ALLOW_REFRESH_DETACHED_ENTITY, true);
        hibernateProperties.put(AvailableSettings.INTERCEPTOR, interceptor);
    }
}