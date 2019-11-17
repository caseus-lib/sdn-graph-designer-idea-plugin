package caseus.sdn.graph.utils;

import com.intellij.psi.PsiField;

public class FieldUtils {

    public static String defineClassName(PsiField field) {
        return field.getType().getCanonicalText();
    }

}
