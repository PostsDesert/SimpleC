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
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/lang.ast:5
 * @astdecl IntType : Type;
 * @production IntType : {@link Type};

 */
public class IntType extends Type implements Cloneable {
  /**
   * @declaredat ASTNode:1
   */
  public IntType() {
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
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:13
   */
  protected int numChildren() {
    return 0;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:17
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    isIntType_reset();
    toString_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:23
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();

  }
  /** @apilevel internal 
   * @declaredat ASTNode:28
   */
  public IntType clone() throws CloneNotSupportedException {
    IntType node = (IntType) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:33
   */
  public IntType copy() {
    try {
      IntType node = (IntType) clone();
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
   * @declaredat ASTNode:52
   */
  @Deprecated
  public IntType fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:62
   */
  public IntType treeCopyNoTransform() {
    IntType tree = (IntType) copy();
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
   * @declaredat ASTNode:82
   */
  public IntType treeCopy() {
    IntType tree = (IntType) copy();
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
/** @apilevel internal */
protected boolean isIntType_visited = false;
  /** @apilevel internal */
  private void isIntType_reset() {
    isIntType_computed = false;
    isIntType_visited = false;
  }
  /** @apilevel internal */
  protected boolean isIntType_computed = false;

  /** @apilevel internal */
  protected boolean isIntType_value;

  /**
   * @attribute syn
   * @aspect IntType
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:8
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="IntType", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:7")
  public boolean isIntType() {
    ASTState state = state();
    if (isIntType_computed) {
      return isIntType_value;
    }
    if (isIntType_visited) {
      throw new RuntimeException("Circular definition of attribute Type.isIntType().");
    }
    isIntType_visited = true;
    state().enterLazyAttribute();
    isIntType_value = true;
    isIntType_computed = true;
    state().leaveLazyAttribute();
    isIntType_visited = false;
    return isIntType_value;
  }
/** @apilevel internal */
protected boolean toString_visited = false;
  /** @apilevel internal */
  private void toString_reset() {
    toString_computed = false;
    
    toString_value = null;
    toString_visited = false;
  }
  /** @apilevel internal */
  protected boolean toString_computed = false;

  /** @apilevel internal */
  protected String toString_value;

  /**
   * @attribute syn
   * @aspect IntType
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:9
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="IntType", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:9")
  public String toString() {
    ASTState state = state();
    if (toString_computed) {
      return toString_value;
    }
    if (toString_visited) {
      throw new RuntimeException("Circular definition of attribute IntType.toString().");
    }
    toString_visited = true;
    state().enterLazyAttribute();
    toString_value = "Int";
    toString_computed = true;
    state().leaveLazyAttribute();
    toString_visited = false;
    return toString_value;
  }

}
