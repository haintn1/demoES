package digi.ecomm.entity;

import digi.ecomm.entity.localized.AbstractL10NAugmentation;
import javassist.*;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.ManyToOne;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.Set;
import java.util.stream.Collectors;

public class L10NAugmentationByteCodeEnhancer {

    private static final Logger LOGGER = LoggerFactory.getLogger(L10NAugmentationByteCodeEnhancer.class);

    public static void main(final String[] args) throws CannotCompileException, NotFoundException, IOException {

        final Reflections reflections = new Reflections(L10NAugmentationByteCodeEnhancer.class.getPackage().getName());
        final Set<Class<? extends AbstractL10NAugmentation>> l10nAugmentations =
                reflections.getSubTypesOf(AbstractL10NAugmentation.class).stream()
                        .filter(augmentationType -> !Modifier.isAbstract(augmentationType.getModifiers())
                                && !Modifier.isInterface(augmentationType.getModifiers()))
                        .collect(Collectors.toSet());
        final ClassPool cp = ClassPool.getDefault();
        cp.appendClassPath(new LoaderClassPath(L10NAugmentationByteCodeEnhancer.class.getClassLoader()));

        LOGGER.info("Enhancing L10N augmentations ...");
        for (final Class<? extends AbstractL10NAugmentation> augmentationType : l10nAugmentations) {
            LOGGER.info(augmentationType.getName());
            final CtClass ctClass = cp.get(augmentationType.getName());
            ctClass.defrost();
            final ParameterizedType augSuperClass = (ParameterizedType) augmentationType.getGenericSuperclass();
            final CtClass l10nEntityClass = cp.get(augSuperClass.getActualTypeArguments()[0].getTypeName());
            final CtMethod ctMethod = ctClass.getDeclaredMethod("setEntity", new CtClass[]{l10nEntityClass});
            CtField ctField = null;
            for (final CtField field : ctClass.getDeclaredFields()) {
                if (field.getType().equals(l10nEntityClass) && field.hasAnnotation(ManyToOne.class)) {
                    ctField = field;
                    break;
                }
            }
            if (ctField != null) {
                ctMethod.setBody("{this." + ctField.getName() + " = $1;}");
            }
            ctClass.writeFile(args[0]);
        }
        LOGGER.info("Enhancing done!");
    }
}
