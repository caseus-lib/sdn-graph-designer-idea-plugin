package caseus.sdn.graph.traverse.annotation;

import caseus.sdn.graph.traverse.RelationshipDirection;
import com.intellij.psi.PsiAnnotation;

public class RelationshipAnnotationProcessor extends RelationshipTypeAnnotationProcessor<RelationshipAnnotation> {

    @Override
    public RelationshipAnnotation process(PsiAnnotation annotation) {
        return RelationshipAnnotation.builder()
                                     .type(extractType(annotation))
                                     .direction(extractDirection(annotation))
                                     .build();
    }

    private RelationshipDirection extractDirection(PsiAnnotation annotation) {
        return RelationshipDirection.valueOf(extractValue(annotation, "direction"));
    }

}
