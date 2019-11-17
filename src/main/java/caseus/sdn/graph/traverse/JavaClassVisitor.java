package caseus.sdn.graph.traverse;

import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaClassVisitor extends JavaRecursiveElementVisitor {

    private final ClassGraph classGraph;
    private final GraphNodeBuilder graphNodeBuilder = new GraphNodeBuilderImpl();

    @Override
    public void visitClass(PsiClass aClass) {
//        if (aClass.hasAnnotation("org.neo4j.ogm.annotation.RelationshipEntity"))
        if (aClass.hasAnnotation("org.neo4j.ogm.annotation.NodeEntity")) {
            GraphNode graphNode = graphNodeBuilder.build(aClass);
            classGraph.add(graphNode);
        }
    }

    @Override
    public void visitPackage(PsiPackage aPackage) {
        aPackage.acceptChildren(this);
    }

}
