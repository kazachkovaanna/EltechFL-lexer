java -jar jflex-1.6.1.jar mylex.flex
rem yacc -J mylex.y
javac -sourcepath ./ Yylex.java