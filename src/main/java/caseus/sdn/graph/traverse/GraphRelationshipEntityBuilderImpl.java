package caseus.sdn.graph.traverse;

import caseus.sdn.graph.traverse.annotation.AnnotationProcessor;
import caseus.sdn.graph.traverse.annotation.AnnotationType;
import caseus.sdn.graph.traverse.annotation.RelationshipEntityAnnotation;
import caseus.sdn.graph.traverse.annotation.RelationshipEntityAnnotationProcessor;
import caseus.sdn.graph.utils.FieldUtils;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;

public class GraphRelationshipEntityBuilderImpl implements GraphRelationshipEntityBuilder {

    private final AnnotationProcessor<RelationshipEntityAnnotation> annotationProcessor = new RelationshipEntityAnnotationProcessor();

    @Override
    public RelationshipEntityDefinition build(PsiClass psiClass) {
        PsiAnnotation annotation = psiClass.getAnnotation(AnnotationType.RELATIONSHIP_ENTITY.getClassName());
        String type = annotationProcessor.process(annotation).getType();
        String classFrom = "", classTo = "";
        for (PsiField field : psiClass.getAllFields()) {
            if (field.hasAnnotation(AnnotationType.START_NODE.getClassName())) {
                classFrom = FieldUtils.defineClassName(field);
            }
            if (field.hasAnnotation(AnnotationType.END_NODE.getClassName())) {
                classTo = FieldUtils.defineClassName(field);
            }
        }
        return RelationshipEntityDefinition.builder()
                                           .className(psiClass.getQualifiedName())
                                           .classFrom(classFrom)
                                           .classTo(classTo)
                                           .name(type)
                                           .build();
    }

}
