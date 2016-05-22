/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minic;


public class Simp extends Stmt{
    
    String ident;
    Integer op;
    Exp exp;

    public Simp(String ident, Integer op, Exp exp) {
        this.ident = ident;
        this.op = op;
        this.exp = exp;
    }
    
    @Override
	public void accept(ASTGenerator v, int tab){
            v.visit(this,tab);
	}
    
    
   
}
