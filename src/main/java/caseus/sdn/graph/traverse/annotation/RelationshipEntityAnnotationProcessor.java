package caseus.sdn.graph.traverse.annotation;

import com.intellij.psi.PsiAnnotation;

public class RelationshipEntityAnnotationProcessor extends RelationshipTypeAnnotationProcessor<RelationshipEntityAnnotation> {

    @Override
    public RelationshipEntityAnnotation process(PsiAnnotation annotation) {
        return RelationshipEntityAnnotation.builder()
                                           .type(extractType(annotation))
                                           .build();
    }

}
