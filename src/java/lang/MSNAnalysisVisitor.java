package lang;

import lang.ast.*;

public class MSNAnalysisVisitor extends TraversingVisitor {

    public static int result(ASTNode n) {
        MSNAnalysisVisitor v = new MSNAnalysisVisitor();
        n.accept(v, 0);
        return v.maxDepth;
    }

    private int maxDepth = 0;

    public Object visit(Block node, Object data) {
        int newDepth = (Integer) data + 1;
        if (newDepth > maxDepth) {
            maxDepth = newDepth;
        }
        visitChildren(node, newDepth);
        return data;
    }
}
