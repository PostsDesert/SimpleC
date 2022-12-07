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
 * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/lang.ast:1
 * @astdecl Program : ASTNode ::= FuncDesc*;
 * @production Program : {@link ASTNode} ::= <span class="component">{@link FuncDesc}*</span>;

 */
public class Program extends ASTNode<ASTNode> implements Cloneable {
  /**
   * @aspect CodeGen
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:8
   */
  public void genCode(PrintStream out) {
    out.println(".global _start");
    out.println(".data");
    out.println("buf: .skip 1024");
    out.println();
    out.println(".text");
    out.println("_start:");

    // call main function
    out.println("        call main");

    // Call sys_exit:
    out.println("        movq $0, %rdi");
    out.println("        movq $60, %rax");
    out.println("        syscall"); // Done!

    // Print code gen for program
    for (FuncDesc func : getFuncDescList()) {
        func.genCode(out);
    }

    // Helper procedures for input/output:
    out.println("# Procedure to print number to stdout.");
    out.println("# C signature: void print(long int)");
    out.println("print:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        ### Convert integer to string (itoa).");
    out.println("        movq 16(%rbp), %rax");
    out.println("        leaq buf(%rip), %rsi    # RSI = write pointer (starts at end of buffer)");
    out.println("        addq $1023, %rsi");
    out.println("        movb $0x0A, (%rsi)      # insert newline");
    out.println("        movq $1, %rcx           # RCX = string length");
    out.println("        cmpq $0, %rax");
    out.println("        jge itoa_loop");
    out.println("        negq %rax               # negate to make RAX positive");
    out.println("itoa_loop:                      # do.. while (at least one iteration)");
    out.println("        movq $10, %rdi");
    out.println("        movq $0, %rdx");
    out.println("        idivq %rdi              # divide RDX:RAX by 10");
    out.println("        addb $0x30, %dl         # remainder + '0'");
    out.println("        decq %rsi               # move string pointer");
    out.println("        movb %dl, (%rsi)");
    out.println("        incq %rcx               # increment string length");
    out.println("        cmpq $0, %rax");
    out.println("        jg itoa_loop            # produce more digits");
    out.println("itoa_done:");
    out.println("        movq 16(%rbp), %rax");
    out.println("        cmpq $0, %rax");
    out.println("        jge print_end");
    out.println("        decq %rsi");
    out.println("        incq %rcx");
    out.println("        movb $0x2D, (%rsi)");
    out.println("print_end:");
    out.println("        movq $1, %rdi");
    out.println("        movq %rcx, %rdx");
    out.println("        movq $1, %rax");
    out.println("        syscall");
    out.println("        popq %rbp");
    out.println("        ret");
    out.println("");
    out.println("# Procedure to read number from stdin.");
    out.println("# C signature: long long int read(void)");
    out.println("read:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        ### R9  = sign");
    out.println("        movq $1, %r9            # sign <- 1");
    out.println("        ### R10 = sum");
    out.println("        movq $0, %r10           # sum <- 0");
    out.println("skip_ws: # skip any leading whitespace");
    out.println("        movq $0, %rdi");
    out.println("        leaq buf(%rip), %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("        cmpq $0, %rax");
    out.println("        jle atoi_done           # nchar <= 0");
    out.println("        movb (%rsi), %cl        # c <- current char");
    out.println("        cmp $32, %cl");
    out.println("        je skip_ws              # c == space");
    out.println("        cmp $13, %cl");
    out.println("        je skip_ws              # c == CR");
    out.println("        cmp $10, %cl");
    out.println("        je skip_ws              # c == NL");
    out.println("        cmp $9, %cl");
    out.println("        je skip_ws              # c == tab");
    out.println("        cmp $45, %cl            # check if negative");
    out.println("        jne atoi_loop");
    out.println("        movq $-1, %r9           # sign <- -1");
    out.println("        movq $0, %rdi");
    out.println("        leaq buf(%rip), %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("atoi_loop:");
    out.println("        cmpq $0, %rax           # while (nchar > 0)");
    out.println("        jle atoi_done           # leave loop if nchar <= 0");
    out.println("        movzbq (%rsi), %rcx     # move byte, zero extend to quad-word");
    out.println("        cmpq $0x30, %rcx        # test if < '0'");
    out.println("        jl atoi_done            # character is not numeric");
    out.println("        cmpq $0x39, %rcx        # test if > '9'");
    out.println("        jg atoi_done            # character is not numeric");
    out.println("        imulq $10, %r10         # multiply sum by 10");
    out.println("        subq $0x30, %rcx        # value of character");
    out.println("        addq %rcx, %r10         # add to sum");
    out.println("        movq $0, %rdi");
    out.println("        leaq buf(%rip), %rsi");
    out.println("        movq $1, %rdx");
    out.println("        movq $0, %rax");
    out.println("        syscall                 # get one char: sys_read(0, buf, 1)");
    out.println("        jmp atoi_loop           # loop back");
    out.println("atoi_done:");
    out.println("        imulq %r9, %r10         # sum *= sign");
    out.println("        movq %r10, %rax         # put result value in RAX");
    out.println("        popq %rbp");
    out.println("        ret");
    out.println();
    out.println("print_string:");
    out.println("        pushq %rbp");
    out.println("        movq %rsp, %rbp");
    out.println("        movq $1, %rdi");
    out.println("        movq 16(%rbp), %rsi");
    out.println("        movq 24(%rbp), %rdx");
    out.println("        movq $1, %rax");
    out.println("        syscall");
    out.println("        popq %rbp");
    out.println("        ret");
  }
  /**
   * @aspect PrettyPrint
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/PrettyPrint.jrag:27
   */
  public void prettyPrint(PrintStream out, String ind) {
        int numFunctions = getNumFuncDesc();
        int numFuncsProcessed = 0;
        for (FuncDesc f : getFuncDescList()) {
            f.prettyPrint(out, ind);
            if (numFuncsProcessed < numFunctions - 1) {
                out.println();
                numFuncsProcessed++;
            }
        }
    }
  /**
   * @aspect Visitor
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Visitor.jrag:111
   */
  public Object accept(Visitor visitor, Object data) {
        return visitor.visit(this, data);
    }
  /**
   * @aspect Interpreter
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:7
   */
  public void eval() {
        boolean hasMain = false;
        for (FuncDesc function : getFuncDescs()) {
            if (function.getFuncId().getID().equals("main")) {
                hasMain = true;
                function.eval(new ActivationRecord());
                break;
            }
        }
        if (!hasMain) throw new RuntimeException("No main function found");
    }
  /**
   * @declaredat ASTNode:1
   */
  public Program() {
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
    children = new ASTNode[1];
    setChild(new List(), 0);
  }
  /**
   * @declaredat ASTNode:14
   */
  @ASTNodeAnnotation.Constructor(
    name = {"FuncDesc"},
    type = {"List<FuncDesc>"},
    kind = {"List"}
  )
  public Program(List<FuncDesc> p0) {
    setChild(p0, 0);
  }
  /** @apilevel low-level 
   * @declaredat ASTNode:23
   */
  protected int numChildren() {
    return 1;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:27
   */
  public void flushAttrCache() {
    super.flushAttrCache();
    intType_reset();
    getPredefinedFunctions_reset();
    unknownDecl_reset();
    boolType_reset();
    localLookup_String_reset();
    unknownType_reset();
  }
  /** @apilevel internal 
   * @declaredat ASTNode:37
   */
  public void flushCollectionCache() {
    super.flushCollectionCache();
    Program_errors_visited = false;
    Program_errors_computed = false;
    
    Program_errors_value = null;
    contributorMap_Program_errors = null;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:46
   */
  public Program clone() throws CloneNotSupportedException {
    Program node = (Program) super.clone();
    return node;
  }
  /** @apilevel internal 
   * @declaredat ASTNode:51
   */
  public Program copy() {
    try {
      Program node = (Program) clone();
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
  public Program fullCopy() {
    return treeCopyNoTransform();
  }
  /**
   * Create a deep copy of the AST subtree at this node.
   * The copy is dangling, i.e. has no parent.
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   * @declaredat ASTNode:80
   */
  public Program treeCopyNoTransform() {
    Program tree = (Program) copy();
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
  public Program treeCopy() {
    Program tree = (Program) copy();
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
   * Replaces the FuncDesc list.
   * @param list The new list node to be used as the FuncDesc list.
   * @apilevel high-level
   */
  public Program setFuncDescList(List<FuncDesc> list) {
    setChild(list, 0);
    return this;
  }
  /**
   * Retrieves the number of children in the FuncDesc list.
   * @return Number of children in the FuncDesc list.
   * @apilevel high-level
   */
  public int getNumFuncDesc() {
    return getFuncDescList().getNumChild();
  }
  /**
   * Retrieves the number of children in the FuncDesc list.
   * Calling this method will not trigger rewrites.
   * @return Number of children in the FuncDesc list.
   * @apilevel low-level
   */
  public int getNumFuncDescNoTransform() {
    return getFuncDescListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the FuncDesc list.
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the FuncDesc list.
   * @apilevel high-level
   */
  public FuncDesc getFuncDesc(int i) {
    return (FuncDesc) getFuncDescList().getChild(i);
  }
  /**
   * Check whether the FuncDesc list has any children.
   * @return {@code true} if it has at least one child, {@code false} otherwise.
   * @apilevel high-level
   */
  public boolean hasFuncDesc() {
    return getFuncDescList().getNumChild() != 0;
  }
  /**
   * Append an element to the FuncDesc list.
   * @param node The element to append to the FuncDesc list.
   * @apilevel high-level
   */
  public Program addFuncDesc(FuncDesc node) {
    List<FuncDesc> list = (parent == null) ? getFuncDescListNoTransform() : getFuncDescList();
    list.addChild(node);
    return this;
  }
  /** @apilevel low-level 
   */
  public Program addFuncDescNoTransform(FuncDesc node) {
    List<FuncDesc> list = getFuncDescListNoTransform();
    list.addChild(node);
    return this;
  }
  /**
   * Replaces the FuncDesc list element at index {@code i} with the new node {@code node}.
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   */
  public Program setFuncDesc(FuncDesc node, int i) {
    List<FuncDesc> list = getFuncDescList();
    list.setChild(node, i);
    return this;
  }
  /**
   * Retrieves the FuncDesc list.
   * @return The node representing the FuncDesc list.
   * @apilevel high-level
   */
  @ASTNodeAnnotation.ListChild(name="FuncDesc")
  public List<FuncDesc> getFuncDescList() {
    List<FuncDesc> list = (List<FuncDesc>) getChild(0);
    return list;
  }
  /**
   * Retrieves the FuncDesc list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the FuncDesc list.
   * @apilevel low-level
   */
  public List<FuncDesc> getFuncDescListNoTransform() {
    return (List<FuncDesc>) getChildNoTransform(0);
  }
  /**
   * @return the element at index {@code i} in the FuncDesc list without
   * triggering rewrites.
   */
  public FuncDesc getFuncDescNoTransform(int i) {
    return (FuncDesc) getFuncDescListNoTransform().getChildNoTransform(i);
  }
  /**
   * Retrieves the FuncDesc list.
   * @return The node representing the FuncDesc list.
   * @apilevel high-level
   */
  public List<FuncDesc> getFuncDescs() {
    return getFuncDescList();
  }
  /**
   * Retrieves the FuncDesc list.
   * <p><em>This method does not invoke AST transformations.</em></p>
   * @return The node representing the FuncDesc list.
   * @apilevel low-level
   */
  public List<FuncDesc> getFuncDescsNoTransform() {
    return getFuncDescListNoTransform();
  }
  /**
   * @aspect <NoAspect>
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:26
   */
  /** @apilevel internal */
protected java.util.Map<ASTNode, java.util.Set<ASTNode>> contributorMap_Program_errors = null;

  /** @apilevel internal */
  protected void survey_Program_errors() {
    if (contributorMap_Program_errors == null) {
      contributorMap_Program_errors = new java.util.IdentityHashMap<ASTNode, java.util.Set<ASTNode>>();
      collect_contributors_Program_errors(this, contributorMap_Program_errors);
    }
  }

/** @apilevel internal */
protected boolean intType_visited = false;
  /** @apilevel internal */
  private void intType_reset() {
    intType_computed = false;
    
    intType_value = null;
    intType_visited = false;
  }
  /** @apilevel internal */
  protected boolean intType_computed = false;

  /** @apilevel internal */
  protected IntType intType_value;

  /**
   * @attribute syn
   * @aspect IntType
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="IntType", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:2")
  public IntType intType() {
    ASTState state = state();
    if (intType_computed) {
      return intType_value;
    }
    if (intType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.intType().");
    }
    intType_visited = true;
    state().enterLazyAttribute();
    intType_value = new IntType();
    intType_value.setParent(this);
    intType_computed = true;
    state().leaveLazyAttribute();
    intType_visited = false;
    return intType_value;
  }
/** @apilevel internal */
protected boolean getPredefinedFunctions_visited = false;
  /** @apilevel internal */
  private void getPredefinedFunctions_reset() {
    getPredefinedFunctions_computed = false;
    
    getPredefinedFunctions_value = null;
    getPredefinedFunctions_visited = false;
  }
  /** @apilevel internal */
  protected boolean getPredefinedFunctions_computed = false;

  /** @apilevel internal */
  protected List<FuncDesc> getPredefinedFunctions_value;

  /**
   * @attribute syn
   * @aspect PredefinedFunctions
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/PredefinedFunctions.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="PredefinedFunctions", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/PredefinedFunctions.jrag:2")
  public List<FuncDesc> getPredefinedFunctions() {
    ASTState state = state();
    if (getPredefinedFunctions_computed) {
      return getPredefinedFunctions_value;
    }
    if (getPredefinedFunctions_visited) {
      throw new RuntimeException("Circular definition of attribute Program.getPredefinedFunctions().");
    }
    getPredefinedFunctions_visited = true;
    state().enterLazyAttribute();
    getPredefinedFunctions_value = getPredefinedFunctions_compute();
    getPredefinedFunctions_value.setParent(this);
    getPredefinedFunctions_computed = true;
    state().leaveLazyAttribute();
    getPredefinedFunctions_visited = false;
    return getPredefinedFunctions_value;
  }
  /** @apilevel internal */
  private List<FuncDesc> getPredefinedFunctions_compute() {
   		List<FuncDesc> list = new List<FuncDesc>();
   		list.add(new FuncDesc(new IdDecl("read"), new List(), new Block(new List())));
   		list.add(new FuncDesc(new IdDecl("print"), new List(new IdDecl("output")), new Block(new List())));
   		return list;
   	}
/** @apilevel internal */
protected boolean unknownDecl_visited = false;
  /** @apilevel internal */
  private void unknownDecl_reset() {
    unknownDecl_computed = false;
    
    unknownDecl_value = null;
    unknownDecl_visited = false;
  }
  /** @apilevel internal */
  protected boolean unknownDecl_computed = false;

  /** @apilevel internal */
  protected UnknownDecl unknownDecl_value;

  /**
   * @attribute syn
   * @aspect UnknownDecl
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownDecl.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="UnknownDecl", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownDecl.jrag:2")
  public UnknownDecl unknownDecl() {
    ASTState state = state();
    if (unknownDecl_computed) {
      return unknownDecl_value;
    }
    if (unknownDecl_visited) {
      throw new RuntimeException("Circular definition of attribute Program.unknownDecl().");
    }
    unknownDecl_visited = true;
    state().enterLazyAttribute();
    unknownDecl_value = new UnknownDecl("<unknown>");
    unknownDecl_value.setParent(this);
    unknownDecl_computed = true;
    state().leaveLazyAttribute();
    unknownDecl_visited = false;
    return unknownDecl_value;
  }
/** @apilevel internal */
protected boolean boolType_visited = false;
  /** @apilevel internal */
  private void boolType_reset() {
    boolType_computed = false;
    
    boolType_value = null;
    boolType_visited = false;
  }
  /** @apilevel internal */
  protected boolean boolType_computed = false;

  /** @apilevel internal */
  protected BoolType boolType_value;

  /**
   * @attribute syn
   * @aspect BoolType
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/BoolType.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="BoolType", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/BoolType.jrag:2")
  public BoolType boolType() {
    ASTState state = state();
    if (boolType_computed) {
      return boolType_value;
    }
    if (boolType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.boolType().");
    }
    boolType_visited = true;
    state().enterLazyAttribute();
    boolType_value = new BoolType();
    boolType_value.setParent(this);
    boolType_computed = true;
    state().leaveLazyAttribute();
    boolType_visited = false;
    return boolType_value;
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
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:11
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN)
  @ASTNodeAnnotation.Source(aspect="NameAnalysis", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:11")
  public IdDecl localLookup(String name) {
    Object _parameters = name;
    if (localLookup_String_visited == null) localLookup_String_visited = new java.util.HashSet(4);
    if (localLookup_String_values == null) localLookup_String_values = new java.util.HashMap(4);
    ASTState state = state();
    if (localLookup_String_values.containsKey(_parameters)) {
      return (IdDecl) localLookup_String_values.get(_parameters);
    }
    if (localLookup_String_visited.contains(_parameters)) {
      throw new RuntimeException("Circular definition of attribute Program.localLookup(String).");
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
  		for(int i = 0; i < getNumFuncDesc(); i++) {
  			IdDecl d = getFuncDesc(i).getFuncId();
  			if(d.getID().equals(name)) {
  				return d;
  			}
  		}
  		for(FuncDesc f : getPredefinedFunctions()) {
  			IdDecl d = f.getFuncId();
  			if(d.getID().equals(name)) {
  				return d;
  			}
  		}
  			return unknownDecl();
  	}
/** @apilevel internal */
protected boolean unknownType_visited = false;
  /** @apilevel internal */
  private void unknownType_reset() {
    unknownType_computed = false;
    
    unknownType_value = null;
    unknownType_visited = false;
  }
  /** @apilevel internal */
  protected boolean unknownType_computed = false;

  /** @apilevel internal */
  protected UnknownType unknownType_value;

  /**
   * @attribute syn
   * @aspect UnknownType
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownType.jrag:2
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.SYN, isNTA=true)
  @ASTNodeAnnotation.Source(aspect="UnknownType", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownType.jrag:2")
  public UnknownType unknownType() {
    ASTState state = state();
    if (unknownType_computed) {
      return unknownType_value;
    }
    if (unknownType_visited) {
      throw new RuntimeException("Circular definition of attribute Program.unknownType().");
    }
    unknownType_visited = true;
    state().enterLazyAttribute();
    unknownType_value = new UnknownType();
    unknownType_value.setParent(this);
    unknownType_computed = true;
    state().leaveLazyAttribute();
    unknownType_visited = false;
    return unknownType_value;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:4
   * @apilevel internal
   */
  public IntType Define_intType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return intType();
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/IntType.jrag:4
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute intType
   */
  protected boolean canDefine_intType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CodeGen.jrag:346
   * @apilevel internal
   */
  public int Define_paramIndex(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return 0;
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
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownDecl.jrag:4
   * @apilevel internal
   */
  public UnknownDecl Define_unknownDecl(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownDecl();
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownDecl.jrag:4
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownDecl
   */
  protected boolean canDefine_unknownDecl(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/BoolType.jrag:4
   * @apilevel internal
   */
  public BoolType Define_boolType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return boolType();
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/BoolType.jrag:4
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute boolType
   */
  protected boolean canDefine_boolType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:28
   * @apilevel internal
   */
  public Program Define_program(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return this;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:28
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute program
   */
  protected boolean canDefine_program(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/NameAnalysis.jrag:3
   * @apilevel internal
   */
  public IdDecl Define_lookup(ASTNode _callerNode, ASTNode _childNode, String name) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return localLookup(name);
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
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownType.jrag:4
   * @apilevel internal
   */
  public UnknownType Define_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return unknownType();
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/UnknownType.jrag:4
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute unknownType
   */
  protected boolean canDefine_unknownType(ASTNode _callerNode, ASTNode _childNode) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CircularDefinitions.jrag:3
   * @apilevel internal
   */
  public boolean Define_inExprOf(ASTNode _callerNode, ASTNode _childNode, IdDecl decl) {
    int childIndex = this.getIndexOfChild(_callerNode);
    return false;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/CircularDefinitions.jrag:3
   * @apilevel internal
   * @return {@code true} if this node has an equation for the inherited attribute inExprOf
   */
  protected boolean canDefine_inExprOf(ASTNode _callerNode, ASTNode _childNode, IdDecl decl) {
    return true;
  }
  /**
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Interpreter.jrag:238
   * @apilevel internal
   */
  public String Define_uniquePrefix(ASTNode _callerNode, ASTNode _childNode) {
    int childIndex = this.getIndexOfChild(_callerNode);
    {
    		return "<Unknown>";
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
protected boolean Program_errors_visited = false;
  /**
   * @attribute coll
   * @aspect Errors
   * @declaredat /h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:26
   */
  @ASTNodeAnnotation.Attribute(kind=ASTNodeAnnotation.Kind.COLL)
  @ASTNodeAnnotation.Source(aspect="Errors", declaredAt="/h/d1/p/gr6507ba-s/EDAN65/p41-griffith-baker/6/A6-SimpliC/src/jastadd/Errors.jrag:26")
  public Set<ErrorMessage> errors() {
    ASTState state = state();
    if (Program_errors_computed) {
      return Program_errors_value;
    }
    if (Program_errors_visited) {
      throw new RuntimeException("Circular definition of attribute Program.errors().");
    }
    Program_errors_visited = true;
    state().enterLazyAttribute();
    Program_errors_value = errors_compute();
    Program_errors_computed = true;
    state().leaveLazyAttribute();
    Program_errors_visited = false;
    return Program_errors_value;
  }
  /** @apilevel internal */
  private Set<ErrorMessage> errors_compute() {
    ASTNode node = this;
    while (node != null && !(node instanceof Program)) {
      node = node.getParent();
    }
    Program root = (Program) node;
    root.survey_Program_errors();
    Set<ErrorMessage> _computedValue = new TreeSet<ErrorMessage>();
    if (root.contributorMap_Program_errors.containsKey(this)) {
      for (ASTNode contributor : (java.util.Set<ASTNode>) root.contributorMap_Program_errors.get(this)) {
        contributor.contributeTo_Program_errors(_computedValue);
      }
    }
    return _computedValue;
  }
  /** @apilevel internal */
  protected boolean Program_errors_computed = false;

  /** @apilevel internal */
  protected Set<ErrorMessage> Program_errors_value;


}
