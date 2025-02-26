package com.simon.tw;

class Solution345 {
    public String reverseVowels(String s) {
        int n = s.length();
        int i = 0, j = n - 1;
        char[] arr = s.toCharArray();// arr 比 string 好swap
        while (i < j) {
            while (i < n && !isVowels(arr[i])) { // 不要写 s.charAt(i)
                i++;
            }
//            if (j >= 0 && !isVowels(s.charAt(j))) {
            while (j> 0  && !isVowels(arr[j])) {
                j--;
            }
            if (i < j) {// isVowels must be true
                swap(arr, i, j);
                i++;
                j--;
            }
        }

        return new String(arr);
    }

    boolean isVowels(char ch) {
        return "aeiouAEIOU".contains(String.valueOf(ch));
    }

    void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

public class ReverseVowelsOfAString {
    public static void main(String[] args) {
        Solution345 s = new Solution345();
        System.out.println(s.reverseVowels("IceCreAm")); // AceCreIm
        System.out.println(s.reverseVowels("a.b,.")); //"a.b,."
    }
}
