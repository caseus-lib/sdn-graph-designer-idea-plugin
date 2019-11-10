package caseus.sdn.graph;

import caseus.sdn.graph.traverse.ClassGraph;
import caseus.sdn.graph.traverse.JavaClassVisitor;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GraphStructureViewAction extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        PsiElement psiElement = e.getData(CommonDataKeys.PSI_ELEMENT);
        if (psiElement == null) {
            throw new RuntimeException("Элемент не того типа");
        }
        if (!(psiElement instanceof PsiDirectory)) {
            throw new RuntimeException("Элемент не того типа");
        }
        ClassGraph classGraph = new ClassGraph();
        JavaClassVisitor visitor = new JavaClassVisitor(classGraph);
        psiElement.accept(visitor);
        GraphBuilder graphBuilder = new GraphBuilderImpl();
        Graph graph = graphBuilder.build(classGraph);
        DotTranslator translator = new DotTranslatorImpl();
        List<String> lines = translator.translate(graph);
        try {
            VirtualFile virtualFile = ((PsiDirectory) psiElement).getVirtualFile();
            File file = new File(virtualFile.getCanonicalPath() + "/graph.dot");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.join("\n", lines));
            fileWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        LocalFileSystem.getInstance().refresh(false);
        Messages.showMessageDialog(e.getProject(), "Graph build success. Check 'graph.dot' file", "Success",
                                   Messages.getInformationIcon());
    }

}
