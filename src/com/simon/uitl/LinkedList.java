package com.simon.uitl;

public class LinkedList {
    private  ListNode first = new ListNode();

    public void insert(int item){
        ListNode oldfirst = first;
        ListNode first = new ListNode(item, oldfirst);
    }

    public  ListNode getHead(){
        return this.first;
    }

    public ListNode initByArr(int[] a){
        for (int i = 0; i < a.length; i++) {
            insert(i);
        }
        return this.first;
    }
}
