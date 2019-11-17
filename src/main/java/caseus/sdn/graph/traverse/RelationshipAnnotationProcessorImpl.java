package caseus.sdn.graph.traverse;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import org.apache.commons.lang.StringUtils;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class RelationshipAnnotationProcessorImpl implements RelationshipAnnotationProcessor {

    @Override
    public RelationshipAnnotationDefinition process(PsiAnnotation annotation) {
        return RelationshipAnnotationDefinition.builder()
                                               .type(extractType(annotation))
                                               .direction(extractDirection(annotation))
                                               .build();
    }

    private RelationshipDirection extractDirection(PsiAnnotation annotation) {
        return RelationshipDirection.valueOf(extractValue(annotation, "direction"));
    }

    private String extractType(PsiAnnotation annotation) {
        Supplier<String> type = () -> extractValue(annotation, "type");
        Supplier<String> value = () -> extractValue(annotation, "value");
        return Stream.of(type, value)
                     .map(Supplier::get)
                     .filter(StringUtils::isNotBlank)
                     .findFirst()
                     .orElse(StringUtils.EMPTY);
    }

    private String extractValue(PsiAnnotation annotation, String attribute) {
        return AnnotationUtil.getStringAttributeValue(annotation, attribute);
    }

}
