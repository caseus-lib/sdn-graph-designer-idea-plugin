package caseus.sdn.graph.traverse;

import com.intellij.psi.PsiClass;

public interface GraphRelationshipEntityBuilder {

    RelationshipEntityDefinition build(PsiClass aClass);

}
