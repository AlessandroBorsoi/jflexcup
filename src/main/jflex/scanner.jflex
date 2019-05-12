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

Identifier = [:jletter:] [:jletterdigit:]*

DecIntegerLiteral = 0 | [1-9][0-9]*

%%

<YYINITIAL> "let"                { return symbol(sym.LET); }

<YYINITIAL> {
  /* identifiers */
  {Identifier}                   { return symbol(sym.IDENTIFIER, yytext()); }

  /* literals */
  {DecIntegerLiteral}            { return symbol(sym.INTEGER_LITERAL, new Integer(yytext())); }

  /* separators */
  ";"                            { return symbol(sym.SEMICOLON); }

  /* operators */
  "="                            { return symbol(sym.EQ); }
  "+"                            { return symbol(sym.PLUS); }
  "-"                            { return symbol(sym.MINUS); }
  "*"                            { return symbol(sym.TIMES); }
  "/"                            { return symbol(sym.DIVIDE); }
  "%"                            { return symbol(sym.MOD); }

  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

/* error fallback */
[^]                              { throw new Error("Illegal character <"+yytext()+">"); }