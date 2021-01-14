package com.simon.uitl;

public class ListNode {
    public int val;
    public ListNode next;
    public ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        StringBuilder str=new StringBuilder();
        str.append("[").append(val);
        if(next == null){
            return str.append("]").toString();
        }
        ListNode p=next;
        while(p.next!=null){
            str.append(",").append(p.val);
            p = p.next;
        }
        str.append("]");
        return str.toString();
    }
}