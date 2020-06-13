grammar Decaf;

/* needs to be present to the source code to be valid for codegen */
CLASS : 'class'; 
CALLOUT : 'callout';
PROGRAM : 'Program'; // this can be used for a valid program or ID can be used instead

/* all bracket types found that are valid to be in the code for a valid program */ 

LCURLY : '{';
RCURLY : '}';
LSQUARE : '[';
RSQUARE : ']';
LBRACK : '(';
RBRACK : ')';

/* valid code synatx punctuation */
ENDCOLON : ';';
COMMA : ',';
ENDPERIOD : '.';
DOUBLEQUOTES : '"';
QUOTE : '\'';
SLASH : '\\';
QUESTIONMARK : '?';
COLON : ':';

/* valid data types and their values that are valid */
VOID : 'void';

BOOL : 'boolean';
INT : 'int';
HEX : '0x';

TRUE : 'true';
FALSE : 'false';


IF : 'if';
ELSE : 'else';

FOR : 'for';
BREAK : 'break';

CONTINUE : 'continue';
RETURN : 'return';

/* Binary operations that are supported as valid */
EQUAL : '=';
NOT : '!';

MINUS : '-';
ADD : '+';
MULTIPLY : '*';
DIVISION : '/';
MODULOS : '%';

EQUALSEQUALS : '==';
NOTEQUAL : '!=';
ANDAND : '&&';
OROR : '||';

PLUSEQUAL : '+=';
MINUSEQUAL : '-=';

/* relational operations supported as valid */
LESS : '<';
GREATER : '>';
LESSEQUALS : '<=';
GREATEREQUALS : '>=';

/* skips tabbed, return and new line syntax within the code */
WHITESPACE : [ \t\r\f\n]+ -> skip;

/* handle invalid code that are not supported tokens */

/* skips all comments within the code as they are not needed via the compiler to generate code or check it semantically,
it is only used to aid a developer understand the code */
CODECOMMENTS: '//' ~('\r' | '\n')*  -> channel(HIDDEN);

CHAR : '\'' (~['\r\t\n\\] | '\\' ['\\]) '\'';

fragment ALPHA : [a-zA-Z_];
fragment DIGIT : [0-9];
fragment ALPHANUM : ALPHA | DIGIT;

ID : ALPHA ALPHANUM*;

HEXCHAR : [a-fA-F];

HEXDIGIT : DIGIT HEXCHAR;

DECIMAL_LITERAL: DIGIT DIGIT*;

HEX_LITERAL : HEX HEXDIGIT HEXDIGIT*;

program : CLASS PROGRAM LCURLY field_decl* method_decl* RCURLY EOF; 

field_name : ID | ID LSQUARE int_literal RSQUARE;

field_decl : data_type field_name (COMMA field_name)* ENDCOLON; 

method_decl : ( data_type | VOID ) ID LBRACK (( data_type ID (COMMA data_type ID)* )?) RBRACK block; 

var_decl : data_type ID (COMMA ID)* ENDCOLON;

block : LCURLY var_decl* statement* RCURLY;

data_type : INT | BOOL; 

statement: location assign_op expr ENDCOLON 
| method_call ENDCOLON 
| IF LBRACK expr RBRACK block ( ELSE block )?
| FOR ID EQUAL expr COMMA expr block 
| RETURN ( expr )? ENDCOLON
| BREAK ENDCOLON
| CONTINUE ENDCOLON
| block;

assign_op : EQUAL | PLUSEQUAL | MINUSEQUAL;

method_name : ID;

method_call : method_name LBRACK (expr (COMMA expr )* )? RBRACK | CALLOUT LBRACK string_literal (COMMA callout_arg)* RBRACK;

location : ID | ID LSQUARE expr RSQUARE;

/* Operator precedence, from highest to lowest,
in this order is the valid standard per the Decaf specification */ 

expr : location 
| method_call 
| literal 
| (MINUS) expr
| (NOT) expr
| expr (MULTIPLY | DIVISION | MODULOS) expr
| expr (ADD | MINUS) expr
| expr (LESS | LESSEQUALS | GREATEREQUALS | GREATER) expr
| expr (EQUALSEQUALS | NOTEQUAL) expr
| expr (ANDAND) expr
| expr (OROR) expr
| LBRACK expr RBRACK;

string_literal : '"' CHAR+ '"';
callout_arg : expr | string_literal;

bin_op : arith_op | rel_op | eq_op | cond_op;
arith_op : ADD | MINUS | MULTIPLY | DIVISION | MODULOS;
rel_op : LESS | LESSEQUALS | GREATEREQUALS | GREATER;
eq_op : EQUALSEQUALS | NOTEQUAL;
cond_op : ANDAND | OROR;

literal : int_literal | char_literal | bool_literal;
int_literal : DECIMAL_LITERAL | HEX_LITERAL;
bool_literal : TRUE | FALSE;
char_literal : CHAR;
