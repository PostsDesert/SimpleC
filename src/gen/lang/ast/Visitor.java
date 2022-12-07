package lang.ast;

import java.io.PrintStream;
import java.util.Scanner;
import java.util.Set;
import java.util.HashMap;
import java.util.TreeSet;
import java.io.ByteArrayOutputStream;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

/**
 * Visitor interface for SimpliC language. Each concrete node type must
 * have a visit method.
 * @ast interface
 * @aspect Visitor
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Visitor.jrag:6
 */
public interface Visitor {

         
        public Object visit(List node, Object data);

         
        public Object visit(FuncDesc node, Object data);

         
        public Object visit(Block node, Object data);

         
        public Object visit(Stmt node, Object data);

         
        public Object visit(AssignStmt node, Object data);

         
        public Object visit(IfStmt node, Object data);

         
        public Object visit(WhileStmt node, Object data);

         
        public Object visit(ReturnStmt node, Object data);

         
        public Object visit(FuncCallStmt node, Object data);

         
        public Object visit(VarDeclStmt node, Object data);

         
        public Object visit(Opt node, Object data);

         
        public Object visit(Expr node, Object data);

         
        public Object visit(IdDecl node, Object data);

         
        public Object visit(IdUse node, Object data);

         
        public Object visit(Numeral node, Object data);

         
        public Object visit(ParenExpr node, Object data);

         
        public Object visit(UnaryMinus node, Object data);

         
        public Object visit(FuncCall node, Object data);

         
        public Object visit(Program node, Object data);

         
        public Object visit(Eq node, Object data);

         
        public Object visit(Neq node, Object data);

         
        public Object visit(Leq node, Object data);

         
        public Object visit(Geq node, Object data);

         
        public Object visit(Lt node, Object data);

         
        public Object visit(Gt node, Object data);

         
        public Object visit(Plus node, Object data);

         
        public Object visit(Minus node, Object data);

         
        public Object visit(Mul node, Object data);

         
        public Object visit(Div node, Object data);

         
        public Object visit(Mod node, Object data);
}
