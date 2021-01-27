package com.simon.util;

import java.util.Scanner;

public class InputTreeUtil {
    public static void displayBTreeVertically(TreeNode root) {
        if(root != null) {
            displayBTreeVertically(root.left);
            System.out.print(root.val + " ");
            displayBTreeVertically(root.right);
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] a = scanner.nextLine().trim().replaceAll(" ","").replaceAll("[\\[\\]]", "").split(",");// [4,2,7,1,3,6,9]
        int [] intArr = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if(a[i].equals("null")){
                intArr[i] = -1;//flag for null
                continue;
            }
            intArr[i] = Integer.parseInt(a[i]);
        }
        TreeNode root = new TreeNode();
        root  = constructBTreeByArray(intArr,  root, 0);
        displayBTreeVertically(root);
    }

    public static TreeNode constructBTreeByArray(int[] arr, TreeNode root, int index) {
        if (index < arr.length && arr[index] != -1) {
            TreeNode tmp = new TreeNode(arr[index], null, null);
            root = tmp;
            root.left = constructBTreeByArray(arr, root.left, 2 * index + 1);
            root.right = constructBTreeByArray(arr, root.right, 2 * index + 2);
        }
        return root;
    }
}
