package caseus.sdn.graph;

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
        psiElement.accept(new EntityPackageVisitor());
        System.out.println("========");
        psiElement.acceptChildren(new EntityPackageVisitor());
    }

}
