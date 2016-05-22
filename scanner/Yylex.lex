package scanner;
import parser.sym;
import java_cup.runtime.Symbol;

%%
%cup
%public
%line

%{
    public static int getLine() { return yyline; }
%}

LITERAL = [a-zA-Z]
NUM = [0-9]
NUM_LITERAL = [0-9A-Za-z]


%%
"/*"(.|[ \t\r\n\f])*"*/" {}
"//"(.)*$ {/* elimina commenti su una linea */}



"{" { return new Symbol(sym.PGRAFFAPERTA); }

"}" { return new Symbol(sym.PGRAFFACHIUSA); }

"(" { return new Symbol(sym.PTONDAPERTA); }

")" { return new Symbol(sym.PTONDACHIUSA); }

";" { return new Symbol(sym.PVIRGOLA); }

"if" { return new Symbol(sym.IF); }

"else" { return new Symbol(sym.ELSE); }

"while" { return new Symbol(sym.WHILE); }

"=" { return new Symbol(sym.ASSEGN); }

"+=" { return new Symbol(sym.PIUGUALE); }

"-=" { return new Symbol(sym.MENOUGUALE); }

"*=" { return new Symbol(sym.PERUGUALE); }

"/=" { return new Symbol(sym.DIVISOUGUALE); }

"%=" { return new Symbol(sym.MODULOUGUALE); }

"+" { return new Symbol(sym.PIU); }

"-" { return new Symbol(sym.MENO); }

"*" { return new Symbol(sym.PER ); }

"/" { return new Symbol(sym.DIVISO ); }

"%" { return new Symbol(sym.MODULO); }

"<" { return new Symbol(sym.MINORE); }

"<=" { return new Symbol(sym.MINOREUGUALE); }

">" { return new Symbol(sym.MAGGIORE ); }

">=" { return new Symbol(sym.MAGGIOREUGUALE ); }

"==" { return new Symbol(sym.UGUALE); }

"!=" { return new Symbol(sym.DIVERSO); }

"&&" { return new Symbol(sym.AND ); }

"||" { return new Symbol(sym.OR ); }

"!" { return new Symbol(sym.NOT ); }


{LITERAL}{NUM_LITERAL}* { return new Symbol(sym.IDENT, new String(yytext())); }
{NUM}* { return new Symbol(sym.INTCONST, new Integer(yytext())); }

[ \t\r\n\f] { /* ignora spazi bianchi. */ }



. { System.err.println("Illegal character: "+ yytext() + " at line: " + yyline); }