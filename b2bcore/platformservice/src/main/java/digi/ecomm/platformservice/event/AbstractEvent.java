package digi.ecomm.platformservice.event;

import org.springframework.context.ApplicationEvent;

public abstract class AbstractEvent extends ApplicationEvent {

    protected AbstractEvent(final Object source) {
        super(source);
    }
}
