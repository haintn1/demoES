package digi.ecomm.platformservice.persistent.interceptor;

import org.hibernate.type.Type;

import java.io.Serializable;

public class InterceptorContext {
    private final Serializable id;
    private final Object[] state;
    private final String[] propertyNames;
    private final Type[] types;

    public InterceptorContext(final Serializable id, final Object[] state, final String[] propertyNames, final Type[] types) {
        this.id = id;
        this.state = state;
        this.propertyNames = propertyNames;
        this.types = types;
    }

    public Serializable getId() {
        return id;
    }

    public Object[] getState() {
        return state;
    }

    public String[] getPropertyNames() {
        return propertyNames;
    }

    public Type[] getTypes() {
        return types;
    }
}
