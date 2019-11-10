package caseus.sdn.graph;

import caseus.sdn.graph.traverse.ClassGraph;
import caseus.sdn.graph.traverse.JavaClassVisitor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public class GraphStructureViewAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement psiElement = e.getData(CommonDataKeys.PSI_ELEMENT);
        if (psiElement == null) {
            throw new RuntimeException("Элемент не того типа");
        }
        ClassGraph classGraph = new ClassGraph();
        JavaClassVisitor visitor = new JavaClassVisitor(classGraph);
        psiElement.accept(visitor);
        classGraph.getNodes().forEach(node -> System.out.println(node.getNodeClass() + " " + node.getName()));
        GraphBuilder graphBuilder = new GraphBuilderImpl();
        Graph graph = graphBuilder.build(classGraph);
        System.out.println("end");
    }

}
