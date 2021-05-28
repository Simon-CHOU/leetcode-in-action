package com.simon.util;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] inputIntArray() {
        System.out.print("#Please Input:");
        String str = scanner.nextLine();//.next()不能接收空字符串换行
        if (str.equals("")) {
            return new int[0];
        }
        String[] s = str.replaceAll(" ", "").replaceAll("[\\[|\\]]", "").replaceAll("\"", "").split(",");
        return getIntArrByStrArr(s);
    }

    public static int[] getIntArrByStrArr(String[] s) {
        int[] a = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        return a;
    }

    public static int inputInt() {
        return scanner.nextInt();
    }

    public static String inputStr() {
        System.out.print("#Please Input:");
        return scanner.nextLine();
    }

    public static ListNode inputLinkedList() {
        int[] ints = inputIntArray();
        ListNode head = new ListNode();
        ListNode p = head;
        for (int i = 0; i < ints.length; i++) {
            ListNode node = new ListNode(ints[i]);
            p.next = node;
            p = p.next;
        }
        return head.next;
    }

    public static int[][] matrix2D() {
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

    /**
     * 输入r行c列的二维字符数组（数独）
     *
     * @param r 行
     * @param c 列
     * @return 二维字符数组
     */
    public static char[][] matrixC2D(int r, int c) {
        System.out.println("输入" + r + "行" + c + "列的二位数组");
        char[][] charArr2d = new char[r][c];
        for (int i = 0; i < r; i++) {
            String str = scanner.nextLine().trim();
            String s = str
                    .substring(2, str.length() - 1)
                    .replace("\"", "");
            String[] split = s.split(",");
            for (int j = 0; j < split.length; j++) {
                charArr2d[i][j] = split[j].charAt(0);
            }
        }
        return charArr2d;
    }
    /*
     [["5","3",".",".","7",".",".",".","."]
 ,["6",".",".","1","9","5",".",".","."]
 ,[".","9","8",".",".",".",".","6","."]
 ,["8",".",".",".","6",".",".",".","3"]
 ,["4",".",".","8",".","3",".",".","1"]
 ,["7",".",".",".","2",".",".",".","6"]
 ,[".","6",".",".",".",".","2","8","."]
 ,[".",".",".","4","1","9",".",".","5"]
 ,[".",".",".",".","8",".",".","7","9"]]
     */

}
