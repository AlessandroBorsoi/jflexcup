package it.uniupo.disit.linguaggi2.jflexcup.generated;

import java_cup.runtime.*;

/**
*   Scanner per esercizio JLex e JCUP
*/
%%

%public
%class Scanner
%unicode
%cup
%line
%column

%{
  StringBuilder string = new StringBuilder();

  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}

LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace     = {LineTerminator} | [ \t\f]

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

FLOAT = "float"
INT = "int"
PRINT = "print"
ID = [a-z]+
INUM = [0-9]+
FNUM = [0-9]+.[0-9]+
ASSIGN = "="
PLUS = "+"
MINUS = "-"
TIMES = "*"
DIV = "/"


%%

<YYINITIAL> {FLOAT}              { return symbol(sym.FLOAT); }
<YYINITIAL> {INT}                { return symbol(sym.INT); }
<YYINITIAL> {PRINT}              { return symbol(sym.PRINT); }

<YYINITIAL> {
  /* identifiers */
  {ID}                           { return symbol(sym.ID, yytext()); }

  /* literals */
  {INUM}                         { return symbol(sym.INUM, new Integer(yytext())); }
  {FNUM}                         { return symbol(sym.FNUM, new Float(yytext())); }

  /* separators */
  {LineTerminator}               { return symbol(sym.SEPARATOR); }

  /* operators */
  {ASSIGN}                       { return symbol(sym.ASSIGN); }
  {PLUS}                         { return symbol(sym.PLUS); }
  {MINUS}                        { return symbol(sym.MINUS); }
  {TIMES}                        { return symbol(sym.TIMES); }
  {DIV}                          { return symbol(sym.DIV); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

/* error fallback */
[^]                              { throw new Error("Illegal character <"+yytext()+">"); }