package com.simon.uitl;

import java.util.Scanner;

public class InputUtil {
    private static final Scanner scanner = new Scanner(System.in);

    public static int[] inputIntArray() {
        System.out.print("#Please Input:");
        String str = scanner.next();
        String[] s = str.replaceAll("\"", "").split(",");
        int[] a = getIntArrByStrArr(s);
        return a;
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
}
