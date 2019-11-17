package caseus.sdn.graph.traverse;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;

public class GraphRelationshipEntityBuilderImpl implements GraphRelationshipEntityBuilder {

    @Override
    public RelationshipEntityDefinition build(PsiClass aClass) {
        PsiAnnotation annotation = aClass.getAnnotation("org.neo4j.ogm.annotation.RelationshipEntity");
        String type = annotation.findAttributeValue("type").getText();
        return null;
    }

}
