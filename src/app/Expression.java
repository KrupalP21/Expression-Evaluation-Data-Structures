package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {
	public static String delims = " \t*+-/()[]";
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void 
    makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 **/
    	for (int i = 0; i < expr.length(); i++) {
			if (Character.isLetter(expr.charAt(i))) {
				String temp = "";
				while (i < expr.length() && Character.isLetter(expr.charAt(i))) {
					temp = temp + expr.charAt(i);
					i++;
				}
				if (i < expr.length() && expr.charAt(i) == '[') {
					Array tempArray = new Array(temp);
					if (!(arrays.contains(tempArray))) {
						arrays.add(tempArray);
					}
				}
				else {
					Variable tempVar = new Variable(temp);
					if (!(vars.contains(tempVar))) {
						vars.add(tempVar);
					}
				}
			}	
		}
	}
    	/*
    	 * Case 1: If current token is not a [ and next token is a [ then store 
    	 *         in ArrayList for arrays
    	 * Case 2: If current token is not a [ and next token is not a [ then
    	 * 		   store in ArrayList for variables
    	 * Case 3: If inner loop does not detect a "next Token" then store the token 
    	 * 		   in the vars 	
    	 */
    	
    	/*
    	
    	expr = removeSpace(expr);
    	StringTokenizer token = new StringTokenizer(expr,delims,true);
    	while (token.hasMoreTokens() == true) {
    		String currentToken = token.nextToken(); // For Case 1 use this as the "backdrop" in case here is a bracket in the next Token
    		
    		if (token.hasMoreTokens() == true) {
    			String nextToken = token.nextToken(); // utilize this to check for bracket in the next next token
    			
    			if (nextToken.equals("[")) {
    				Array tempArray = new Array(currentToken); // not next token because that would store the bracket
    				if (arrays.contains(tempArray)) {
    					continue;
    				} else {
    				arrays.add(tempArray);
    				}
    			}
    		} else {
    			Variable tempVariable1 = new Variable(currentToken);
    			if (vars.contains(tempVariable1)) {
    				continue;
    			} else {
    			vars.add(tempVariable1);
    		}
    		}
    	
    		
    }	
    	}
    */
    	
    		
    		
    		/*
    		 * while(hasMoreTokens)
token = next Token
if (hasMoreTokens)
delim = next Token
if (delim == '[')

    		 */
    	
    
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void 
    loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
 // PRIVATE METHOD TO CHECK IF THE EXPRESSION OR PART OF EXPRESSION IS A FLOAT
    private static boolean checkFloat(String temp) {
    	try {
    		Float.parseFloat(temp);
    		return true;
    	} catch (Exception e) {
    		return false;
    	}
    
    }
    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	// following line just a placeholder for compilation
    	
    	expr = expr.replaceAll(" ", "");
    	expr = removeVars(expr, vars);
    	// IF EXPRESSION IS NULL OR 0
    	if (expr.length() == 0 || expr == null ) {
    		return 0;
}
// IF EXPRESSION IS SIMPLY A NUMBER
    	if (checkFloat(expr) == true) {
    		float result = Float.parseFloat(expr);
    		 return result;
    	}
    	expr = removeVars(expr, vars);
// NO PARENTHESES OR BRACKETS OR MULTIPLY OR DIVISION
	    /*	
    	if (!expr.contains("[") || !expr.contains("(") || !expr.contains("*") || !expr.contains("/")) {
	    		Stack <Float> variables = new Stack <Float> ();
	    		Stack <String> operators = new Stack <String> ();
	    		float total = 0;
	    		for (int i = 0; i < expr.length(); i++) {
	    			if (expr.charAt(i) == '+' || expr.charAt(i) == '-') {
	    				operators.push(expr.substring(i,i+1));
	    			} else   {
	    				
	    				variables.push(Float.parseFloat(expr.substring(i,i+1)));
	    			}
	    		
	    		}
	    			total = doMath(operators.pop(), variables.pop(), variables.pop());
	    			while (!operators.isEmpty()) {
	    				total = doMath(operators.pop(), variables.pop(), total);
	    				variables.push(total);
	    			}
	    			variables.push(total);
	    		return variables.peek();
	    	
	    	}
    	*/       	
        	if (!expr.contains("[") && !expr.contains("(") && !expr.contains("*") && !expr.contains("/")) {
        		//expr = expr.replaceAll("", " ");
        		StringTokenizer token1 = new StringTokenizer(expr,"+-",true);
        		Stack <Float> variables = new Stack <Float> ();
        		Stack <String> operators = new Stack <String> ();
        		float total = 0;
        		while (token1.hasMoreTokens() == true) {
        			String current = token1.nextToken();
        			if (current.equals("+") || current.equals("-")) {
        				operators.push(current);
        			} else  {
        				variables.push(Float.parseFloat(current));
        			}
        		}
        		total = doMath(operators.pop(), variables.pop(), variables.pop());
        			while (!operators.isEmpty()) {
        				total = doMath(operators.pop(), total, variables.pop());
        			}
        		return total;
    	}
        	/*
    	if (!expr.contains("[") || !expr.contains("(") || !expr.contains("+") || !expr.contains("-")) {
    		Stack <Float> variables = new Stack <Float> ();
    		Stack <Character> operators = new Stack <Character> ();
    		float total = 0;
    		for (int i = 0; i < expr.length(); i++) {
    			if (expr.charAt(i) == '*' || expr.charAt(i) == '/') {
    				operators.push(expr.charAt(i));
    			} else  {
    				variables.push(Float.parseFloat(expr.substring(i, i+1)));
    			}
    		
    		}
    			//total = doMath(operators.pop(), variables.pop(), variables.pop());
    			while (!operators.isEmpty()) {
    				total = doMath(operators.pop(), variables.pop(), variables.pop());
    				variables.push(total);	
    			}
    		return variables.peek();
    	
    	}*/
        		// IF NO PARENTHESES, BRACKETS, ADD, SUBTRACT
        		if (!expr.contains("[") && !expr.contains("(") && !expr.contains("+") && !expr.contains("-")) {
            		//expr = expr.replaceAll("", " ");
            		StringTokenizer token1 = new StringTokenizer(expr,"*/",true);
            		Stack <Float> variables = new Stack <Float> ();
            		Stack <String> operators = new Stack <String> ();
            		float total = 0;
            		while (token1.hasMoreTokens() == true) {
            			String current = token1.nextToken();
            			if (current.equals("*") || current.equals("/")) {
            				operators.push(current);
            			} else  {
            				variables.push(Float.parseFloat(current));
            			}
            		}
            		total = doMath(operators.pop(), variables.pop(), variables.pop());
            			while (!operators.isEmpty()) {
            				total = doMath(operators.pop(), total, variables.pop());
            			}
            		return total;
        	}
        	// FOR SIMPLE PEMDAS INVOLVING PAREN
        		if (expr.contains("(")) {
        			int i = 0;
        			int parenCounter = 0;
        			for (int k = 0; k < expr.length(); k++) {
        				if (expr.charAt(k) == '(') {
        					i = i + 1;
        					String s = "";
        					parenCounter = parenCounter + 1;
        					while (parenCounter >= 1) {
        						if (expr.charAt(i) == '(')
        							parenCounter = parenCounter + 1;
        						if (expr.charAt(i) == ')')
        							parenCounter = parenCounter - 1;
        						if (parenCounter != 0) {
        							s = s + expr.charAt(i);
        							i++;
        						}
        					}

        					// Return the contents of the parens
        					String temp = "";
        					if (parenCounter > 1) {
        						temp = s;
        						String s1 = "" + evaluate(temp, vars, arrays);
        						expr = expr.replace("(" + temp + ")", s1);
        						return evaluate(expr, vars, arrays);
        					} else {
        						temp = s;
        						String s1 = "" + evaluate(temp, vars, arrays);
        						expr = expr.replace("(" + temp + ")", s1);
        						return evaluate(expr, vars, arrays);
        					}

        				}

        			}
        		}

        		
        		if (!expr.contains("[") && !expr.contains("(")) {
        			StringTokenizer token1 = new StringTokenizer(expr,"+-/*",true);
            		Stack <Float> variables = new Stack <Float> ();
            		Stack <String> operators = new Stack <String> ();
            		float total = 0;
            		while (token1.hasMoreTokens() == true) {
            			String current = token1.nextToken();
            			if (current.equals("+") || current.equals("-") || current.equals("*") || current.equals("/")) {
            				operators.push(current);
            			} else  {
            				variables.push(Float.parseFloat(current));
            			}	
            		}
            		StringTokenizer token2 = new StringTokenizer(expr,"+-/*",true);
            		while (token2.hasMoreTokens() == true) {
            			String current = token2.nextToken();
            		while (!operators.isEmpty() && hasPrecedence(current, operators.peek())) {
    					variables.push(doMath(operators.pop(), variables.pop(), variables.pop()));	
            		}
            		}
            		while (!operators.isEmpty()) {
            			variables.push(doMath(operators.pop(),variables.pop(),variables.pop()));
            		}
            		return variables.pop();
        		}
        		
            	/*	
        		if(!operators.isEmpty() && operators.size() + 1 == variables.size()){
        				if(operators.peek().equals("*")){
        					float tempNum2 = variables.pop();
        					float tempNum1 = variables.pop();
        					//pop operator
        					operators.pop();
        					//push the product of the tempNumbers on the stack
        					variables.push(tempNum1 * tempNum2);
        				}
        				//peek operator stack. if /, solve immediately to follow order of operations
        				else if(operators.peek().equals("/")){
        					float tempNum2 = variables.pop();
        					float tempNum1 = variables.pop();
        					//pop operator
        					operators.pop();
        					//push the quotient of the tempNumbers on the stack
        					variables.push(tempNum1 / tempNum2);
        				}
        			}
        			//end of while loop
        		
        		//run until there are no operators and one operand
        		while(!operators.isEmpty() && variables.size() != 1){
        			String operator = operators.pop();
        			//if the last operator is addition, add. push sum
        			if(operator.equals("+")){
        				float tempNum2 = variables.pop();
        				float tempNum1 = variables.pop();
        				variables.push(tempNum1 + tempNum2);
        			}
        			//if the last operator is subtraction, subtract. push difference
        			if(operator.equals("-")){
        				float tempNum2 = variables.pop();
        				float tempNum1 = variables.pop();
        				variables.push(tempNum1 - tempNum2);
        			}
        		}
        		return variables.pop();
        	}
           */ 		
        		
        		
        		// MISSING ARRAY IMPLEMENTATION
return 69;    	
    }    
   /*
    private static String noSpaces(String string) {

		String temp = "";
		for (int i = 0; i < expr.length(); i++) {

			// Checks if character is a space
			if (expr.charAt(i) != ' ') {

				res = res + expr.charAt(i);
			}
		}
		return res;
	}
	*/

private static boolean hasPrecedence(String current, String string) {
    if (string.equals("(") || string.equals(")")) {
        return false; }
    if ((current.equals("*") || current.equals("/")) && (string.equals("+") || string.equals("-"))) {
        return false;
    } else {
        return true;
    }
    }

    private static String removeVars(String expr, ArrayList<Variable> vars) {
		for (int i = 0; i < vars.size(); i++) {
			expr = expr.replaceAll(vars.get(i).name, "" + vars.get(i).value);
		}
		return expr;
    }
    private static float doMath(String string, float a, float b) {

    	if (string.equals("+")) {
    		return  b + a;
    	} else if (string.equals("-")) {
    		return a - b;
    	} else if (string.equals("*")) {
    		return a * b;
    	} else { 
    		return b / a;
    	} 
    }
	}
    
