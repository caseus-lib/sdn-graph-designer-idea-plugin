package caseus.sdn.graph.traverse.annotation;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import org.apache.commons.lang.StringUtils;

import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class RelationshipTypeAnnotationProcessor<T> implements AnnotationProcessor<T> {

    protected String extractType(PsiAnnotation annotation) {
        Supplier<String> type = () -> extractValue(annotation, "type");
        Supplier<String> value = () -> extractValue(annotation, "value");
        return Stream.of(type, value)
                     .map(Supplier::get)
                     .filter(StringUtils::isNotBlank)
                     .findFirst()
                     .orElse(StringUtils.EMPTY);
    }

    protected String extractValue(PsiAnnotation annotation, String attribute) {
        return AnnotationUtil.getStringAttributeValue(annotation, attribute);
    }

}
