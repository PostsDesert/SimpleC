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
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/lang.ast:11
 * @astdecl FuncDesc : ASTNode ::= FuncId:IdDecl ParamId:IdDecl* Block;
 * @production FuncDesc : {@link ASTNode} ::= <span class="component">FuncId:{@link IdDecl}</span> <span class="component">ParamId:{@link IdDecl}*</span> <span class="component">{@link Block}</span>;

 */
public class FuncDesc extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:138
   */
  public void genCode(PrintStream out) {
    out.println(getFuncId().getID() + ":");
    // Allocate space for local variables:
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        subq $" + (numLocals() * 8) + ", %rsp");
    getBlock().genCode(out);
  }
  /**
   * @aspect PrettyPrint
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/PrettyPrint.jrag:39
   */
  public void prettyPrint(PrintStream out, String ind) {
	    out.print("int ");
        getFuncId().prettyPrint(out, ind);
        out.print("(");
        // Print Params
        boolean first = true;
            for (IdDecl param : getParamIdList()) {
                if (!first) {
                    out.print(", ");
                }
                param.prettyPrint(out, ind);
                first = false;
            }
        out.print(")");
	    getBlock().prettyPrint(out, ind);
	}
  /**
   * @aspect Visitor
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Visitor.jrag:47
   */
  public Object accept(Visitor visitor, Object data) {
        return visitor.visit(this, data);
    }
  /**
   * @aspect Interpreter
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:19
   */
  public int eval(ActivationRecord actrec) {
        try {
            getBlock().eval(actrec);
        } catch (ReturnException e) {
            return e.getValue();
        }
        return 0;
    }
  /**
   * @declaredat ASTNode:1
   */
  public FuncDesc() {
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
    setChild(new List(), 1);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"FuncId", "ParamId", "Block"},
    type = {"IdDecl", "List<IdDecl>", "Block"},
    kind = {"Child", "List", "Child"}
  )
  public FuncDesc(IdDecl p0, List<IdDecl> p1, Block p2) {
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
    localIndex_reset();
    localLookup_String_reset();
    reachable_reset();
    lookup_String_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    FuncDesc_functionCalls_visited = false;
    FuncDesc_functionCalls_computed = false;
    
    FuncDesc_functionCalls_value = null;
    contributorMap_FuncDesc_functionCalls = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public FuncDesc clone() throws CloneNotSupportedException {
    FuncDesc node = (FuncDesc) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public FuncDesc copy() {
    try {
      FuncDesc node = (FuncDesc) clone();
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
   * @declaredat ASTNode:70
   */
  @Deprecated
  public FuncDesc fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:80
   */
  public FuncDesc treeCopyNoTransform() {
    FuncDesc tree = (FuncDesc) copy();
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
   * @declaredat ASTNode:100
   */
  public FuncDesc treeCopy() {
    FuncDesc tree = (FuncDesc) copy();
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
   * Replaces the FuncId child.
   * @param node The new node to replace the FuncId child.
   * @apilevel high-level
   */
  public FuncDesc setFuncId(IdDecl node) {
    setChild(node, 0);
    return this;
  }
  /**
   * Retrieves the FuncId child.
   * @return The current node used as the FuncId child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="FuncId")
  public IdDecl getFuncId() {
    return (IdDecl) getChild(0);
  }
  /**
   * Retrieves the FuncId child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the FuncId child.
   * @apilevel low-level
   */
  public IdDecl getFuncIdNoTransform() {
    return (IdDecl) getChildNoTransform(0);
  }
  /**
   * Replaces the ParamId list.
   * @param list The new list node to be used as the ParamId list.
   * @apilevel high-level
   */
  public FuncDesc setParamIdList(List<IdDecl> list) {
    setChild(list, 1);
    return this;
  }
  /**
   * Retrieves the number of children in the ParamId list.
   * @return Number of children in the ParamId list.
   * @apilevel high-level
   */
  public int getNumParamId() {
    return getParamIdList().getNumChild();
  }
  /**
   * Retrieves the number of children in the ParamId list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the ParamId list.
   * @apilevel low-level
   */
  public int getNumParamIdNoTransform() {
    return getParamIdListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the ParamId list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the ParamId list.
   * @apilevel high-level
   */
  public IdDecl getParamId(int i) {
    return (IdDecl) getParamIdList().getChild(i);
  }
  /**
   * Check whether the ParamId list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasParamId() {
    return getParamIdList().getNumChild() != 0;
  }
  /**
   * Append an element to the ParamId list.
   * @param node The element to append to the ParamId list.
   * @apilevel high-level
   */
  public FuncDesc addParamId(IdDecl node) {
    List<IdDecl> list = (parent == null) ? getParamIdListNoTransform() : getParamIdList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public FuncDesc addParamIdNoTransform(IdDecl node) {
    List<IdDecl> list = getParamIdListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the ParamId list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public FuncDesc setParamId(IdDecl node, int i) {
    List<IdDecl> list = getParamIdList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the ParamId list.
   * @return The node representing the ParamId list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="ParamId")
  public List<IdDecl> getParamIdList() {
    List<IdDecl> list = (List<IdDecl>) getChild(1);
    return list;
  }
  /**
   * Retrieves the ParamId list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ParamId list.
   * @apilevel low-level
   */
  public List<IdDecl> getParamIdListNoTransform() {
    return (List<IdDecl>) getChildNoTransform(1);
  }
  /**
   * @return the element at index {@code i} in the ParamId list without
   * triggering rewrites.
   */
  public IdDecl getParamIdNoTransform(int i) {
    return (IdDecl) getParamIdListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the ParamId list.
   * @return The node representing the ParamId list.
   * @apilevel high-level
   */
  public List<IdDecl> getParamIds() {
    return getParamIdList();
  }
  /**
   * Retrieves the ParamId list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the ParamId list.
   * @apilevel low-level
   */
  public List<IdDecl> getParamIdsNoTransform() {
    return getParamIdListNoTransform();
  }
  /**
   * Replaces the Block child.
   * @param node The new node to replace the Block child.
   * @apilevel high-level
   */
  public FuncDesc setBlock(Block node) {
    setChild(node, 2);
    return this;
  }
  /**
   * Retrieves the Block child.
   * @return The current node used as the Block child.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.Child(name="Block")
  public Block getBlock() {
    return (Block) getChild(2);
  }
  /**
   * Retrieves the Block child.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The current node used as the Block child.
   * @apilevel low-level
   */
  public Block getBlockNoTransform() {
    return (Block) getChildNoTransform(2);
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:7
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_FuncDesc_functionCalls = null;

  /** @apilevel internal */
  protected void survey_FuncDesc_functionCalls() {
    if (contributorMap_FuncDesc_functionCalls == null) {
      contributorMap_FuncDesc_functionCalls = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_FuncDesc_functionCalls(this, contributorMap_FuncDesc_functionCalls);
    }
  }

/** @apilevel internal */
protected boolean localIndex_visited = false;
  /** @apilevel internal */
  private void localIndex_reset() {
    localIndex_computed = false;
    localIndex_visited = false;
  }
  /** @apilevel internal */
  protected boolean localIndex_computed = false;

  /** @apilevel internal */
  protected int localIndex_value;

  /**
   * @attribute syn
   * @aspect CodeGen
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:369
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="CodeGen", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:368")
  public int localIndex() {
    ASTState state = state();
    if (localIndex_computed) {
      return localIndex_value;
    }
    if (localIndex_visited) {
      throw new RuntimeException("Circular definition of attribute ASTNode.localIndex().");
    }
    localIndex_visited = true;
    state().enterLazyAttribute();
    localIndex_value = 0;
    localIndex_computed = true;
    state().leaveLazyAttribute();
    localIndex_visited = false;
    return localIndex_value;
  }
/** @apilevel internal */
protected java.util.Set localLookup_String_visited;
  /** @apilevel internal */
  private void localLookup_String_reset() {
    localLookup_String_values = null;
    localLookup_String_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map localLookup_String_values;

  /**
   * @attribute syn
   * @aspect NameAnalysis
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:36
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:36")
  public IdDecl localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_visited == null) localLookup_String_visited = new java.util.HashSet(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_values.containsKey(_parameters)) {
      return (IdDecl) localLookup_String_values.get(_parameters);
    }
    if (localLookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute FuncDesc.localLookup(String).");
    }
    localLookup_String_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl localLookup_String_value = localLookup_compute(name);
    localLookup_String_values.put(_parameters, localLookup_String_value);
    state().leaveLazyAttribute();
    localLookup_String_visited.remove(_parameters);
    return localLookup_String_value;
  }
  /** @apilevel internal */
  private IdDecl localLookup_compute(String name) {
  
  		for(int i = 0; i < getNumParamId(); i++) {
  			IdDecl decl = getParamId(i);
  			if(decl.getID().equals(name)) {
  				return decl;
  			}
  		}
  		return unknownDecl();
  	}
/** @apilevel internal */
protected ASTState.Cycle reachable_cycle = null;
  /** @apilevel internal */
  private void reachable_reset() {
    reachable_computed = false;
    reachable_initialized = false;
    reachable_value = null;
    reachable_cycle = null;
  }
  /** @apilevel internal */
  protected boolean reachable_computed = false;

  /** @apilevel internal */
  protected TreeSet<FuncDesc> reachable_value;
  /** @apilevel internal */
  protected boolean reachable_initialized = false;
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isCircular=true)
  @ASTNodeAnnotation.Source(aspect="Reachability", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Reachable.jrag:2")
  public TreeSet<FuncDesc> reachable() {
    if (reachable_computed) {
      return reachable_value;
    }
    ASTState state = state();
    if (!reachable_initialized) {
      reachable_initialized = true;
      reachable_value = new TreeSet<FuncDesc>(new FuncComparator());
    }
    if (!state.inCircle() || state.calledByLazyAttribute()) {
      state.enterCircle();
      do {
        reachable_cycle = state.nextCycle();
        TreeSet<FuncDesc> new_reachable_value = reachable_compute();
        if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
          state.setChangeInCycle();
        }
        reachable_value = new_reachable_value;
      } while (state.testAndClearChangeInCycle());
      reachable_computed = true;
      state.startLastCycle();
      TreeSet<FuncDesc> $tmp = reachable_compute();

      state.leaveCircle();
    } else if (reachable_cycle != state.cycle()) {
      reachable_cycle = state.cycle();
      if (state.lastCycle()) {
        reachable_computed = true;
        TreeSet<FuncDesc> new_reachable_value = reachable_compute();
        return new_reachable_value;
      }
      TreeSet<FuncDesc> new_reachable_value = reachable_compute();
      if (!AttributeValue.equals(reachable_value, new_reachable_value)) {
        state.setChangeInCycle();
      }
      reachable_value = new_reachable_value;
    } else {
    }
    return reachable_value;
  }
  /** @apilevel internal */
  private TreeSet<FuncDesc> reachable_compute() {
  		TreeSet<FuncDesc> r = new TreeSet<FuncDesc>(new FuncComparator());
    		for (FuncDesc f : functionCalls()) {
      			r.add(f);
      			r.addAll(f.reachable());
    		}
    		return r;
   	}
  /**
   * @attribute inh
   * @aspect NameAnalysis
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:27
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.INH)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:27")
  public IdDecl lookup(String name) {
    Object _parameters = name;
    if (lookup_String_visited == null) lookup_String_visited = new java.util.HashSet(4);
    if (lookup_String_values == null) lookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (lookup_String_values.containsKey(_parameters)) {
      return (IdDecl) lookup_String_values.get(_parameters);
    }
    if (lookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute FuncDesc.lookup(String).");
    }
    lookup_String_visited.add(_parameters);
    state().enterLazyAttribute();
    IdDecl lookup_String_value = getParent().Define_lookup(this, null, name);
    lookup_String_values.put(_parameters, lookup_String_value);
    state().leaveLazyAttribute();
    lookup_String_visited.remove(_parameters);
    return lookup_String_value;
  }
/** @apilevel internal */
protected java.util.Set lookup_String_visited;
  /** @apilevel internal */
  private void lookup_String_reset() {
    lookup_String_values = null;
    lookup_String_visited = null;
  }
  /** @apilevel internal */
  protected java.util.Map lookup_String_values;

  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:53
   * @apilevel internal
   */
  public boolean Define_isVariable(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParamIdListNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:76
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else if (_callerNode == getFuncIdNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:72
      return false;
    }
    else {
      return getParent().Define_isVariable(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:53
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isVariable
   */
  protected boolean canDefine_isVariable(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:54
   * @apilevel internal
   */
  public boolean Define_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParamIdListNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:77
      int childIndex = _callerNode.getIndexOfChild(_childNode);
      return false;
    }
    else if (_callerNode == getFuncIdNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:73
      return true;
    }
    else {
      return getParent().Define_isFunction(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:54
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute isFunction
   */
  protected boolean canDefine_isFunction(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:55
   * @apilevel internal
   */
  public FuncDesc Define_function(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getFuncIdNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:74
      return this;
    }
    else {
      return getParent().Define_function(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/TypeAnalysis.jrag:55
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute function
   */
  protected boolean canDefine_function(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:346
   * @apilevel internal
   */
  public int Define_paramIndex(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getParamIdListNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:348
      int i = _callerNode.getIndexOfChild(_childNode);
      return i + 1;
    }
    else {
      return getParent().Define_paramIndex(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:346
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute paramIndex
   */
  protected boolean canDefine_paramIndex(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:3
   * @apilevel internal
   */
  public IdDecl Define_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    if (_callerNode == getFuncIdNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:28
      {
      		return lookup(name);
      	}
    }
    else {
      int childIndex = this.getIndexOfChild(_callerNode);
      {
      		IdDecl decl = localLookup(name);
      		return !decl.isUnknown() ? decl : lookup(name);
      	}
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:3
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute lookup
   */
  protected boolean canDefine_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:12
   * @apilevel internal
   */
  public FuncDesc Define_enclosingFunction(ASTNode _callerNode, ASTNode _childNode) {
    if (_callerNode == getBlockNoTransform()) {
      // @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:15
      return this;
    }
    else {
      return getParent().Define_enclosingFunction(this, _callerNode);
    }
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:12
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute enclosingFunction
   */
  protected boolean canDefine_enclosingFunction(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:238
   * @apilevel internal
   */
  public String Define_uniquePrefix(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
    		return "";
    	}
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:238
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute uniquePrefix
   */
  protected boolean canDefine_uniquePrefix(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
/** @apilevel internal */
protected boolean FuncDesc_functionCalls_visited = false;
  /**
   * @attribute coll
   * @aspect FunctionCalls
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:7
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="FunctionCalls", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/FunctionCalls.jrag:7")
  public Set<FuncDesc> functionCalls() {
    ASTState state = state();
    if (FuncDesc_functionCalls_computed) {
      return FuncDesc_functionCalls_value;
    }
    if (FuncDesc_functionCalls_visited) {
      throw new RuntimeException("Circular definition of attribute FuncDesc.functionCalls().");
    }
    FuncDesc_functionCalls_visited = true;
    state().enterLazyAttribute();
    FuncDesc_functionCalls_value = functionCalls_compute();
    FuncDesc_functionCalls_computed = true;
    state().leaveLazyAttribute();
    FuncDesc_functionCalls_visited = false;
    return FuncDesc_functionCalls_value;
  }
  /** @apilevel internal */
  private Set<FuncDesc> functionCalls_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof FuncDesc)) {
      node = node.getParent();
    }
    FuncDesc root = (FuncDesc) node;
    root.survey_FuncDesc_functionCalls();
    Set<FuncDesc> _computedValue = new TreeSet<FuncDesc>(new FuncComparator());
    if (root.contributorMap_FuncDesc_functionCalls.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_FuncDesc_functionCalls.get(this)) {
        contributor.contributeTo_FuncDesc_functionCalls(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected boolean FuncDesc_functionCalls_computed = false;

  /** @apilevel internal */
  protected Set<FuncDesc> FuncDesc_functionCalls_value;


}
