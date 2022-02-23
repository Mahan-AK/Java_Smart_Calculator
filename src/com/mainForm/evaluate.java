package com.mainForm;
//import com.mainForm.stack;

import java.util.ArrayList;
import java.util.List;

class evaluate {
    static double[] vars = new double[3];

    static void toPost(String inf) 
        {
            stack op = new stack(inf.length()); 
            char[] chars = inf.toCharArray();
            char[] prec = {'(', '+', '-', '*', '/', '^'};
            
            for (int index = 0; index<inf.length(); index++) { 
                if (Character.isLetterOrDigit(chars[index])) 
                    System.out.print(chars[index] + " ");
                else if (chars[index] == '(') 
                    op.push(Character.toString(chars[index])); 
                else if (op.isEmpty())
                    op.push(Character.toString(chars[index]));
                else if (chars[index] == ')') { 
                    while (!op.isEmpty() && !op.top().equals("(")) 
                        System.out.print(op.pop() + " ");
                        
                    op.pop();
                } else {
                    while (!op.isEmpty() && search(prec, chars[index]) <= search(prec, op.top()))
                        System.out.print(op.pop() + " ");
                
                    op.push(Character.toString(chars[index]));
                }
            } 

            while (!op.isEmpty()){ 
                System.out.print(op.pop() + " ");
            } 
            System.out.println("");
        }

    public static int search (char[] arr, char val) {
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == val) return i;
        }
        return -1;
    }

    public static int search (char[] arr, String val) {
        char val_char = val.toCharArray()[0];
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == val_char) return i;
        }
        return -1;
    }

    public static String eval(String str) {
        char[] chars = str.toCharArray();
        char[] prec = {'+', '-', '×', '÷', '^'};
        stack operators = new stack(str.length());
        stack operands = new stack(str.length());

        int index = 0;
        while (index < chars.length) {
    
            if (Character.isDigit(chars[index])) {
                String val = String.valueOf(chars[index]);
                while (index < chars.length - 1 && (Character.isDigit(chars[index + 1]) || chars[index + 1] == '.'  || chars[index + 1] == 'E' || (chars[index] == 'E' && chars[index + 1] == '-'))) {
                    val += String.valueOf(chars[index+1]);
                    index++;
                }
                operands.push(val);
            } else if (operators.isEmpty()) {
                operators.push(Character.toString(chars[index]));
            } else if (chars[index] == '(') {
                operators.push("(");
            } else if (chars[index] == ')') {
                while (!operators.top().equals("(")) {

                    String op = operators.pop();
                    double a = Double.parseDouble(operands.pop());
                    double b = Double.parseDouble(operands.pop());

                    switch (op) {
                        case "+":
                            operands.push(Double.toString(b + a));
                            break;
                        case "-":
                            operands.push(Double.toString(b - a));
                            break;
                        case "×":
                            operands.push(Double.toString(b * a));
                            break;
                        case "÷":
                            operands.push(Double.toString(b / a));
                            break;
                        case "^":
                            operands.push(Double.toString(Math.pow(b, a)));
                            break;
                    }
                    
                }
                operators.pop();
            } else if (search(prec, chars[index]) < search(prec, operators.top())) {
                while (!operators.isEmpty() && search(prec, chars[index]) < search(prec, operators.top())) {
                    String op = operators.pop();
                    double a = Double.parseDouble(operands.pop());
                    double b = Double.parseDouble(operands.pop());

                    switch (op) {
                        case "+":
                            operands.push(Double.toString(b + a));
                            break;
                        case "-":
                            operands.push(Double.toString(b - a));
                            break;
                        case "×":
                            operands.push(Double.toString(b * a));
                            break;
                        case "÷":
                            operands.push(Double.toString(b / a));
                            break;
                        case "^":
                            operands.push(Double.toString(Math.pow(b, a)));
                            break;
                    }

                    
                }
                operators.push(Character.toString(chars[index]));
            } else {
                operators.push(Character.toString(chars[index]));
            }
            index++;
        }

        while (!operators.isEmpty()) {
            String op = operators.pop();
            double a = Double.parseDouble(operands.pop());
            double b = Double.parseDouble(operands.pop());

            switch (op) {
                case "+":
                    operands.push(Double.toString(b + a));
                    break;
                case "-":
                    operands.push(Double.toString(b - a));
                    break;
                case "×":
                    operands.push(Double.toString(b * a));
                    break;
                case "÷":
                    operands.push(Double.toString(b / a));
                    break;
                case "^":
                    operands.push(Double.toString(Math.pow(b, a)));
                    break;
            }

        }

        double result = Double.parseDouble(operands.pop());
        if (result == Math.round(result))
            return Long.toString(Math.round(result));

        return Double.toString(result);
    }

    static int findClose(char[] lst, int from) {
        int c = 0;
        for (int i = from; i < lst.length; i++) {
            if (lst[i] == '(') c++;
            if (lst[i] == ')') c--;
            if (c==0 && lst[i] == ')') return i;
        }
        return -1;
    }
    static boolean isNumeric(String str) { 
        return Character.isDigit(str.charAt(0));
    }

    static boolean isOP(char ch) {
        return (ch=='+' || ch=='-' || ch=='×' || ch=='÷' || ch=='^' || ch=='!');
    }

    static String fixMinus(String n) {
        if (n.startsWith("-")) n = "(0" + n + ")";
        return n;
    }

    public static long fact(double n) {
        long result = 1;
        for (int factor = 2; factor <= n; factor++) {
            result *= factor;
        }
        return result;
    }

    public static void setVars(double[] lst) {
        vars = lst;
    }

    public static String proccess(String exp) throws Exception {
        if (exp.equals("NaN")) throw new Exception("Not in domain!");
        List<String> elements = new ArrayList<String>();
        char[] chars = exp.toCharArray();
        boolean minus = false;
        int index = 0;
        while (index < chars.length) {
    
            if (Character.isDigit(chars[index])) {
                String val = String.valueOf(chars[index]);
                while (index < chars.length - 1 && (Character.isDigit(chars[index + 1]) || chars[index + 1] == '.'  || chars[index + 1] == 'E' || (chars[index] == 'E' && chars[index + 1] == '-'))) {
                    val += chars[index + 1];
                    index++;
                }
                if (minus) {
                    val = "(0-" + val + ")";
                    minus = false;
                }
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(val);
                    elements.add("×");
                } else {
                    elements.add(val);
                }
            }
            if (chars[index] == '-') {
                if (index==0 || isOP(chars[index-1])) minus = true;
                else elements.add("-");
            } else if (chars[index]=='+' || chars[index]=='×' || chars[index]=='÷' || chars[index]=='^') {
                elements.add(Character.toString(chars[index]));
            } else if (chars[index] == 'π') {
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(Double.toString(Math.PI));
                    elements.add("×");
                } else {
                    elements.add(Double.toString(Math.PI));
                }
            } else if (chars[index] == 'e') {
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(Double.toString(Math.E));
                    elements.add("×");
                } else {
                    elements.add(Double.toString(Math.E));
                }
            } else if (chars[index] == '(') {
                int end = findClose(chars, index);
                String res = '(' + proccess(exp.substring(index+1, end)) + ')';
                if (minus) {
                    res = "(0-" + res + ")";
                    minus = false;
                }
                elements.add(res);
                index = end;
            } else if (chars[index] == '√') {
                int end = findClose(chars, index+1);
                elements.add(Double.toString(Math.sqrt(Double.parseDouble(eval(proccess(exp.substring(index+2, end)))))));
                index = end;
            } else if (chars[index] == '!') {
                double last_el = Double.parseDouble(eval(proccess(elements.get(elements.size()-1))));
                if (last_el != Math.round(last_el) || last_el<0) throw new Exception("Fact must be applied on a positive integer");
                elements.set(elements.size()-1, Double.toString(fact(last_el)));
            } else if (chars[index]=='l'&&chars[index+1]=='n') {
                int end = findClose(chars, index+2);
                elements.add(fixMinus(Double.toString(Math.log(Double.parseDouble(eval(proccess(exp.substring(index+3, end))))))));
                index = end;
            } else if (chars[index]=='a'&&chars[index+1]=='b'&&chars[index+2]=='s') {
                int end = findClose(chars, index+3);
                elements.add(Double.toString(Math.abs(Double.parseDouble(eval(proccess(exp.substring(index+4, end)))))));
                index = end;
            } else if (chars[index]=='s'&&chars[index+1]=='i'&&chars[index+2]=='n') {
                int end = findClose(chars, index+3);
                elements.add(fixMinus(Double.toString(Math.sin(Math.PI/180*Double.parseDouble(eval(proccess(exp.substring(index+4, end))))))));
                index = end;
            } else if (chars[index]=='c'&&chars[index+1]=='o'&&chars[index+2]=='s') {
                int end = findClose(chars, index+3);
                elements.add(fixMinus(Double.toString(Math.cos(Math.PI/180*Double.parseDouble(eval(proccess(exp.substring(index+4, end))))))));
                index = end;
            } else if (chars[index]=='t'&&chars[index+1]=='a'&&chars[index+2]=='n') {
                int end = findClose(chars, index+3);
                elements.add(fixMinus(Double.toString(Math.tan(Math.PI/180*Double.parseDouble(eval(proccess(exp.substring(index+4, end))))))));
                index = end;
            } else if (chars[index] == 'x') {
                if (vars[0] == 0) throw new Exception("x is not initialized!");
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(fixMinus(Double.toString(vars[0])));
                    elements.add("×");
                } else {
                    elements.add(fixMinus(Double.toString(vars[0])));
                }
            } else if (chars[index] == 'y') {
                if (vars[1] == 0) throw new Exception("y is not initialized!");
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(fixMinus(Double.toString(vars[1])));
                    elements.add("×");
                } else {
                    elements.add(fixMinus(Double.toString(vars[1])));
                }
            } else if (chars[index] == 'z') {
                if (vars[2] == 0) throw new Exception("z is not initialized!");
                if (index != chars.length - 1 && !isOP(chars[index+1]) && chars[index+1] != ')') {
                    elements.add(fixMinus(Double.toString(vars[2])));
                    elements.add("×");
                } else {
                    elements.add(fixMinus(Double.toString(vars[2])));
                }
            }
            index++;
        }

        return String.join("", elements);
    }

    public static void main(String[] args) {
        toPost("s/(5+u)");
    }
}