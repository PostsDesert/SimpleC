package lang.ast; // The generated scanner will belong to the package lang.ast

import lang.ast.LangParser.Terminals; // The terminals are implicitly defined in the parser
import lang.ast.LangParser.SyntaxError;

%%

// define the signature for the generated scanner
%public
%final
%class LangScanner
%extends beaver.Scanner

// the interface between the scanner and the parser is the nextToken() method
%type beaver.Symbol 
%function nextToken 

// store line and column information in the tokens
%line
%column

// this code will be inlined in the body of the generated scanner class
%{
  private beaver.Symbol sym(short id) {
    return new beaver.Symbol(id, yyline + 1, yycolumn + 1, yylength(), yytext());
  }
%}

// macros
WhiteSpace = [ ] | \t | \f | \n | \r
ID = [a-zA-Z]+[a-zA-Z0-9]*
Numeral = [0-9]+
Comment = "//"[^\n]*

%%

// discard whitespace information
{WhiteSpace}  { }

// token definitions
"int"        { return sym(Terminals.INT); }
"while"      { return sym(Terminals.WHILE); }
"if"         { return sym(Terminals.IF); }
"else"       { return sym(Terminals.ELSE); }
"return"     { return sym(Terminals.RETURN); }

"="          { return sym(Terminals.ASSIGN); }
","          { return sym(Terminals.COMMA); }

"("          { return sym(Terminals.LPAREN); }
")"          { return sym(Terminals.RPAREN); }
"{"          { return sym(Terminals.LBRACE); }
"}"          { return sym(Terminals.RBRACE); }

"*"          { return sym(Terminals.MUL); }
"/"          { return sym(Terminals.DIV); }
"%"          { return sym(Terminals.MOD); }
"+"          { return sym(Terminals.PLUS); }
"-"          { return sym(Terminals.MINUS); }

"=="         { return sym(Terminals.EQ); }
"!="         { return sym(Terminals.NEQ); }
"<="         { return sym(Terminals.LEQ); }
">="         { return sym(Terminals.GEQ); }
"<"          { return sym(Terminals.LT); }
">"          { return sym(Terminals.GT); }

";"          { return sym(Terminals.SEMICOLON); }
{Comment}     { /* ignore comments */ }
{ID}          { return sym(Terminals.ID); }
{Numeral}     { return sym(Terminals.NUMERAL); }
<<EOF>>       { return sym(Terminals.EOF); }

/* error fallback */
[^]           { throw new SyntaxError("Illegal character <"+yytext()+">"); }
