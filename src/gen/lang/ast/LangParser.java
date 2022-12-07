package lang.ast;
import beaver.*;
import java.util.ArrayList;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Stack;
import java.util.zip.DeflaterOutputStream;

// This is a parser generated by NeoBeaver.
public class LangParser extends beaver.Parser {
  
	static public class SyntaxError extends RuntimeException { public SyntaxError(String msg) {super(msg);}}
	// Disable syntax error recovery
	protected void recoverFromError(Symbol token, TokenStream in) {
		throw new SyntaxError("Cannot recover from the syntax error");
	}

  public static class Terminals {
    public static final short EOF = 0;
    public static final short MINUS = 1;
    public static final short ID = 2;
    public static final short RPAREN = 3;
    public static final short COMMA = 4;
    public static final short SEMICOLON = 5;
    public static final short INT = 6;
    public static final short LPAREN = 7;
    public static final short PLUS = 8;
    public static final short IF = 9;
    public static final short RETURN = 10;
    public static final short RBRACE = 11;
    public static final short WHILE = 12;
    public static final short NUMERAL = 13;
    public static final short LEQ = 14;
    public static final short EQ = 15;
    public static final short GEQ = 16;
    public static final short LT = 17;
    public static final short GT = 18;
    public static final short NEQ = 19;
    public static final short MOD = 20;
    public static final short MUL = 21;
    public static final short DIV = 22;
    public static final short ASSIGN = 23;
    public static final short LBRACE = 24;
    public static final short ELSE = 25;

    public static final String[] NAMES = {
        "EOF",
        "MINUS",
        "ID",
        "RPAREN",
        "COMMA",
        "SEMICOLON",
        "INT",
        "LPAREN",
        "PLUS",
        "IF",
        "RETURN",
        "RBRACE",
        "WHILE",
        "NUMERAL",
        "LEQ",
        "EQ",
        "GEQ",
        "LT",
        "GT",
        "NEQ",
        "MOD",
        "MUL",
        "DIV",
        "ASSIGN",
        "LBRACE",
        "ELSE",
    };
  }

  private final Action[] actions = {
    new Action() { // [0] func_list = 
      public Symbol reduce(Symbol[] _symbols, int offset) {
        return new List();
      }
    },
    new Action() { // [1] program =  func_list
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final List funcs = (List) _symbols[offset + 1].value;
        return new Program(funcs);
      }
    },
    new Action() { // [2] GOAL =  program EOF
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Program program = (Program) _symbols[offset + 1].value;
        final Symbol sym2 = _symbols[offset + 2];
        return program;
      }
    },
    new Action() { // [3] func_list =  func_list func_desc
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final List list = (List) _symbols[offset + 1].value;
        final FuncDesc func = (FuncDesc) _symbols[offset + 2].value;
        return list.add(func);
      }
    },
    new Action() { // [4] id_decl =  INT ID
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol INT = _symbols[offset + 1];
        final Symbol id = _symbols[offset + 2];
        return new IdDecl(id);
      }
    },
    new Action() { // [5] param_list_opt = 
      public Symbol reduce(Symbol[] _symbols, int offset) {
        return new List();
      }
    },
    new Action() { // [6] param_list =  id_decl
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdDecl param = (IdDecl) _symbols[offset + 1].value;
        return new List().add(param);
      }
    },
    Action.RETURN, // [7] param_list_opt =  param_list (default action: return symbol 1)
    new Action() { // [8] func_desc =  id_decl LPAREN param_list_opt RPAREN block
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdDecl id = (IdDecl) _symbols[offset + 1].value;
        final Symbol LPAREN = _symbols[offset + 2];
        final List params = (List) _symbols[offset + 3].value;
        final Symbol RPAREN = _symbols[offset + 4];
        final Block body = (Block) _symbols[offset + 5].value;
        return new FuncDesc(id, params, body);
      }
    },
    new Action() { // [9] param_list =  param_list COMMA id_decl
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final List list = (List) _symbols[offset + 1].value;
        final Symbol COMMA = _symbols[offset + 2];
        final IdDecl param = (IdDecl) _symbols[offset + 3].value;
        return list.add(param);
      }
    },
    new Action() { // [10] id_use =  ID
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol id = _symbols[offset + 1];
        return new IdUse(id);
      }
    },
    Action.RETURN, // [11] stmt =  func_call_stmt (default action: return symbol 1)
    new Action() { // [12] stmt_list =  stmt
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Stmt stmt = (Stmt) _symbols[offset + 1].value;
        return new List().add(stmt);
      }
    },
    new Action() { // [13] block =  LBRACE RBRACE
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol LBRACE = _symbols[offset + 1];
        final Symbol RBRACE = _symbols[offset + 2];
        return new Block();
      }
    },
    new Action() { // [14] var_decl_stmt =  id_decl SEMICOLON
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdDecl var = (IdDecl) _symbols[offset + 1].value;
        final Symbol SEMICOLON = _symbols[offset + 2];
        return new VarDeclStmt(var, new Opt());
      }
    },
    new Action() { // [15] func_call_stmt =  func_call SEMICOLON
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final FuncCall func_call = (FuncCall) _symbols[offset + 1].value;
        final Symbol SEMICOLON = _symbols[offset + 2];
        return new FuncCallStmt(func_call);
      }
    },
    new Action() { // [16] arg_list_opt = 
      public Symbol reduce(Symbol[] _symbols, int offset) {
        return new List();
      }
    },
    new Action() { // [17] stmt_list =  stmt_list stmt
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final List list = (List) _symbols[offset + 1].value;
        final Stmt stmt = (Stmt) _symbols[offset + 2].value;
        return list.add(stmt);
      }
    },
    new Action() { // [18] block =  LBRACE stmt_list RBRACE
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol LBRACE = _symbols[offset + 1];
        final List stmts = (List) _symbols[offset + 2].value;
        final Symbol RBRACE = _symbols[offset + 3];
        return new Block(stmts);
      }
    },
    Action.RETURN, // [19] term =  numeral (default action: return symbol 1)
    Action.RETURN, // [20] additive =  multi (default action: return symbol 1)
    Action.RETURN, // [21] expr =  additive (default action: return symbol 1)
    Action.RETURN, // [22] multi =  term (default action: return symbol 1)
    new Action() { // [23] numeral =  NUMERAL
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol num = _symbols[offset + 1];
        return new Numeral(num);
      }
    },
    new Action() { // [24] arg_list =  expr
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr e = (Expr) _symbols[offset + 1].value;
        return new List().add(e);
      }
    },
    Action.RETURN, // [25] arg_list_opt =  arg_list (default action: return symbol 1)
    new Action() { // [26] return_stmt =  RETURN expr SEMICOLON
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol RETURN = _symbols[offset + 1];
        final Expr e = (Expr) _symbols[offset + 2].value;
        final Symbol SEMICOLON = _symbols[offset + 3];
        return new ReturnStmt(e);
      }
    },
    new Action() { // [27] unary_minus =  MINUS term
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol MINUS = _symbols[offset + 1];
        final Expr f = (Expr) _symbols[offset + 2].value;
        return new UnaryMinus(f);
      }
    },
    new Action() { // [28] var_decl_stmt =  id_decl ASSIGN expr SEMICOLON
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdDecl var = (IdDecl) _symbols[offset + 1].value;
        final Symbol ASSIGN = _symbols[offset + 2];
        final Expr e = (Expr) _symbols[offset + 3].value;
        final Symbol SEMICOLON = _symbols[offset + 4];
        return new VarDeclStmt(var, new Opt(e));
      }
    },
    new Action() { // [29] func_call =  id_use LPAREN arg_list_opt RPAREN
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdUse id = (IdUse) _symbols[offset + 1].value;
        final Symbol LPAREN = _symbols[offset + 2];
        final List args = (List) _symbols[offset + 3].value;
        final Symbol RPAREN = _symbols[offset + 4];
        return new FuncCall(id, args);
      }
    },
    new Action() { // [30] assign_stmt =  id_use ASSIGN expr SEMICOLON
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final IdUse id = (IdUse) _symbols[offset + 1].value;
        final Symbol ASSIGN = _symbols[offset + 2];
        final Expr e = (Expr) _symbols[offset + 3].value;
        final Symbol SEMICOLON = _symbols[offset + 4];
        return new AssignStmt(id, e);
      }
    },
    new Action() { // [31] multi =  multi DIV term
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol DIV = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Div(a, b);
      }
    },
    new Action() { // [32] multi =  multi MOD term
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol MOD = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Mod(a, b);
      }
    },
    new Action() { // [33] multi =  multi MUL term
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol MUL = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Mul(a, b);
      }
    },
    new Action() { // [34] compare_expr =  additive EQ additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol EQ = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Eq(a, b);
      }
    },
    new Action() { // [35] compare_expr =  additive LEQ additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol LEQ = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Leq(a, b);
      }
    },
    new Action() { // [36] compare_expr =  additive GT additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol GT = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Gt(a, b);
      }
    },
    new Action() { // [37] compare_expr =  additive GEQ additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol GEQ = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Geq(a, b);
      }
    },
    new Action() { // [38] compare_expr =  additive LT additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol LT = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Lt(a, b);
      }
    },
    new Action() { // [39] compare_expr =  additive NEQ additive
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol NEQ = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Neq(a, b);
      }
    },
    new Action() { // [40] additive =  additive PLUS multi
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol PLUS = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Plus(a, b);
      }
    },
    new Action() { // [41] additive =  additive MINUS multi
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Expr a = (Expr) _symbols[offset + 1].value;
        final Symbol MINUS = _symbols[offset + 2];
        final Expr b = (Expr) _symbols[offset + 3].value;
        return new Minus(a, b);
      }
    },
    new Action() { // [42] paren_expr =  LPAREN expr RPAREN
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol LPAREN = _symbols[offset + 1];
        final Expr e = (Expr) _symbols[offset + 2].value;
        final Symbol RPAREN = _symbols[offset + 3];
        return new ParenExpr(e);
      }
    },
    new Action() { // [43] while_stmt =  WHILE LPAREN expr RPAREN block
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol WHILE = _symbols[offset + 1];
        final Symbol LPAREN = _symbols[offset + 2];
        final Expr e = (Expr) _symbols[offset + 3].value;
        final Symbol RPAREN = _symbols[offset + 4];
        final Block b = (Block) _symbols[offset + 5].value;
        return new WhileStmt(e, b);
      }
    },
    new Action() { // [44] arg_list =  arg_list COMMA expr
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final List list = (List) _symbols[offset + 1].value;
        final Symbol COMMA = _symbols[offset + 2];
        final Expr e = (Expr) _symbols[offset + 3].value;
        return list.add(e);
      }
    },
    new Action() { // [45] opt_block = 
      public Symbol reduce(Symbol[] _symbols, int offset) {
        return new Opt();
      }
    },
    new Action() { // [46] if_stmt =  IF LPAREN expr RPAREN block opt_block
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol IF = _symbols[offset + 1];
        final Symbol LPAREN = _symbols[offset + 2];
        final Expr e = (Expr) _symbols[offset + 3].value;
        final Symbol RPAREN = _symbols[offset + 4];
        final Block if_true = (Block) _symbols[offset + 5].value;
        final Opt if_false = (Opt) _symbols[offset + 6].value;
        return new IfStmt(e, if_true, if_false);
      }
    },
    new Action() { // [47] opt_block =  ELSE block
      public Symbol reduce(Symbol[] _symbols, int offset) {
        final Symbol ELSE = _symbols[offset + 1];
        final Block block = (Block) _symbols[offset + 2].value;
        return new Opt(block);
      }
    },
  };

  static final ParsingTables PARSING_TABLES = new ParsingTables(
    "U9pjcbcC54KOn$ypipDxS8ZWSYtimUx2SYuBko0eegAYWguA2eA8g9XWeYyycdYyUCN4#E2" +
    "3KHyC3yOOdum#g37HnFXak0y5MJbNPRbaGH6r$7zzJDVKz4nF5sGo3zINNrTLV$Mlhwkwks" +
    "QcUu2wSKeXYGo68AMsiZm2r#9nhCS6F8aw0ACnYjkXf1f#6eagD6Ci9c0Ync0y6fszuz29w" +
    "UZ277HZ6cPW5cPYdgTdKlzf#hIdx$3qfl9wAaNpRvvd0JtcleLkQo$LvFaNqNy3RdJst#tj" +
    "b$83NXcepTCi2ucpN8#Z$iafIziLMjyhjRBVnrfrIOpjBnbzFvXJVqJQPFl#sydIXk8Xexu" +
    "AUXfcU5SRzOUD#Xh6kQWkvetuiPoPpKlgBmmlvWzDryHMoEZZf#joj2reG1FgYqlKd#7bef" +
    "eBwaBiOvjKGfjMezuMEmhKUK7CpNLFldckzlXnC3d2FoNas0RrN0swLhmR6qt8TJ0ryZgmY" +
    "JCx6mdeqVRFlR9nr3azL#TSYz3OnFd3nI$BrhN22gjvcn$X3ujqLi$LMTSYH3RdvuoUgpEk" +
    "NVaueb6duslYnb6dz5oTSgr836brqiKlozQrmWermEs0c5CVq7m3fWKU2yq9GSol1Px0i1W" +
    "tuwQiwvRYumrDTx77tyMSsfsQPmbknUsu0xTXgJ4B#dWcbpOUgtFwco3crmAFQQ77LkfS$o" +
    "g8#RN0OzhbnB6QW#3Sb9vixjDxOiQGT6zyYUfN$R55lHPnZkE#sABxuyUnNHFBeL77z5mTS" +
    "wr8Z38UsrMDdKOT5SJyMk0nBUjQL#GO2gL7uqlK4JrNHroB45dyPbA71J6$5dXCoxfivlgG" +
    "dgj3haKSso#2c5yBFAPbFNs2c5yBFAPbFGS5CRyMU4pBkkIp$cSnfwP$rXTEeZaW5bEpNoo" +
    "cvYUnc9eVnM9gzecLhb5x1J6$5dW2o$qkflO8Odujy0HcQ7OBOdujy9YMTTdSFxlqNEroBK" +
    "ApK#sC7MU7dgiThXLi$eJJQhkdsUvkNOi8P3C7s$HSRNEDnPbeeyr65p9ePsaEjsApbC9uD" +
    "70xbynJNdAUNKp657Jmj$SqJCSCPs#dj7MU8ZQY2StyRJu9hQxUyKFJPtBrOKfNKwZFiDPU" +
    "xybGJJTNG7s$LgQcsY8Ej7AcKDz67obK8HqQBxS#Hkix5H8XNz6YbAw9QfslTewjw8ZpasY" +
    "A#KCrO#CgPFGMcgIkHItgC0H38pGvvxJ4E3Ppa2wXpHYZNe8chu#4TrxTQwv6ympd2Y9l1q" +
    "PY5Bybr6Eq7m$L5d4YDEPOd7qsvwUIDGbVbJiV65O1nsPvByJJOPnL79lhhPBFZqqSQjnrF" +
    "fucTfnoNGVQkiDIqNKd64#PD9UvZXQEKrWJjVO66fjhfuBdsafZCGVP6AKdgpWsrqxAHcFn" +
    "dQ9SSQpk7xiriIoQCgxnLpNbqbYiErRtZuN6vdgp#SrKhhNNwlwfvC#VGXgRyLpLLCGvjRb" +
    "$RAvhcy$JcD#jhO$jYiq1W8ql1eyLnvDkk6z6doBFaVV8f#G7qWya4gI1D9Fbv6doCdcVV4" +
    "QsafD0aksIR9TSI5QIPychP0lvYkmXPu0KZo7LHXQGrMGpUODyIBuX#yaWK3M2J2LBo3h2T" +
    "bLlaO$9TwIFN0JIeyXCifIi9nn3cktIdv3loI5o2SYmNQQBB2CRoVFa7SAnPZZM3CTQpJ5K" +
    "SmpLFQINR28laNV9v#GBSWAeIPD6mZ7Kh23Fa5V81#HBifkS1cfhIIjPH5QHDUHrmZcf$Ph" +
    "i8mU0kg5oJdYQThtWdhI3afimzHdrm#GeEH$MteGXwhryI4Ix3Y#NDq#BIMy9VQyDsRV9op" +
    "SNqMqWhv4tlJhd49ndV4laIRgyBP6tM6#JZqBcv9Yd6oXb3aBqnxNoNY$lz$hS6Fz9VGMds" +
    "JHvOv105v8i9z56Pa7UAYQO9p0FQPRb2NG2KqadQMUR6MGQaRMYataxsqoWhuLC8isaYVIG" +
    "Rj91VwEZgq83cUxyJpcLfRK0BHuJoHGUIm$pEP0t5XdcISp$7xez1yC=");

  public LangParser() {
    super(PARSING_TABLES);
  }

  protected Symbol invokeReduceAction(int rule_num, int offset) {
    return actions[rule_num].reduce(_symbols, offset);
  }
}