package minic;
import java.io.*;
import java.util.List;
import parser.sym;


public class ASTGenerator  {
    
    PrintWriter writer;
    
    public ASTGenerator(String filename){
        try{
            writer = new PrintWriter(filename);
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
    
    public void flush(){
        writer.flush();
        writer.close();
    }
    
    public void visit(Stmt.Empty e){
    }
        
    

    public boolean preVisit(Stmt.Compound i){
	return true;
    }
    public void postVisit(Stmt.Compound i){
    }

    
    public void visit(Program p,int tab){
	writer.print ( "(Program");
        List <Stmt> ls = p.getStmtList();
        for (Stmt s: ls ) s.accept(this,tab);
        writer.print ( "\n)");

    }

    public void visit(Simp s, int tab){
        int myTab = tab+1;
        String op = null;
        if (s.op == sym.ASSEGN) op = " = ";
        if (s.op == sym.PIUGUALE) op = " += ";
        if (s.op == sym.MENOUGUALE) op = " -= ";
        if (s.op == sym.PERUGUALE) op = " *= ";
        if (s.op == sym.DIVISOUGUALE) op = " /= ";
        if (s.op == sym.MODULOUGUALE) op = " %= ";        
        //writer.print("\n\t(" + op + "\n\t\t(" + s.ident + ")");
        writer.print("\n");
        printTab(myTab);
        writer.print("(" + op +"\n");
        printTab(myTab+1);
        writer.print("("+s.ident +")");
        s.exp.accept(this,myTab+1);
        writer.print("\n");
        printTab(myTab);
        writer.print(")");
        
    }
    
    public void visit(Exp.Binex i, int tab){
        int myTab = tab+1;
	String operator=null;
	if (i.op== sym.PIU)  operator = "+";
	if (i.op==sym.MENO) operator = "-";
	if (i.op==sym.PER)  operator = "*";
	if (i.op==sym.DIVISO)   operator = "/";
	if (i.op== sym.MODULO)   operator = "%";
        if (i.op== sym.MINORE)   operator = "<";
        if (i.op== sym.MINOREUGUALE)   operator = "<=";
        if (i.op== sym.MAGGIORE)   operator = ">";
        if (i.op== sym.MAGGIOREUGUALE)   operator = ">=";
        if (i.op== sym.UGUALE)   operator = "==";
        if (i.op== sym.DIVERSO)   operator = "!=";
        if (i.op== sym.AND)   operator = "&&";
        if (i.op== sym.OR)   operator = "||";
	writer.print( "(" + operator);
        
        writer.print("\n");
        printTab(myTab);

        i.e1.accept(this,myTab);
	i.e2.accept(this,myTab);
        
        writer.print("\n");
        printTab(tab);
        writer.print(")");

    }
    
    
        
    public void visit(Exp.Unex i, int tab){
        int myTab = tab+1;
        String operator=null;
	if (i.op== sym.NOT)  operator = "!";
	if (i.op==sym.MENO) operator = "-";
	writer.print( "(" + operator);
        i.e1.accept(this,myTab);
        writer.print("\n");
        printTab(tab);
        writer.print(")");

    }
    
    public void visit(Exp.Identifier d){
	writer.print( "(" + d.i + ")");
    }
    
    public void visit(Exp.IntConst d){
	writer.print( "(" + d.i + ")");
    }
    
    
    public void visit (Control.IfThenElse i, int tab){
        int myTab = tab+1;
        writer.print("\n");
        printTab(myTab);
        writer.print("(if");
        writer.print("\n");
        printTab(myTab+1);
        i.cond.accept(this,myTab+1);  // cond è expr
        i.then.accept(this,myTab);  // i è Block
        
        if (i.els!=null ) {   // else è block
            writer.print("\n");
            printTab(myTab+1);
            writer.print( "(else");
            i.els.accept(this,myTab+1);
            writer.print("\n");
            printTab(myTab+1);
            writer.print(")");
        }
        writer.print("\n");
        printTab(myTab);
        writer.print(") ");

    
    }
    
    public void visit(Control.Loop l,int tab){
        int myTab = tab+1;
        writer.print("\n");
        printTab(myTab);
	writer.print("(while ");
        writer.print("\n");
        printTab(myTab+1);
	
	l.cond.accept(this,myTab+1); // cond Expr
	l.body.accept(this,myTab); // body Block
        writer.print("\n");
        printTab(myTab);
        writer.print(")");
	
    }
    



    
    private void printTab(int tab){
        for (int i = 0; i < tab; i++) writer.print("\t");
    }
   

}
