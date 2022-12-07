/* This file was generated with JastAdd2 (http://jastadd.org) version 2.3.5 */
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
 * @ast node
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/lang.ast:17
 * @astdecl IfStmt : Stmt ::= Expr True:Block [False:Block];
 * @production IfStmt : {@link Stmt} ::= <span class="component">{@link Expr}</span> <span class="component">True:{@link Block}</span> <span class="component">[False:{@link Block}]</span>;

 */
public class IfStmt extends Stmt implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:170
   */
  public void genCode(PrintStream out) {
    out.println("        # IfStmt Start");
    getExpr().genConditionalFlags(out);

    String labelEnd = "fi_" + enclosingFunction().getFuncId().getID() + "_" + getTrue().uniquePrefix();
    if (hasFalse()) {
      String labelFalse = "else_" + enclosingFunction().getFuncId().getID() + "_" + getTrue().uniquePrefix();
      getExpr().genConditionalJump(out, labelFalse);
      getTrue().genCode(out);
      out.println("        jmp " + labelEnd);
      out.println(labelFalse + ":");
      getFalse().genCode(out);
    } else {
      getExpr().genConditionalJump(out, labelEnd);
      getTrue().genCode(out);
    }
    out.println(labelEnd + ":");
  }
  /**
   * @aspect PrettyPrint
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/PrettyPrint.jrag:72
   */
  public void prettyPrint(PrintStream out, String ind) {
        out.print(ind + "if (");
        getExpr().prettyPrint(out, ind);
        out.print(")");
        getTrue().prettyPrint(out, ind);
        if (hasFalse()) {
            out.print(" else");
            getFalse().prettyPrint(out, ind);
        }
        out.println();
    }
  /**
   * @aspect Visitor
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Visitor.jrag:63
   */
  public Object accept(Visitor visitor, Object data) {
        return visitor.visit(this, data);
    }
  /**
   * @aspect Interpreter
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:119
   */
  @Override
    public void eval(ActivationRecord actrec) {
        if (getExpr().eval(actrec) == 1) {
            getTrue().eval(actrec);
        } else if (hasFalse()) {
            getFalse().eval(actrec);
        }
    }
  /**
   * @declaredat ASTNode:1
   */
  public IfStmt() {
    super();
  }
  /**
   * Initializes the child array to the correct size.
   * Initializes List and Opt nta children.
   * @apilevel internal
   * @ast method
   * @declaredat ASTNode:10
   */
  public void init$Children() {
    children = new ASTNode[3];
    setChild(new Opt(), 2);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"Expr", "True", "False"},
    type = {"Expr", "Block", "Opt<Block>"},
    kind = {"Child", "Child", "Opt"}
  )
  public IfStmt(Expr p0, Block p1, Opt<Block> p2) {
    setChild(p0, 0);
    setChild(p1, 1);
    setChild(p2, 2);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:25
   */
  protected int numChildren() {
    return 3;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:29
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    compatibleType_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:34
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:39
   */
  public IfStmt clone() throws CloneNotSupportedException {
    IfStmt node = (IfStmt) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:44
   */
  public IfStmt copy() {
    try {
      IfStmt node = (IfStmt) clone();
      node.parent = null;
      if (children != null) {
        node.children = (ASTNode[]) children.clone();
      }
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @deprecated Please use treeCopy or treeCopyNoTransform instead
   * @declaredat ASTNode:63
   */
  @Deprecated
  public IfStmt fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:73
   */
  public IfStmt treeCopyNoTransform() {
    IfStmt tree = (IfStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) children[i];
        if (child != null) {
          child = child.treeCopyNoTransform();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The subtree of this node is traversed to trigger rewrites before copy.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:93
   */
  public IfStmt treeCopy() {
    IfStmt tree = (IfStmt) copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = (ASTNode) getChild(i);
        if (child != null) {
          child = child.treeCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * Replaces the Expr child.
   * @param node The new node to replace the Expr child.
   * @apilevel high-level
   */
  public IfStmt setExpr(Expr node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the Expr child.
   * @return The current node used as the Expr child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Expr")
  public Expr getExpr() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Expr child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Expr child.
   * @apilevel low-level
   */
  public Expr getExprNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the True child.
   * @param node The new node to replace the True child.
   * @apilevel high-level
   */
  public IfStmt setTrue(Block node) {
    setChild(node, 1);
    return this;
  }
  /**
   * Retrieves the True child.
   * @return The current node used as the True child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="True")
  public Block getTrue() {
    return (Block) getChild(1);
  }
  /**
   * Retrieves the True child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the True child.
   * @apilevel low-level
   */
  public Block getTrueNoTransform() {
    return (Block) getChildNoTransform(1);
  }
  /**
   * Replaces the optional node for the False child. This is the <code>Opt</code>
   * node containing the child False, not the actual child!
   * @param opt The new node to be used as the optional node for the False child.
   * @apilevel low-level
   */
  public IfStmt setFalseOpt(Opt<Block> opt) {
    setChild(opt, 2);
    return this;
  }
  /**
   * Replaces the (optional) False child.
   * @param node The new node to be used as the False child.
   * @apilevel high-level
   */
  public IfStmt setFalse(Block node) {
    getFalseOpt().setChild(node, 0);
    return this;
  }
  /**
   * Check whether the optional False child exists.
   * @return {@code true} if the optional False child exists, {@code false} if it does not.
   * @apilevel high-level
   */
  public boolean hasFalse() {
    return getFalseOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) False child.
   * @return The False child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   */
  public Block getFalse() {
    return (Block) getFalseOpt().getChild(0);
  }
  /**
   * Retrieves the optional node for the False child. This is the <code>Opt</code> node containing the child False, not the actual child!
   * @return The optional node for child the False child.
   * @apilevel low-level
   */
  @ASTNodeAnnotation.OptChild(name="False")
  public Opt<Block> getFalseOpt() {
    return (Opt<Block>) getChild(2);
  }
  /**
   * Retrieves the optional node for child False. This is the <code>Opt</code> node containing the child False, not the actual child!
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The optional node for child False.
   * @apilevel low-level
   */
  public Opt<Block> getFalseOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(2);
  }
/** @apilevel internal */
protected boolean compatibleType_visited = false;
  /** @apilevel internal */
  private void compatibleType_reset() {
    compatibleType_computed = false;
    compatibleType_visited = false;
  }
  /** @apilevel internal */
  protected boolean compatibleType_computed = false;

  /** @apilevel internal */
  protected boolean compatibleType_value;

  /**
   * @attribute syn
   * @aspect TypeAnalysis
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:50
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="TypeAnalysis", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:50")
  public boolean compatibleType() {
    ASTState state = state();
    if (compatibleType_computed) {
      return compatibleType_value;
    }
    if (compatibleType_visited) {
      throw new RuntimeException("Circular definition of attribute IfStmt.compatibleType().");
    }
    compatibleType_visited = true;
    state().enterLazyAttribute();
    compatibleType_value = getExpr().expectedType().compatibleType(getExpr().type());
    compatibleType_computed = true;
    state().leaveLazyAttribute();
    compatibleType_visited = false;
    return compatibleType_value;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:28
   * @apilevel internal
   */
  public Type Define_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getExprNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:33
      return boolType();
    }
    else {
      return getParent().Define_expectedType(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:28
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute expectedType
   */
  protected boolean canDefine_expectedType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /** @apilevel internal */
  protected void collect_contributors_Program_errors(Program _root, java.util.Map<ASTNode, java.util.Set<ASTNode>> _map) {
    // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:50
    if (!compatibleType()) {
      {
        Program target = (Program) (program());
        java.util.Set<ASTNode> contributors = _map.get(target);
        if (contributors == null) {
          contributors = new java.util.LinkedHashSet<ASTNode>();
          _map.put((ASTNode) target, contributors);
        }
        contributors.add(this);
      }
    }
    super.collect_contributors_Program_errors(_root, _map);
  }
  /** @apilevel internal */
  protected void contributeTo_Program_errors(Set<ErrorMessage> collection) {
    super.contributeTo_Program_errors(collection);
    if (!compatibleType()) {
      collection.add(error("IfStmt: Expected type '" + getExpr().expectedType().toString() + "', got '" + getExpr().type().toString() + "' instead"));
    }
  }

}
