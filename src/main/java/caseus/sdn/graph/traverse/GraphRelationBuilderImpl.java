package caseus.sdn.graph.traverse;

import caseus.sdn.graph.RelationType;
import caseus.sdn.graph.traverse.annotation.AnnotationProcessor;
import caseus.sdn.graph.traverse.annotation.RelationshipAnnotation;
import caseus.sdn.graph.traverse.annotation.RelationshipAnnotationProcessor;
import caseus.sdn.graph.utils.FieldUtils;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiField;
import one.util.streamex.StreamEx;

import static caseus.sdn.graph.traverse.annotation.AnnotationType.RELATIONSHIP;
import static caseus.sdn.graph.utils.AnnotationUtils.getAnnotation;

public class GraphRelationBuilderImpl implements GraphRelationBuilder {

    private final AnnotationProcessor<RelationshipAnnotation> annotationProcessor = new RelationshipAnnotationProcessor();

    @Override
    public GraphRelation build(PsiField field) {
        RelationshipAnnotation relationshipAnnotation = annotationProcessor.process(getAnnotation(field, RELATIONSHIP));
        if (isCollection(((PsiClassType) field.getType()))) {
            return buildCollections(field, relationshipAnnotation);
        }
        return buildReference(field, relationshipAnnotation);
    }

    private GraphRelation buildReference(PsiField field, RelationshipAnnotation relationshipAnnotation) {
        return GraphRelation.builder()
                            .nodeClassTo(FieldUtils.defineClassName(field))
                            .relationType(RelationType.REFERENCE)
                            .name(relationshipAnnotation.getType())
                            .direction(relationshipAnnotation.getDirection())
                            .build();
    }

    private GraphRelation buildCollections(PsiField field, RelationshipAnnotation relationshipAnnotation) {
        return GraphRelation.builder()
                            .nodeClassTo(((PsiClassType) field.getType()).getParameters()[0].getCanonicalText())
                            .relationType(RelationType.COLLECTION)
                            .name(relationshipAnnotation.getType())
                            .direction(relationshipAnnotation.getDirection())
                            .build();
    }

    private boolean isCollection(PsiClassType classType) {
        return StreamEx.of(classType.getSuperTypes())
                       .select(PsiClassType.class)
                       .map(PsiClassType::getClassName)
                       .anyMatch(name -> name.equals("Collection"));
    }

}
