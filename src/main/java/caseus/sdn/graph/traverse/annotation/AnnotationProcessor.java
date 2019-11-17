package caseus.sdn.graph.traverse.annotation;

import com.intellij.psi.PsiAnnotation;

public interface AnnotationProcessor<T> {

    T process(PsiAnnotation annotation);

}
