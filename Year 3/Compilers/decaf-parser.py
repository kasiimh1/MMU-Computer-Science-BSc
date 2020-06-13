import os
import antlr4 as ant

os.system("java -Xmx500M -cp antlr-4.7.2-complete.jar org.antlr.v4.Tool -Dlanguage=Python3 Decaf.g4 -visitor")

from DecafLexer import DecafLexer
from DecafParser import DecafParser
from DecafVisitor import DecafVisitor

# This class is used to print the parser tree, 
# it does a BFS on the tree visiting each node and child nodes,
# mapping out the whole read in file and outputs as a string to be added to the parse tree html file.

#Grammer file is used to find and parse each node and their child nodes

class DecafTreeVisitor(DecafVisitor):
    def __init__(self):
        super().__init__()
        self.text = ''
        
    def printNode(self, ctx):
        self.text = self.text + '{\"name\": \"' + parser.ruleNames[ctx.getRuleIndex()] + '\",' + '\"value\":2,' + '\"children\":['
        self.visitChildren(ctx)
        self.text = self.text + ']},'
    
    def visitTerminal(self, ctx):
        self.text = self.text + '{\"name\": \"' + ctx.getText().replace('\\', '\\\\').replace('\"', '\\\"') + '\"},'

    #visit and print program node
    def visitProgram(self, ctx:DecafParser.ProgramContext):
        self.printNode(ctx)

    def visitMethod_decl(self, ctx:DecafParser.Method_declContext):
        self.printNode(ctx)
        
    def visitExpr(self, ctx:DecafParser.ExprContext):
        self.printNode(ctx)

    def visitStatement(self, ctx:DecafParser.StatementContext):
        self.printNode(ctx)
    
    def visitMethod_name(self, ctx:DecafParser.Method_nameContext):
        self.printNode(ctx)

    def visitField_decl(self, ctx:DecafParser.Field_declContext):
        self.printNode(ctx)

    def visitField_name(self, ctx:DecafParser.Field_nameContext):
        self.printNode(ctx)    

    def visitMethod_call(self, ctx:DecafParser.Method_callContext):
       self.printNode(ctx)    

    def visitBlock(self, ctx:DecafParser.block):
        self.printNode(ctx)    

    def visitVar_decl(self, ctx:DecafParser.var_decl):
        self.printNode(ctx)    
        
    def visitData_type(self, ctx:DecafParser.data_type):
        self.printNode(ctx)    

    def visitAssign_op(self, ctx:DecafParser.assign_op):
        self.printNode(ctx)
    
    def visitLocation(self, ctx:DecafParser.location):
        self.printNode(ctx)

    def visitCallout_arg(self, ctx:DecafParser.callout_arg):
        self.printNode(ctx)
    
    def visitBin_op(self, ctx:DecafParser.bin_op):
        self.printNode(ctx)

    def visitArith_op(self, ctx:DecafParser.arith_op):
        self.printNode(ctx)

    def visitRel_op(self, ctx:DecafParser.rel_op):
        self.printNode(ctx)

    def visitEq_op(self, ctx:DecafParser.eq_op):
        self.printNode(ctx)

    def visitCond_op(self, ctx:DecafParser.cond_op):
        self.printNode(ctx)

    def visitLiteral(self, ctx:DecafParser.literal):
        self.printNode(ctx)

    def visitInt_literal(self, ctx:DecafParser.int_literal):
        self.printNode(ctx)

    def visitBool_literal(self, ctx:DecafParser.bool_literal):
        self.printNode(ctx)

    def visitChar_literal(self, ctx:DecafParser.char_literal):
        self.printNode(ctx)

    def visitString_literal(self, ctx:DecafParser.string_literal):
        self.printNode(ctx)    

source = 'testdata/Program.dcf'
filein = open(source, 'r')
lexer = DecafLexer(ant.InputStream(filein.read()))

#create a token stream from the lexer
stream = ant.CommonTokenStream(lexer)

#create a new parser with the token stream as input
parser = DecafParser(stream)
tree = parser.program()
#tree = parser.expr()
#tree = parser.string_literal()
#tree = parser.char_literal()


#create a new calc visitor
tree_visitor = DecafTreeVisitor()
tree_visitor.visit(tree)

#output code
print(tree_visitor.text)