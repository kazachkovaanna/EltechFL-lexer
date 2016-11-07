//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "mylex.y"
  import java.io.*;
import java.util.ArrayList;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//## **user defined:ParserVal
String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[] = new ParserVal[YYSTACKSIZE];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
final void val_init()
{
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
final void val_push(ParserVal val)
{
  try {
    valptr++;
    valstk[valptr]=val;
  }
  catch (ArrayIndexOutOfBoundsException e) {
    int oldsize = valstk.length;
    int newsize = oldsize*2;
    ParserVal[] newstack = new ParserVal[newsize];
    System.arraycopy(valstk,0,newstack,0,oldsize);
    valstk = newstack;
    valstk[valptr]=val;
  }
}
final ParserVal val_pop()
{
  return valstk[valptr--];
}
final void val_drop(int cnt)
{
  valptr -= cnt;
}
final ParserVal val_peek(int relative)
{
  return valstk[valptr-relative];
}
final ParserVal dup_yyval(ParserVal val)
{
  return val;
}
//#### end semantic value section ####
public final static short NUM=257;
public final static short VAR=258;
public final static short OPERATOR=259;
public final static short SET=260;
public final static short SEMICOLON=261;
public final static short NL=262;
public final static short LBKT=263;
public final static short RBKT=264;
public final static short SKIP=265;
public final static short WRITE=266;
public final static short READ=267;
public final static short WHILE=268;
public final static short DO=269;
public final static short IF=270;
public final static short ELSE=271;
public final static short COMMENT=272;
public final static short THEN=273;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    0,    1,    1,    1,    1,    1,    1,    1,    3,
    3,    3,    3,    2,
};
final static short yylen[] = {                            2,
    0,    1,    1,    3,    3,    2,    2,    4,    6,    1,
    1,    3,    3,    1,
};
final static short yydefred[] = {                         0,
   14,    3,    0,    0,    0,    0,    0,    0,    0,   10,
   11,    0,    0,    7,    0,    0,    0,    0,    0,    0,
    0,    0,    5,    0,   13,   12,    8,    0,    0,    9,
};
final static short yydgoto[] = {                          7,
    8,    9,   13,
};
final static short yysindex[] = {                      -246,
    0,    0, -250, -249, -250, -250,    0, -244, -231,    0,
    0, -250, -234,    0, -254, -259, -246, -250, -253, -250,
 -246, -246,    0, -234,    0,    0,    0, -235, -246,    0,
};
final static short yyrindex[] = {                        30,
    0,    0,    0,    0,    0,    0,    0,   31,    0,    0,
    0,    0,    1,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    2,    0,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    6,   28,   -2,
};
final static int YYTABLESIZE=273;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         20,
    6,    4,   15,   16,   20,   20,   10,   11,    1,   19,
   25,    1,   12,   22,   21,   24,   17,   26,    2,    3,
    4,    5,   23,    6,   20,   17,   27,   28,   18,    1,
    2,   14,    0,    0,   30,   29,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    6,    4,    0,    0,    0,    0,    0,    0,    0,
    0,    6,    4,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                        259,
    0,    0,    5,    6,  259,  259,  257,  258,  258,   12,
  264,  258,  263,  273,  269,   18,  261,   20,  265,  266,
  267,  268,   17,  270,  259,  261,   21,   22,  260,    0,
    0,    4,   -1,   -1,   29,  271,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  261,  261,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  271,  271,
};
}
final static short YYFINAL=7;
final static short YYMAXTOKEN=273;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"NUM","VAR","OPERATOR","SET","SEMICOLON","NL","LBKT","RBKT",
"SKIP","WRITE","READ","WHILE","DO","IF","ELSE","COMMENT","THEN",
};
final static String yyrule[] = {
"$accept : input",
"input :",
"input : S",
"S : SKIP",
"S : VART SET EXPR",
"S : S SEMICOLON S",
"S : WRITE EXPR",
"S : READ VART",
"S : WHILE EXPR DO S",
"S : IF EXPR THEN S ELSE S",
"EXPR : NUM",
"EXPR : VAR",
"EXPR : EXPR OPERATOR EXPR",
"EXPR : LBKT EXPR RBKT",
"VART : VAR",
};

//#line 154 "mylex.y"
    private Yylex lexer;
    private GenericTreeNode root;
    public static boolean interactive;
    public ArrayList list;
    private GenericTreeNode node;
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
      lexer = new Yylex(r, this, false);
      list = new ArrayList();
    }
    public GenericTreeNode getRoot(){
        return root;
    }
//#line 272 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 2:
//#line 36 "mylex.y"
{
            /*$$ = new ParserVal(new GenericTreeNode("start"));*/
            node = (GenericTreeNode)val_peek(0).obj;
            root = node;
        }
break;
case 3:
//#line 44 "mylex.y"
{
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("skip"));
            list.add("skip");
        }
break;
case 4:
//#line 51 "mylex.y"
{
            list.add("var := expr"); 
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild((GenericTreeNode)val_peek(2).obj);
            node.addChild(new GenericTreeNode(":="));
            node.addChild((GenericTreeNode)val_peek(0).obj);
        }
break;
case 5:
//#line 60 "mylex.y"
{
            list.add("s ; s");
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild((GenericTreeNode)val_peek(2).obj);
            node.addChild(new GenericTreeNode(";"));
            node.addChild((GenericTreeNode)val_peek(0).obj);
        }
break;
case 6:
//#line 69 "mylex.y"
{
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("write"));
            node.addChild((GenericTreeNode)val_peek(0).obj);
            list.add("write expr"); 
        }
break;
case 7:
//#line 77 "mylex.y"
{
            list.add("read expr"); 
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("read"));
            node.addChild((GenericTreeNode)val_peek(0).obj);
        }
break;
case 8:
//#line 85 "mylex.y"
{
            list.add("while expr do s"); 
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("while"));
            node.addChild((GenericTreeNode)val_peek(4).obj);
            node.addChild(new GenericTreeNode("do"));
            node.addChild((GenericTreeNode)val_peek(1).obj);
        }
break;
case 9:
//#line 95 "mylex.y"
{
            list.add("if expr then s else s"); 
            yyval = new ParserVal(new GenericTreeNode("s"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("if"));
            node.addChild((GenericTreeNode)val_peek(5).obj);
            node.addChild(new GenericTreeNode("then"));
            node.addChild((GenericTreeNode)val_peek(3).obj);
            node.addChild(new GenericTreeNode("else"));
            node.addChild((GenericTreeNode)val_peek(1).obj);
        }
break;
case 10:
//#line 109 "mylex.y"
{
            list.add("num  "+ yyval.ival); 
            yyval = new ParserVal(new GenericTreeNode("expr"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("num"));
            node.getChildAt(0).addChild(new GenericTreeNode(val_peek(0).ival));
        }
break;
case 11:
//#line 117 "mylex.y"
{
            list.add("var  "+ yyval.sval); 
            yyval = new ParserVal(new GenericTreeNode("expr"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("var"));
            node.getChildAt(0).addChild(new GenericTreeNode(val_peek(0).sval));
        }
break;
case 12:
//#line 125 "mylex.y"
{
            list.add("expr " + yyval.sval +" expr"); 
            String old = yyval.sval;
            yyval = new ParserVal(new GenericTreeNode("expr"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild((GenericTreeNode)val_peek(2).obj);
            node.addChild(new GenericTreeNode(val_peek(1).sval));
            node.addChild((GenericTreeNode)val_peek(0).obj);
        }
break;
case 13:
//#line 135 "mylex.y"
{
            list.add("(expr) ");
            yyval = new ParserVal(new GenericTreeNode("expr"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode("("));
            node.addChild((GenericTreeNode)val_peek(2).obj);
            node.addChild(new GenericTreeNode(")"));
        }
break;
case 14:
//#line 145 "mylex.y"
{
            list.add("var  "+ yyval.sval); 
            String old = yyval.sval;
            yyval = new ParserVal(new GenericTreeNode("var"));
            node = (GenericTreeNode)yyval.obj;
            node.addChild(new GenericTreeNode(old));
        }
break;
//#line 559 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
