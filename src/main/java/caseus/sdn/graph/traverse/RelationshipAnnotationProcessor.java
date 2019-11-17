package caseus.sdn.graph.traverse;

import com.intellij.psi.PsiAnnotation;

public interface RelationshipAnnotationProcessor {

    RelationshipAnnotationDefinition process(PsiAnnotation annotation);

}
