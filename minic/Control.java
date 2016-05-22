/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minic;


public abstract class Control extends Stmt{
    
        public static class Loop extends Control{
	public Block body;
	public Exp cond;
        
	public Loop(Block b, Exp e){
	    cond = e;
	    body= b;
	}

        @Override
	public void accept(ASTGenerator v,int tab){

	    v.visit(this,tab);
	}
        
    }
        
    public static class IfThenElse extends Control {
	public Block then;
	public Block els;
	public Exp cond;
	public IfThenElse(Block t, Block e, Exp c ){
	    cond = c;
	    then = t;
	    els  = e;
	}

        @Override
	public void accept(ASTGenerator v,int tab){
	    
         
            v.visit(this,tab);
	}
    }
    
    

    public static Control whileloop(Block b, Exp e){
	return new Loop(b,e);
    }
    
    
    public static  Control ifthenelse(Block b, Block e, Exp c ){
	return new IfThenElse(b,e,c);
    }
    public static  Control ifthen(Block b, Exp c ){
	return new IfThenElse(b,null,c);
    }
    
    
    
}
