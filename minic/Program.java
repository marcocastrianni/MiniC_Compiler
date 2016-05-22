/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minic;
import java.util.List;


public class Program extends Stmt.Compound {
 
    public Program(List<Stmt> ls){
	super(ls);
    }

    @Override
    public void accept(ASTGenerator v,int tab){

        v.visit(this,tab);
    }

    public List<Stmt> getStmtList() {
       return ls;
    }
}
