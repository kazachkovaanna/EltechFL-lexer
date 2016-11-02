import java.io.IOException;

%%

%class Parser
%byaccj
%line
%column
%standalone

%{
  private Parser yyparser;

  public Parser(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

/*перечисляются возмоные элементы языка*/

Num = ("-")? [0-9]+
Operator = "+" | "-" | "*" | "/" | "%" | "==" | "!=" | ">" | ">=" | "<" | "<=" | "&&" | "||" | "**"
Set = ":="
Semicolon = ";"
NewLine = \n | \r | \r\n
Skip = "skip" ([ ,\t])
Var = [a-zA-Z]+
Write = "write" ([ ,\t])
Read = "read" ([ ,\t])
While = "while" ([ ,\t])
Do = "do" ([ ,\t])
If = "if" ([ ,\t])
Then = "then" ([ ,\t])
Else = "else"
Comment =  "//" [^\r\n]* {NewLine}? | "(*" [^]* ~"*)"
Other = [^]

%%
{Comment} {
	System.out.printf("Comment(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
}

{Num} {
	System.out.printf("Num(\"%s\", %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	
}


{Operator} {
	System.out.printf("Op(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	
}

{Set} {
	System.out.printf("KW_Set(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Semicolon} {
	System.out.printf("Semicolon(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{NewLine} | [ ,\t] {
	
}


"(" {
	System.out.printf("Bracket(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
}
	

")" {
	System.out.printf("Bracket(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	
}

{Skip} {
	System.out.printf("KW_Skip(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Write} {
	System.out.printf("KW_Write(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Read} {
	System.out.printf("KW_Read(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{While} {
	System.out.printf("KW_While(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Do} {
	System.out.printf("KW_Do(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{If} {
	System.out.printf("KW_If(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Then} {
	System.out.printf("KW_Then(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	
}

{Else} {
	System.out.printf("KW_Else(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	

}

{Var} {
	System.out.printf("Var(\"%s\", %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	
}

{Other} {
	throw new IOException("unexpected character: " + yytext());
}