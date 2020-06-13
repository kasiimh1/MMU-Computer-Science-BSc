# Generated from Decaf.g4 by ANTLR 4.7.2
from antlr4 import *
if __name__ is not None and "." in __name__:
    from .DecafParser import DecafParser
else:
    from DecafParser import DecafParser

# This class defines a complete listener for a parse tree produced by DecafParser.
class DecafListener(ParseTreeListener):

    # Enter a parse tree produced by DecafParser#program.
    def enterProgram(self, ctx:DecafParser.ProgramContext):
        pass

    # Exit a parse tree produced by DecafParser#program.
    def exitProgram(self, ctx:DecafParser.ProgramContext):
        pass


    # Enter a parse tree produced by DecafParser#field_name.
    def enterField_name(self, ctx:DecafParser.Field_nameContext):
        pass

    # Exit a parse tree produced by DecafParser#field_name.
    def exitField_name(self, ctx:DecafParser.Field_nameContext):
        pass


    # Enter a parse tree produced by DecafParser#field_decl.
    def enterField_decl(self, ctx:DecafParser.Field_declContext):
        pass

    # Exit a parse tree produced by DecafParser#field_decl.
    def exitField_decl(self, ctx:DecafParser.Field_declContext):
        pass


    # Enter a parse tree produced by DecafParser#method_decl.
    def enterMethod_decl(self, ctx:DecafParser.Method_declContext):
        pass

    # Exit a parse tree produced by DecafParser#method_decl.
    def exitMethod_decl(self, ctx:DecafParser.Method_declContext):
        pass


    # Enter a parse tree produced by DecafParser#var_decl.
    def enterVar_decl(self, ctx:DecafParser.Var_declContext):
        pass

    # Exit a parse tree produced by DecafParser#var_decl.
    def exitVar_decl(self, ctx:DecafParser.Var_declContext):
        pass


    # Enter a parse tree produced by DecafParser#block.
    def enterBlock(self, ctx:DecafParser.BlockContext):
        pass

    # Exit a parse tree produced by DecafParser#block.
    def exitBlock(self, ctx:DecafParser.BlockContext):
        pass


    # Enter a parse tree produced by DecafParser#data_type.
    def enterData_type(self, ctx:DecafParser.Data_typeContext):
        pass

    # Exit a parse tree produced by DecafParser#data_type.
    def exitData_type(self, ctx:DecafParser.Data_typeContext):
        pass


    # Enter a parse tree produced by DecafParser#statement.
    def enterStatement(self, ctx:DecafParser.StatementContext):
        pass

    # Exit a parse tree produced by DecafParser#statement.
    def exitStatement(self, ctx:DecafParser.StatementContext):
        pass


    # Enter a parse tree produced by DecafParser#assign_op.
    def enterAssign_op(self, ctx:DecafParser.Assign_opContext):
        pass

    # Exit a parse tree produced by DecafParser#assign_op.
    def exitAssign_op(self, ctx:DecafParser.Assign_opContext):
        pass


    # Enter a parse tree produced by DecafParser#method_name.
    def enterMethod_name(self, ctx:DecafParser.Method_nameContext):
        pass

    # Exit a parse tree produced by DecafParser#method_name.
    def exitMethod_name(self, ctx:DecafParser.Method_nameContext):
        pass


    # Enter a parse tree produced by DecafParser#method_call.
    def enterMethod_call(self, ctx:DecafParser.Method_callContext):
        pass

    # Exit a parse tree produced by DecafParser#method_call.
    def exitMethod_call(self, ctx:DecafParser.Method_callContext):
        pass


    # Enter a parse tree produced by DecafParser#location.
    def enterLocation(self, ctx:DecafParser.LocationContext):
        pass

    # Exit a parse tree produced by DecafParser#location.
    def exitLocation(self, ctx:DecafParser.LocationContext):
        pass


    # Enter a parse tree produced by DecafParser#expr.
    def enterExpr(self, ctx:DecafParser.ExprContext):
        pass

    # Exit a parse tree produced by DecafParser#expr.
    def exitExpr(self, ctx:DecafParser.ExprContext):
        pass


    # Enter a parse tree produced by DecafParser#string_literal.
    def enterString_literal(self, ctx:DecafParser.String_literalContext):
        pass

    # Exit a parse tree produced by DecafParser#string_literal.
    def exitString_literal(self, ctx:DecafParser.String_literalContext):
        pass


    # Enter a parse tree produced by DecafParser#callout_arg.
    def enterCallout_arg(self, ctx:DecafParser.Callout_argContext):
        pass

    # Exit a parse tree produced by DecafParser#callout_arg.
    def exitCallout_arg(self, ctx:DecafParser.Callout_argContext):
        pass


    # Enter a parse tree produced by DecafParser#bin_op.
    def enterBin_op(self, ctx:DecafParser.Bin_opContext):
        pass

    # Exit a parse tree produced by DecafParser#bin_op.
    def exitBin_op(self, ctx:DecafParser.Bin_opContext):
        pass


    # Enter a parse tree produced by DecafParser#arith_op.
    def enterArith_op(self, ctx:DecafParser.Arith_opContext):
        pass

    # Exit a parse tree produced by DecafParser#arith_op.
    def exitArith_op(self, ctx:DecafParser.Arith_opContext):
        pass


    # Enter a parse tree produced by DecafParser#rel_op.
    def enterRel_op(self, ctx:DecafParser.Rel_opContext):
        pass

    # Exit a parse tree produced by DecafParser#rel_op.
    def exitRel_op(self, ctx:DecafParser.Rel_opContext):
        pass


    # Enter a parse tree produced by DecafParser#eq_op.
    def enterEq_op(self, ctx:DecafParser.Eq_opContext):
        pass

    # Exit a parse tree produced by DecafParser#eq_op.
    def exitEq_op(self, ctx:DecafParser.Eq_opContext):
        pass


    # Enter a parse tree produced by DecafParser#cond_op.
    def enterCond_op(self, ctx:DecafParser.Cond_opContext):
        pass

    # Exit a parse tree produced by DecafParser#cond_op.
    def exitCond_op(self, ctx:DecafParser.Cond_opContext):
        pass


    # Enter a parse tree produced by DecafParser#literal.
    def enterLiteral(self, ctx:DecafParser.LiteralContext):
        pass

    # Exit a parse tree produced by DecafParser#literal.
    def exitLiteral(self, ctx:DecafParser.LiteralContext):
        pass


    # Enter a parse tree produced by DecafParser#int_literal.
    def enterInt_literal(self, ctx:DecafParser.Int_literalContext):
        pass

    # Exit a parse tree produced by DecafParser#int_literal.
    def exitInt_literal(self, ctx:DecafParser.Int_literalContext):
        pass


    # Enter a parse tree produced by DecafParser#bool_literal.
    def enterBool_literal(self, ctx:DecafParser.Bool_literalContext):
        pass

    # Exit a parse tree produced by DecafParser#bool_literal.
    def exitBool_literal(self, ctx:DecafParser.Bool_literalContext):
        pass


    # Enter a parse tree produced by DecafParser#char_literal.
    def enterChar_literal(self, ctx:DecafParser.Char_literalContext):
        pass

    # Exit a parse tree produced by DecafParser#char_literal.
    def exitChar_literal(self, ctx:DecafParser.Char_literalContext):
        pass


