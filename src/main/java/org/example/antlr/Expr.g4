grammar Expr;

expr   : expr ('*' | '/') expr   # MulDiv
       | expr ('+' | '-') expr   # AddSub
       | INT                     # Int
       | '(' expr ')'            # Parens
       ;

INT    : [0-9]+;
WS     : [ \t\r\n]+ -> skip;