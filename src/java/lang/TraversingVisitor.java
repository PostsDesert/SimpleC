package lang;

import lang.ast.*;

public class TraversingVisitor implements lang.ast.Visitor {

    protected Object visitChildren(ASTNode node, Object data) {
        for (int i = 0; i < node.getNumChild(); ++i) {
            node.getChild(i).accept(this, data);
        }
        return data;
    }

    public Object visit(List node, Object data) { return visitChildren(node, data); }
    public Object visit(FuncDesc node, Object data) { return visitChildren(node, data); }
    public Object visit(Block node, Object data) { return visitChildren(node, data); }
    public Object visit(Stmt node, Object data) { return visitChildren(node, data); }
    public Object visit(AssignStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(IfStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(WhileStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(ReturnStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(FuncCallStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(VarDeclStmt node, Object data) { return visitChildren(node, data); }
    public Object visit(Opt node, Object data) { return visitChildren(node, data); }
    public Object visit(Expr node, Object data) { return visitChildren(node, data); }
    public Object visit(IdDecl node, Object data) { return visitChildren(node, data); }
    public Object visit(IdUse node, Object data) { return visitChildren(node, data); }
    public Object visit(Numeral node, Object data) { return visitChildren(node, data); }
    public Object visit(ParenExpr node, Object data) { return visitChildren(node, data); }
    public Object visit(UnaryMinus node, Object data) { return visitChildren(node, data); }
    public Object visit(FuncCall node, Object data) { return visitChildren(node, data); }
    public Object visit(Program node, Object data) { return visitChildren(node, data); }
    public Object visit(Eq node, Object data) { return visitChildren(node, data); }
    public Object visit(Neq node, Object data) { return visitChildren(node, data); }
    public Object visit(Leq node, Object data) { return visitChildren(node, data); }
    public Object visit(Geq node, Object data) { return visitChildren(node, data); }
    public Object visit(Lt node, Object data) { return visitChildren(node, data); }
    public Object visit(Gt node, Object data) { return visitChildren(node, data); }
    public Object visit(Plus node, Object data) { return visitChildren(node, data); }
    public Object visit(Minus node, Object data) { return visitChildren(node, data); }
    public Object visit(Mul node, Object data) { return visitChildren(node, data); }
    public Object visit(Div node, Object data) { return visitChildren(node, data); }
    public Object visit(Mod node, Object data) { return visitChildren(node, data); }
}
