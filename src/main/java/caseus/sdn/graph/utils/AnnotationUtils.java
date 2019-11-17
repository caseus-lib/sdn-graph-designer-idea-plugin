package caseus.sdn.graph.utils;

import caseus.sdn.graph.traverse.annotation.AnnotationType;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiJvmModifiersOwner;

public class AnnotationUtils {

    public static boolean hasAnnotation(PsiJvmModifiersOwner psiElement, AnnotationType type) {
        return psiElement.hasAnnotation(type.getClassName());
    }

    public static PsiAnnotation getAnnotation(PsiJvmModifiersOwner psiElement, AnnotationType type) {
        return psiElement.getAnnotation(type.getClassName());
    }

}
