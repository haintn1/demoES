package digi.ecomm.platformservice.rest.springdata.hal;

import digi.ecomm.entity.AbstractEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.LinkRelation;
import org.springframework.hateoas.server.RepresentationModelProcessor;

public class EntityModelProcessor implements RepresentationModelProcessor<EntityModel<AbstractEntity>> {
    @Override
    public EntityModel<AbstractEntity> process(final EntityModel<AbstractEntity> model) {
        model.getLinks().stream().filter(link -> link.getRel().isSameAs(LinkRelation.of(IanaLinkRelations.SELF_VALUE)))
                .findFirst().ifPresent(selfLink -> {
            model.removeLinks();
            model.add(selfLink);
        });
        return model;
    }
}
