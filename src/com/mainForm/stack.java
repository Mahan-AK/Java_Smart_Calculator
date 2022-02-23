package com.mainForm;

public class stack {
    private int cap;
    private int top = -1;
    private String[] stack;
    
    stack(int length) {
        this.cap = length;
        this.stack = new String[cap];
    }


    void push(String str){
        if (top == this.cap - 1) {
            System.out.println("Error! Stack is FULL");
        } else {
            stack[++top] = str;
        }
    }

    String pop(){
        if (top == -1) {
            System.out.println("Error! Stack is Empty");
        } else {
            String popped = stack[top];
            stack[top] = null;
            --top;
            return popped;
        }
        return "";
    }

    boolean isEmpty() {
        return top == -1;
    }

    boolean isFull() {
        return top == this.cap - 1;
    }

    String top() {
        return stack[top];
    }
}