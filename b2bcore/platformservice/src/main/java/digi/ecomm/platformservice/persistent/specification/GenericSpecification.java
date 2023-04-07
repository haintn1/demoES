package digi.ecomm.platformservice.persistent.specification;

import digi.ecomm.entity.AbstractEntity;
import digi.ecomm.platformservice.util.ServicesUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GenericSpecification<T extends AbstractEntity> implements Specification<T> {
    private static final long serialVersionUID = 1L;

    private final transient QueryCriteria criteria;

    public GenericSpecification(final QueryCriteria criteria) {
        ServicesUtils.validateParameterNotNullStandardMessage("criteria", criteria);

        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        switch (criteria.getOperator()) {
            case GREATER_THAN:
                return builder.greaterThan(root.get(criteria.getField()), criteria.getValue().toString());
            case GREATER_THAN_OR_EQUAL:
                return builder.greaterThanOrEqualTo(root.get(criteria.getField()), criteria.getValue().toString());
            case EQUAL:
                return builder.equal(root.get(criteria.getField()), criteria.getValue());
            case LESS_THAN_OR_EQUAL:
                return builder.lessThanOrEqualTo(root.get(criteria.getField()), criteria.getValue().toString());
            case LESS_THAN:
                return builder.lessThan(root.get(criteria.getField()), criteria.getValue().toString());
            case NOT_EQUAL:
                return builder.notEqual(root.get(criteria.getField()), criteria.getValue());
            case LIKE:
                return builder.like(root.get(criteria.getField()), "%" + criteria.getValue() + "%");
            case IN:
                return builder.in(root.get(criteria.getField())).value(criteria.getValues());
            default:
                throw new NotSupportQueryOperation("Operation not supported yet");
        }
    }
}
