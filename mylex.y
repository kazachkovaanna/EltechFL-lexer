%{
  import java.io.*;
%}
      
%token NUM
%token VAR
%token OPERATOR
%token SET
%token SEMICOLON
%token NL
%token LBKT
%token RBKT
%token SKIP
%token WRITE
%token READ
%token WHILE
%token DO
%token IF
%token ELSE
      
%left OPERATOR
%left SET
%left SEMICOLON
%left THEN
%left ELSE
%left DO

%%

input: /*empty line*/
      | input line      {if(interactive) System.out.print("Type expression: ");}
      ;
      
line:  NL
      | S NL            {System.out.println();System.out.println();}
      ;

S:      SKIP
      | VAR SET EXPR
      | S SEMICOLON S
      | WRITE EXPR
      | READ EXPR
      | WHILE EXPR DO S
      | IF EXPR THEN S ELSE S
      ;

EXPR:     NUM
        | VAR
        | EXPR OPERATOR EXPR
        | LBKT EXPR RBKT
        ;

%%
  private Yylex lexer;
  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }
  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }
  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }
  static boolean interactive;
  public static void main(String args[]) throws IOException {
    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.print("Type expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }
    yyparser.yyparse();
  }