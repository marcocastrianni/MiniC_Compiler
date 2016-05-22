/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minic;
import java.util.List;

public abstract class Stmt extends Block{
    
     
    public static class Compound extends Stmt {
	public List<Stmt> ls;
	public Compound(List<Stmt> l){
	    ls = l;
	}

        @Override
	public void accept(ASTGenerator v,int tab){
	    if (!v.preVisit(this)) return;
	    for (Stmt s: ls) s.accept(v,tab);
	    v.postVisit(this);
	}
    }
    
    
    public static Stmt compound(List<Stmt> l){
	return new Compound(l);
    }
    
    public static class Empty extends Stmt {
	public Empty(){
	}
        @Override
	public void accept(ASTGenerator v,int tab){
	    v.visit(this);
	}
    }
    public static Stmt empty(){
	return new Empty();
    }
    @Override
    public abstract void accept(ASTGenerator v, int tab);

    
}
