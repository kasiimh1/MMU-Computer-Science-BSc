from DecafVisitor import DecafVisitor
from DecafParser import DecafParser
from DecafLexer import DecafLexer
import os
import SymbolTable 
import antlr4 as ant

os.system("java -Xmx500M -cp antlr-4.7.2-complete.jar org.antlr.v4.Tool -Dlanguage=Python3 Decaf.g4 -visitor")

class DecafCodeGenVisitor(DecafVisitor):
    
    def __init__(self):
        super().__init__()
        #set up the symbol table
        self.st = SymbolTable.SymbolTable()
        #add barebones asm code for codegen
        self.head = '.data\n'
        self.body = '.global main\n'
        self.body += 'movq %rsp, %rbp\n'
        #array to hold void methods 
        self.voidMethods = []

    def visitProgram(self, ctx: DecafParser.ProgramContext):
        # enter scope for the global scope
        self.st.enterScope()
        self.visitChildren(ctx)
        
        #main method lookup to check if the program currently being checked is valid, continue check if valid
        method_name = self.st.lookup('main')
        if method_name == None: 
            print('[Program] error, no main found')
        else:
            #Rule 4
             for i in ctx.field_decl():        
                if i.field_name(0).int_literal():
                    if i.getText() != None:
                        #check if the value is 0 or less.. negative numbers shouldn't get past the parser anyways!
                        if (int(i.field_name(0).int_literal().getText()) <= 0):
                            print('[Rule 4] error field_decl array declaration must be greater than 0. Found value:', i.field_name(0).int_literal().getText())               
        self.st.exitScope()

    def visitMethod_decl(self, ctx: DecafParser.Method_declContext):       
        # add the method_decl params to the symbol table one by one
        i = 1
        paramvalues = []
        if ctx.ID() and ctx.data_type(1):
            while i < len(ctx.ID()):  
                val = ctx.ID(i).getText()
                t = ctx.data_type(1).getText()
                i += 1     
                paramvalues.append(val)
                
                var_symbol = SymbolTable.VarSymbol(
                id=val,
                type=t,
                line=ctx.start.line,
                size=8,
                mem=SymbolTable.STACK
            )
            self.st.addSymbol(var_symbol)             
        
        if ctx.ID() and ctx.data_type():
            #add the method_decl and its type to the symbol table, with also the list of params 
            return_type = ctx.data_type(0).getText()
            method_name = ctx.ID(0).getText()
            line_number = ctx.start.line
                
            #sets values to be inserted into the symbol table
            method_symbol = SymbolTable.MethodSymbol(
                id=method_name,
                type=return_type,
                line=line_number,
                params = paramvalues
            )
            
            #add var symbol to the stack, you need to add method symbol to global scope and method symbol include param types, enter new scope,    
            #then loop through the param list and add to stack so theyre added to the symbol table

            #adds the above method to the symbol table for later use!            
            self.st.addSymbol(method_symbol)  
     
            #Rule 3 check if main has params, which it should not, we also check that method currently being checked is main, otherwise they are allowed to have params
            if len(self.st.lookup(method_name).params) > 0 and method_name == 'main':
                print("[Rule 3] error found parameters in main declaration on line", ctx.start.line)
        # enter the scope for the method scope
        self.st.enterScope()          
        #add params to table one by one
        self.visitChildren(ctx)

        #Rule 7 void method check if contains return in body! as the method we're checking should not have a return since it is VOID
        if ctx.VOID():
            if ctx.block().getText().find('return') != -1:
                print('[Rule 7] found void method, but return found on line', ctx.start.line , 'for method:', method_name)
                #catch the method_name if this method is type void
                self.voidMethods.append(method_name)
      
        self.st.exitScope()

    def visitField_decl(self, ctx: DecafParser.Field_declContext):
        #add all the variables to the symbol table
        if ctx.field_name() != None:
            return_type = ctx.data_type().getText()
            method_name = ctx.field_name(0).getText()
            line_number = ctx.start.line
                      
            #sets values to be inserted into the symbol table
            var_symbol = SymbolTable.VarSymbol(
                id=method_name,
                type=return_type,
                line=line_number,
                size=8,
                mem=SymbolTable.HEAP
            )
            self.st.addSymbol(var_symbol)                
        return self.visitChildren(ctx)

    def visitMethod_call(self, ctx: DecafParser.Method_callContext):
        #Rule 5 find out the count of params currently found for a method_call and check if they match the len which is in the symbol table for this method
        if ctx.method_name() != None and ctx.expr() != None:
            id = ctx.method_name().getText()   
            if self.st.lookup(id) == None or len(ctx.expr()) < len(self.st.lookup(id).params) or len(ctx.expr()) > len(self.st.lookup(id).params):
                count = 0
                if self.st.lookup(id) != None:
                    count = len(self.st.lookup(id).params) 
                print('[Rule 5] error param length do not match expected for method_call', id,'lookup param count is:', count ,'found params in call_method:', len(ctx.expr()))       
        return self.visitChildren(ctx)

    def visitBlock(self, ctx: DecafParser.BlockContext):
        #Rule 6, return method_call val in expr, check our array that we set up earlier with method_name for void methods 
        #see if it is called to return a value as it should not return a value as it is void and error
        for i in ctx.statement(): 
            for item in self.voidMethods:
                if i.getText().find(item) != -1:
                    print('[Rule 6] error expr expecting return val but', item , 'is a void method')
                else:
                    break
        return self.visitChildren(ctx)

    def visitVar_decl(self, ctx: DecafParser.Var_declContext):       
         #Rule 1 this rule checks if the same var is declared within the same scope by probing the current ID, this rule was provided by Ryan via screencast on moodle
         for id in ctx.ID(): 
            if (self.st.probe(id.getText()) == None) is True:
                print("[Rule 1] error on line", ctx.start.line, 'ID: (', id.getText(), ') redeclared in the same scope')
                
        #add var symbol table to the stack
         return self.visitChildren(ctx)

    def visitStatement(self, ctx: DecafParser.StatementContext):          
        #Rule 17 iterate through the expressions found within a for loop and check if they are digits by using a python built in feature, if not then the for loop is not valid and error displayed
        if ctx.FOR():
            for j, i in enumerate(ctx.expr()):
                if i.getText().isdigit() != True:
                    #Tell us which expr has the issue 0 = first 1 = second
                    print("[Rule 17] Expected type int for expr", j , "in this for loop but found error on line:", ctx.start.line, "is int?", i.getText().isdigit())

        #Rule 11 check if boolean has return in body! if not error out, simple check for a true or false within the statement
        if ctx.IF():
            temp = ctx.getText()
            if temp.find('true') == -1 and temp.find('false') == -1:
                print('[Rule 11] error on line', ctx.start.line, 'expected boolean type return value')
                         
        # rule 2, No identifier is used before it is declared, check if it exists in the symbol table, this rule was provided by Ryan via screencast on moodle
        if ctx.location():         
            id = ctx.location().ID().getText()
            if (self.st.lookup(id) == None):
                print("[Rule 2] error on line", ctx.start.line,'ID: (', id, ') not declared before use')
         
        if ctx.location():         
            if ctx.location().ID().getText() != None:
                if self.st.lookup(ctx.location().ID().getText()) == None:
                    print('[Rule 9] error ID  (', ctx.location().ID().getText(), ") does not exists in local/global variable or formal parameter, on line", ctx.start.line) 

        #Rule 15 check that location and expr have the same type by doing a lookup in the table if they exist if they do then test to see if types match otherwise
        #error and error if not in symbol table           
        if ctx.location() and ctx.expr():    
            lookup = self.st.lookup(ctx.location().getText())
            for i in ctx.expr():
                res = self.st.lookup(i.getText())
                if lookup == None and res == None or lookup and res and lookup.type != res.type:
                    print("[Rule 15] location and expr don't have same type")                                                     
    
        return self.visitChildren(ctx)

    def visitExpr(self, ctx: DecafParser.ExprContext): 
        if ctx.expr():
            #Rule 12 check that expr is type int for arith or rel op and check if exists in table before comparing
            if ctx.ADD() or ctx.MODULOS() or ctx.MINUS() or ctx.MULTIPLY() or ctx.DIVISION() or ctx.GREATER() or ctx.GREATEREQUALS or ctx.LESS() or ctx.LESSEQUALS():
                for index, _ in enumerate(ctx.expr()):
                    id = ctx.expr(index).getText()
                    check = self.st.lookup(id)
                    if check != 'int' and id.isdigit() != True and check == None: 
                        print("[Rule 12] error doesn't have type int for: (", id, ") on line:", ctx.start.line)                
            
            #Rule 14 check that logical and cond op have type boolean, if not None check symbol table else return error
            if ctx.NOT():
                temp = ctx.expr(0).getText().replace('!','')
                lookup = self.st.lookup(temp)
                if lookup == None or lookup.type != 'boolean':
                    print("[Rule 14] error type boolean not found for not expression, on line:", ctx.start.line)
            if ctx.ANDAND() or ctx.OROR():        
                temp = ctx.expr(0).getText().replace('!','')
                temp1 = ctx.expr(1).getText().replace('!','')
                lookup = self.st.lookup(temp)
                lookup1 = self.st.lookup(temp1)
                if lookup == None and lookup1 == None or lookup.type != 'boolean' and lookup1.type != 'boolean' :
                    print("[Rule 14] error type boolean not found for not expression, on line:", ctx.start.line)

            #Rule 13 check that eq op have type boolean or int, if not None check symbol table else return error
            if ctx.EQUALSEQUALS() or ctx.NOTEQUAL():         
                temp = ctx.expr(0).getText()
                temp1 = ctx.expr(1).getText()
                lookup = self.st.lookup(temp)
                lookup1 = self.st.lookup(temp1)
                if lookup != None and lookup1 != None and lookup.type != lookup1.type or lookup == None and lookup1 == None:
                    print("[Rule 13] error operands type do not match, on line:", ctx.start.line)
            
        # rule 2, No identifier is used before it is declared, check if it exists in the symbol table, this rule was provided by Ryan via screencast on moodle
        if ctx.location():
            id = ctx.location().ID().getText()
            if (self.st.lookup(id) == None):
                print("[Rule 2] error on line", ctx.start.line,'ID: (', id, ') not declared before use')     
        return self.visitChildren(ctx)
    
    def visitLocation(self, ctx:DecafParser.LocationContext):
        return self.visitChildren(ctx)      

    def visitAssign_op(self, ctx: DecafParser.Assign_opContext):
        return self.visitChildren(ctx)

    def visitCallout_arg(self, ctx: DecafParser.Callout_argContext):
        return self.visitChildren(ctx)

    def visitBin_op(self, ctx: DecafParser.Bin_opContext):             
        return self.visitChildren(ctx)

    def visitArith_op(self, ctx: DecafParser.Arith_opContext):        
        return self.visitChildren(ctx)

    def visitRel_op(self, ctx: DecafParser.Rel_opContext):
        return self.visitChildren(ctx)

    def visitEq_op(self, ctx: DecafParser.Eq_opContext):
        return self.visitChildren(ctx)

    def visitCond_op(self, ctx: DecafParser.Cond_opContext):
        return self.visitChildren(ctx)

    def visitLiteral(self, ctx: DecafParser.LiteralContext):
        return self.visitChildren(ctx)

    def visitInt_literal(self, ctx: DecafParser.Int_literalContext):
        return self.visitChildren(ctx)

    def visitBool_literal(self, ctx: DecafParser.Bool_literalContext):
        return self.visitChildren(ctx)

    def visitChar_literal(self, ctx: DecafParser.Char_literalContext):
        return self.visitChildren(ctx)

    def visitString_literal(self, ctx: DecafParser.String_literalContext):
        return self.visitChildren(ctx)


source = 'testdata/Program'
filein = open(source + '.dcf', 'r')
lexer = DecafLexer(ant.InputStream(filein.read()))

# create a token stream from the lexer
stream = ant.CommonTokenStream(lexer)

# create a new parser with the token stream as input
parser = DecafParser(stream)
tree = parser.program()

# create a new decaf visitor
codegen_visitor = DecafCodeGenVisitor()
codegen_visitor.visit(tree)

# output code
code = codegen_visitor.head + codegen_visitor.body
print(code)
