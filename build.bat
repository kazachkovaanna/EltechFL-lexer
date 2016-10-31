java -jar jflex-1.6.1.jar mylex.flex
rem yacc -J mylex.y
rem javac -sourcepath ./ Parser.java
javac Parser.java