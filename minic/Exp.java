/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minic;

import parser.sym;


public abstract class Exp {
    
    public Exp(){
    }

    public static class Binex extends Exp {
	public Exp e1, e2;
	public int op;
	public Binex(Exp e1, int op, Exp e2){
	    this.e1=e1;
	    this.e2=e2;
	    this.op=op;
	}
	public String toString(){
	    String operator=null;
	    if (op== sym.PIU)  operator = "+";
	    if (op==sym.MENO) operator = "-";
	    if (op==sym.PER)  operator = "*";
	    if (op==sym.DIVISO)   operator = "/";
	    if (op==sym.MODULO)   operator = "\\%";
	    return e1 + ""+operator + e2;
	}
        @Override
	public void accept(ASTGenerator v,int tab){
            v.visit(this,tab);
	}

    }
    public static Binex binop(Exp e1, int op, Exp e2){
	return new Binex(e1,op,e2);
    }
    public static class Unex extends Exp {
        public Integer op;
	public Exp e1;
	public Unex(Integer op,Exp e1){
            this.op = op;
	    this.e1=e1;
	}

        @Override
	public void accept(ASTGenerator v,int tab){

	    v.visit(this,tab);
	}
    }
    public static Unex unop(Integer op,Exp e){
	return new Unex(op,e);
    }
    public static class IntConst extends Exp {
	public int i;
	public IntConst(int i){
	    this.i=i;
	}

        @Override
	public void accept(ASTGenerator v,int tab){
	    v.visit(this);
	}
    }
    public static IntConst intconst(int i){
	return new IntConst(i);
    }
    public static class Identifier extends Exp {

	public String i;
	public Identifier(String i){
	    this.i=i;

	}

        @Override
	public void accept(ASTGenerator v,int tab){
	    v.visit(this);
	}
    }
    public static Identifier ident(String s){
	return new Identifier(s);
    }

    public abstract void accept(ASTGenerator v,int tab);

}
