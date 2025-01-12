package com.simon.str;

import java.util.Deque;
import java.util.LinkedList;

public class MinStack {
    Deque<Integer> xStack;
    Deque<Integer> minStack;

    public MinStack() {
        xStack = new LinkedList<>();
        minStack = new LinkedList<>();
        minStack.push(Integer.MAX_VALUE);//初始化一个最大值
    }

    public void push(int val) {
        xStack.push(val);
        minStack.push(Math.min(minStack.peek(), val)); //这里不是pop
    }

    public void pop() {
        xStack.pop();
        minStack.pop();
    }

    public int top() {
        return xStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());  // --> 返回 -3.
        minStack.pop();
        System.out.println(minStack.top());     //--> 返回 0.
        System.out.println(minStack.getMin());  //--> 返回 -2.
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */