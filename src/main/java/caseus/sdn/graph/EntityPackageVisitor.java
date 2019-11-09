package caseus.sdn.graph;

import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;

public class EntityPackageVisitor extends PsiElementVisitor {

    @Override
    public void visitElement(PsiElement element) {
        if (element == null) {
            System.out.println("Element is null");
            return;
        }
        if (element instanceof PsiDirectory) {
            element.acceptChildren(this);
            return;
        }
        if (element.getNode() == null) {
            System.out.println("Element node is null " + element);
            return;
        }
        System.out.println(element.getNode().getElementType());
    }

}
