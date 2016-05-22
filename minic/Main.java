/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package minic;
import java.io.*;
import parser.parser;
import scanner.Yylex;



public class Main {

 /**
   * Runs the parser on an input file.
   */
  public static void main(String args[]) 
    throws java.io.IOException, java.lang.Exception {
      
    String file = null;
    if(args.length !=0) file = args[0];    
    else file = "input.txt";
     
    try {
     
        Yylex lexer = null;
        FileReader f;
        f=new FileReader(file);
        lexer = new Yylex(f);
        parser p = new parser(lexer);
        Program result = (Program)p.parse().value;
        if(result!= null && p.getNumErrors()== 0){
            ASTGenerator syntax = new ASTGenerator("output.txt");
            result.accept(syntax,0);
            syntax.flush();
            System.out.println("Successfully compiled!\n Abstract syntax tree has been generated");
        }
      else  System.err.println("Syntax errors in input file, abstract syntax has not been generated");
      
     
      
    }
    catch (java.io.IOException e) {
      System.err.println("An I/O error occured while parsing : \n"+e);
      System.exit(1);
    }
    
  }
}




