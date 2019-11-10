package caseus.sdn.graph.traverse;

import com.intellij.psi.PsiClass;

public interface GraphNodeBuilder {

    GraphNode build(PsiClass psiClass);

}
