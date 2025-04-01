package com.simon.tw;

import java.util.ArrayDeque;
import java.util.Deque;

class MyQueue {

    Deque<Integer> inStack;
    Deque<Integer> outStack;

    /**
     * Initialize your data structure here.
     */
    public MyQueue() {
        inStack = new ArrayDeque<>();
        outStack = new ArrayDeque<>();
    }

    /**
     * Push element x to the back of queue.
     */
    public void push(int x) {
        inStack.push(x);
    }

    /**
     * Removes the element from in front of queue and returns that element.
     */
    public int pop() {
        if (outStack.isEmpty()) {
            in2out();
        }
        return outStack.pop();
    }

    /**
     * Get the front element.
     */
    public int peek() {
        if (outStack.isEmpty()) {
            in2out();
            // 这里不是 inStack.peek()
        }
        return outStack.peek();
    }

    /**
     * Returns whether the queue is empty.
     */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void in2out() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}

public class ImplementQueueUsingStacksLcci {
    public static void main(String[] args) {
        MyQueue queue = new MyQueue();

        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());  // 返回 1
        System.out.println(queue.pop());   // 返回 1
        System.out.println(queue.empty()); // 返回 false
        System.out.println("00000000");
        //["MyQueue","push","push","peek","push","peek"]
        //[[],[1],[2],[],[3],[]] // 用例可以测试判空
        MyQueue queue1 = new MyQueue();
        queue1.push(1);
        queue1.push(2);
        System.out.println(queue1.peek()); // 1
        queue1.push(3);
        System.out.println(queue1.peek()); // 1

    }
}
