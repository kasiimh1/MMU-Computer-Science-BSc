import os
import antlr4 as ant

os.system("java -Xmx500M -cp antlr-4.7.2-complete.jar org.antlr.v4.Tool -Dlanguage=Python3 Decaf.g4 -visitor")

from DecafLexer import DecafLexer

filein = open('testdata/lexer/ws1', 'r')
lexer = DecafLexer(ant.InputStream(filein.read()))

done = False

#iterates over the length of the file until there is no next item to parse
token = lexer.nextToken()
while (token.type != -1):
       print(lexer.symbolicNames[token.type])
       token = lexer.nextToken()
