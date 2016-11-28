import java.io.IOException;

%%

%byaccj
%line
%column

%{
  private Parser yyparser;
  private boolean yyfilter = false;

  public Yylex(java.io.Reader r, Parser yyparser, boolean filter) {
    this(r);
    this.yyparser = yyparser;
    this.yyfilter = filter;
  }

  public void setFilter(boolean filter) {
    this.yyfilter = filter;
  }

  public boolean getFilter() {
    return yyfilter;
  }

  public boolean atEOF() {
    return zzAtEOF;
  }

  private void printToken(String str){
  	System.out.println(str);
  }
  public static void main(String args[]){
  	if (args.length == 0) {
			System.err.println("Укажите фаил в параметрах запуска");
			System.exit(-1);
	}
        
	boolean filter = false;
	int pos = 0;
	if (args.length > 1 && args[1].equals("-filter")) {
		filter = true;
	} 
    Yylex scanner = null;
    try {
      java.io.FileInputStream stream = new java.io.FileInputStream(args[0]);
      java.io.Reader reader = new java.io.InputStreamReader(stream);
      scanner = new Yylex(reader, new Parser(reader), filter);
      while ( !scanner.zzAtEOF ) scanner.yylex();
    }
    catch (java.io.FileNotFoundException e) {
      System.out.printf("File not found : \"%s\"", args[0]);
    }
    catch (java.io.IOException e) {
      System.out.printf("IO error scanning file \"%s\"", args[0]);
      System.out.println(e);
    }
    catch (Exception e) {
      System.out.println("Unexpected exception:");
      e.printStackTrace();
    }
    filter = false;

	
  }
%}

/*перечисляются возмоные элементы языка*/

Num = ("-")? ([0-9]+) ([, ,\t] | {NewLine} )
Operator = "+" | "-" | "*" | "/" | "%" | "==" | "!=" | ">" | ">=" | "<" | "<=" | "&&" | "||" | "**"
Set = ":="
Semicolon = ";"
NewLine = \n | \r | \r\n
Skip = "skip" 
Var = [a-zA-Z]+
Write = "write" ([, ,\t] | {NewLine})
Read = "read" ([, ,\t] | {NewLine})
While = "while" ([, ,\t] | {NewLine})
Do = "do" ([, ,\t] | {NewLine})
If = "if" ([, ,\t] | {NewLine})
Then = "then" ([, ,\t] | {NewLine})
Else = "else" ([, ,\t] | {NewLine})
Comment =  "//" [^\r\n]* {NewLine}? | "(*" [^*]? ~"*)"
Other = [^]


%%
{Comment} {
    if (true == getFilter()) {
   		printToken("Comment(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
        return Parser.COMMENT;
    }
}

{Num} {
	printToken("Num("+yytext()+", " + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
    yyparser.yylval = new ParserVal(Integer.parseInt(yytext().replaceAll("\\s+","")));
	return Parser.NUM;
}


{Operator} {
	printToken("Operator("+yytext()+", " + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	yyparser.yylval = new ParserVal(yytext());
	return Parser.OPERATOR;
}

{Set} {
	printToken("Set(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.SET;
}

{Semicolon} {
	printToken("Semicolon(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.SEMICOLON;
}

{NewLine} | [ \t] {
}


"(" {
	printToken("Bracket("+yytext()+", " + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
    return Parser.LBKT;
}
	

")" {
	printToken("Bracket("+yytext()+", " + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
    return Parser.RBKT;
	
}

{Skip} {
	printToken("KW_Skip(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.SKIP;
}

{Write} {
	printToken("KW_Write(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.WRITE;
}

{Read} {
	printToken("KW_Read(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.READ;
}

{While} {
	printToken("KW_While(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.WHILE;
}

{Do} {
	printToken("KW_Do(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.DO;
}

{If} {
	printToken("KW_If(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.IF;
}

{Then} {
	printToken("KW_Then(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.THEN;
}

{Else} {
	printToken("KW_Else(" + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	return Parser.ELSE;

}

{Var} {
	printToken("Var("+yytext()+", " + yyline+", " + yycolumn+ ", " + (yycolumn + yytext().length()) + ")");
	yyparser.yylval = new ParserVal(yytext());
	return Parser.VAR;
}

{Other} {
	throw new IOException("unexpected character: " + yytext());
}