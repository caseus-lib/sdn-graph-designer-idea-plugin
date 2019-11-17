package caseus.sdn.graph.traverse;

import com.intellij.psi.PsiClass;

import java.util.Arrays;
import java.util.stream.Collectors;

import static caseus.sdn.graph.traverse.annotation.AnnotationType.RELATIONSHIP;

public class GraphNodeBuilderImpl implements GraphNodeBuilder {

    private final GraphRelationBuilder graphRelationBuilder = new GraphRelationBuilderImpl();

    @Override
    public GraphNode build(PsiClass psiClass) {
        return GraphNode.builder()
                        .nodeClass(psiClass.getQualifiedName())
                        .name(psiClass.getName())
                        .relations(Arrays.stream(psiClass.getAllFields())
                                         .filter(field -> field.hasAnnotation(RELATIONSHIP.getClassName()))
                                         .map(graphRelationBuilder::build)
                                         .collect(Collectors.toList()))
                        .build();
    }

}
