package com.simon.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] inputIntArray() {
        System.out.print("#Please Input:");
        String str = scanner.nextLine();//.next()不能接收空字符串换行
        if(str.equals("")){
            return new int[0];
        }
        String[] s = str.replaceAll(" ","").replaceAll("[\\[|\\]]","").replaceAll("\"", "").split(",");
        return getIntArrByStrArr(s);
    }
    public static int[] getIntArrByStrArr(String[] s) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        return a;
    }
    public static int inputInt(){
        return scanner.nextInt();
    }
    public static String inputStr(){
        System.out.print("#Please Input:");
        return scanner.nextLine();
    }

    public static ListNode inputLinkedList(){
        int[] ints = inputIntArray();
        ListNode head = new ListNode();
        ListNode p  = head;
        for (int i = 0; i < ints.length; i++) {
            ListNode node = new ListNode(ints[i]);
            p.next = node;
            p = p.next;
        }
        return head.next;
    }
    public static int[][] matrix2D(){
        String s = InputUtil.inputStr().trim();
        String substring = s.substring(2, s.length() - 2);
        String[] split = substring.split("],\\[");
        int[][] events = new int[split.length][2];
        for (int i = 0; i < split.length; i++) {
            String[] split1 = split[i].split(",");
            for (int j = 0; j < split1.length; j++) {
                events[i][j] = Integer.parseInt(split1[j]);
            }
        }
        return events;
    }
}
