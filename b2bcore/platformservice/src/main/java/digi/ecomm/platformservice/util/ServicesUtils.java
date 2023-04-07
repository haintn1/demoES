package digi.ecomm.platformservice.util;

import com.google.common.base.Preconditions;
import digi.ecomm.platformservice.exception.AmbiguousIdentifierException;
import digi.ecomm.platformservice.exception.UnknownIdentifierException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public final class ServicesUtils {

    /**
     * Hide constructor.
     */
    private ServicesUtils() {
    }

    /**
     * Validate if single result.
     *
     * @param resultToCheck
     * @param unknownIdException
     * @param ambiguousIdException
     */
    public static void validateIfSingleResult(final Collection<? extends Object> resultToCheck, final String unknownIdException,
                                              final String ambiguousIdException) {
        Preconditions.checkArgument(resultToCheck != null, "the result collection can not be null");
        validateSingleResultWithType(resultToCheck, Object.class, unknownIdException, ambiguousIdException);
    }

    /**
     * Validate if single result.
     *
     * @param resultToCheck
     * @param clazz
     * @param qualifier
     * @param qualifierValue
     */
    public static void validateIfSingleResult(final Collection<? extends Object> resultToCheck,
                                              final Class<? extends Object> clazz,
                                              final String qualifier, final Object qualifierValue) {
        Preconditions.checkArgument(resultToCheck != null, "the result collection can not be null");
        Preconditions.checkArgument(clazz != null, "the given clazz can not be null");
        Preconditions.checkArgument(qualifier != null && qualifier.length() > 0, "qualifier must contain something");
        String unknownId = String.format("%s with %s \'%s\' not found!", clazz.getSimpleName(), qualifier, qualifierValue.toString());
        String ambiguousId = String.format("%s %s \'%s\' is not unique, %d instances  of type %s found!", clazz.getSimpleName(), qualifier, qualifierValue.toString(), resultToCheck.size(), clazz.getSimpleName());
        validateSingleResultWithType(resultToCheck, clazz, unknownId, ambiguousId);
    }

    /**
     * Validate if single result with type.
     *
     * @param resultToCheck
     * @param clazz
     * @param unknownIdException
     * @param ambiguousIdException
     */
    private static void validateSingleResultWithType(final Collection<? extends Object> resultToCheck,
                                                     final Class<? extends Object> clazz,
                                                     final String unknownIdException, final String ambiguousIdException) {
        if (CollectionUtils.isEmpty(resultToCheck)) {
            throw new UnknownIdentifierException(unknownIdException);
        } else if (resultToCheck.size() > 1) {
            throw new AmbiguousIdentifierException(ambiguousIdException);
        } else {
            Iterator<? extends Object> iterator = resultToCheck.iterator();

            while (iterator.hasNext()) {
                final Object element = iterator.next();
                if (!clazz.isInstance(element)) {
                    throw new IllegalStateException(String.format("Element in result (\'%s\') is not the same class or a subclass of \'%s\'", element.getClass(), clazz));
                }
            }

        }
    }

    /**
     * Validate if any result.
     *
     * @param resultToCheck
     * @param unknownIdException
     */
    public static void validateIfAnyResult(final Collection<? extends Object> resultToCheck, final String unknownIdException) {
        if (CollectionUtils.isEmpty(resultToCheck)) {
            throw new UnknownIdentifierException(unknownIdException);
        }
    }

    /**
     * Validate parameter not mull.
     *
     * @param parameter
     * @param nullMessage
     */
    public static void validateParameterNotNull(final Object parameter, final String nullMessage) {
        Preconditions.checkArgument(parameter != null, nullMessage);
    }

    /**
     * Validate parameter not empty.
     *
     * @param parameter
     * @param emptyMessage
     */
    public static void validateParameterNotEmpty(final Object parameter, final String emptyMessage) {
        if (parameter instanceof Collection) {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty((Collection<?>) parameter), emptyMessage);
        } else if (parameter instanceof Map) {
            Preconditions.checkArgument(MapUtils.isNotEmpty((Map<?, ?>) parameter), emptyMessage);
        } else if (parameter instanceof String) {
            Preconditions.checkArgument(StringUtils.isNotEmpty((CharSequence) parameter), emptyMessage);
        } else {
            throw new IllegalArgumentException("Empty check is not support for " + parameter.getClass().getName());
        }
    }

    /**
     * Validate parameter not null with standard message.
     *
     * @param parameter
     * @param parameterValue
     */
    public static void validateParameterNotNullStandardMessage(final String parameter, final Object parameterValue) {
        validateParameterNotNull(parameterValue, String.format("Parameter %s can not be null", parameter));
    }

    /**
     * Validate parameter not empty with standard message.
     *
     * @param parameter
     * @param parameterValue
     */
    public static void validateParameterNotEmptyStandardMessage(final String parameter, final Object parameterValue) {
        validateParameterNotEmpty(parameterValue, String.format("Parameter %s can not be empty", parameter));
    }
}
