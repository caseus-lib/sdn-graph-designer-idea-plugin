package caseus.sdn.graph.traverse;

import caseus.sdn.graph.traverse.annotation.AnnotationType;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JavaClassVisitor extends JavaRecursiveElementVisitor {

    private final ClassGraph classGraph;
    private final GraphNodeBuilder graphNodeBuilder = new GraphNodeBuilderImpl();
    private final GraphRelationshipEntityBuilder graphRelationshipEntityBuilder =
            new GraphRelationshipEntityBuilderImpl();

    @Override
    public void visitClass(PsiClass aClass) {
        if (aClass.hasAnnotation(AnnotationType.RELATIONSHIP_ENTITY.getClassName())) {
            classGraph.add(graphRelationshipEntityBuilder.build(aClass));
        }
        if (aClass.hasAnnotation(AnnotationType.NODE_ENTITY.getClassName())) {
            classGraph.add(graphNodeBuilder.build(aClass));
        }
    }

    @Override
    public void visitPackage(PsiPackage aPackage) {
        aPackage.acceptChildren(this);
    }

}
