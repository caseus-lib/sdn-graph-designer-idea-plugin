package caseus.sdn.graph.traverse;

import com.intellij.codeInsight.AnnotationUtil;
import com.intellij.psi.PsiAnnotation;
import org.apache.commons.lang.StringUtils;

import java.util.Optional;

public class RelationshipAnnotationProcessorImpl implements RelationshipAnnotationProcessor {

    @Override
    public RelationshipAnnotationDefinition process(PsiAnnotation annotation) {
        return RelationshipAnnotationDefinition.builder()
                                               .type(extractType(annotation))
                                               .direction(extractDirection(annotation))
                                               .build();
    }

    private RelationshipDirection extractDirection(PsiAnnotation annotation) {
        return extractValue(annotation, "direction").map(RelationshipDirection::valueOf)
                                                    .orElse(RelationshipDirection.OUTGOING);
    }

    private String extractType(PsiAnnotation annotation) {
        return extractValue(annotation, "type").orElse(StringUtils.EMPTY);
    }

    private Optional<String> extractValue(PsiAnnotation annotation, String attribute) {
        return Optional.ofNullable(AnnotationUtil.getStringAttributeValue(annotation, attribute));
    }

}
