import java.io.IOException;

%%

%byaccj
%line
%column

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
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
Skip = "skip"
Var = [a-zA-Z]+
Write = "write "
Read = "read "
While = "while "
Do = "do "
If = "if "
Then = "then "
Else = "else "
Comment =  "//" [^\r\n]* {NewLine}? | "(*" [^*] ~"*)"
Other = [^]

%%
{Comment} {
	System.out.printf("Comment(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
}

{Num} {
	System.out.printf("Num(\"%s\", %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	return Parser.NUM;
}


{Operator} {
	System.out.printf("Op(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	return Parser.OPERATOR;
}

{Set} {
	System.out.printf("KW_Set(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.SET;
}

{Semicolon} {
	System.out.printf("Semicolon(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.SEMICOLON;
}

{NewLine} {
	return Parser.NL;
}


"(" {
	System.out.printf("Bracket(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	return Parser.LBKT;
}

")" {
	System.out.printf("Bracket(%s, %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	return Parser.RBKT;
}

{Skip} {
	System.out.printf("KW_Skip(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.SKIP;
}

{Var} {
	System.out.printf("Var(\"%s\", %d, %d, %d); ", yytext(), yyline, yycolumn, yycolumn + yytext().length());
	return Parser.VAR;
}

{Write} {
	System.out.printf("KW_Write(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.WRITE;
}

{Read} {
	System.out.printf("KW_Read(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.READ;
}

{While} {
	System.out.printf("KW_While(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.WHILE;
}

{Do} {
	System.out.printf("KW_Do(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.DO;
}

{If} {
	System.out.printf("KW_If(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.IF;
}

{Then} {
	System.out.printf("KW_Then(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.THEN;
}

{Else} {
	System.out.printf("KW_Else(%d, %d, %d); ", yyline, yycolumn, yycolumn + yytext().length());
	return Parser.ELSE;

}

{Other} {

}