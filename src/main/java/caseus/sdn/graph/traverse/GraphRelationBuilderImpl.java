package caseus.sdn.graph.traverse;

import caseus.sdn.graph.RelationType;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClassType;
import com.intellij.psi.PsiField;

public class GraphRelationBuilderImpl implements GraphRelationBuilder {

    private final RelationshipAnnotationProcessor annotationProcessor = new RelationshipAnnotationProcessorImpl();

    @Override
    public GraphRelation build(PsiField field) {
        PsiAnnotation relationship = field.getAnnotation("org.neo4j.ogm.annotation.Relationship");
        String type = annotationProcessor.process(relationship).getType();
        if (isCollection(((PsiClassType) field.getType()))) {
            return buildCollections(field, type);
        }
        return buildReference(field, type);
    }

    private GraphRelation buildReference(PsiField field, String name) {
        return GraphRelation.builder()
                            .nodeClassTo(field.getType().getCanonicalText())
                            .relationType(RelationType.REFERENCE)
                            .name(name)
                            .build();
    }

    private GraphRelation buildCollections(PsiField field, String name) {
        return GraphRelation.builder()
                            .nodeClassTo(((PsiClassType) field.getType()).getParameters()[0].getCanonicalText())
                            .relationType(RelationType.COLLECTION)
                            .name(name)
                            .build();
    }

    private boolean isCollection(PsiClassType classType) {
        return classType.getClassName().equals("List");
    }

}
