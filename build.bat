java -jar jflex-1.6.1.jar mylex.flex
yacc -J mylex.y
javac -sourcepath ./ Parser.java