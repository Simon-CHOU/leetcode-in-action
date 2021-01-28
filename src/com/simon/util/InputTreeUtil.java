package com.simon.util;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class InputTreeUtil {
    public static void displayBTreeVertically(TreeNode root) {
        if(root != null) {
            System.out.print(root.val + " ");
            displayBTreeVertically(root.left);
            displayBTreeVertically(root.right);
        }

    }

    public static TreeNode inputBtree() {
        Scanner scanner = new Scanner(System.in);
        String[] a = scanner.nextLine().trim().replaceAll(" ","").replaceAll("[\\[\\]]", "").split(",");// [4,2,7,1,3,6,9]
        int [] intArr = new int[a.length];
        Integer [] INTArr = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            INTArr[i] = a[i].equals("null") ? null : Integer.parseInt(a[i]);
            intArr[i] = a[i].equals("null") ? -1 : Integer.parseInt(a[i]);
        }
//        TreeNode root = new TreeNode();
//        root  = constructBTreeByArray(intArr,  root, 0);
        TreeNode root = createTree(INTArr);
//        TreeNode root = createTreeByPrimeIntArr(intArr);
        return root;
//        displayBTreeVertically(root);
    }

    /**
     * 不支持null
     * https://www.geeksforgeeks.org/construct-complete-binary-tree-given-array/
     *
     * @param arr
     * @param root
     * @param index
     * @return
     */
    public static TreeNode constructBTreeByArray(int[] arr, TreeNode root, int index) {
        if( index < arr.length) {
            TreeNode tmp = new TreeNode(arr[index], null, null);
            root = tmp;
            root.left = constructBTreeByArray(arr, root.left, 2 * index + 1);
            root.right = constructBTreeByArray(arr, root.right, 2 * index + 2);
        }
        return root;
    }

    /**
     * 支持null
     * https://stackoverflow.com/questions/37941318/how-to-build-an-incomplete-binary-tree-from-array-representation
     * @param array
     * @return
     */
    public static  TreeNode createTree(Integer[] array){
        if(array == null || array.length == 0){
            return null;
        }
        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        Queue<Integer> integerQueue = new LinkedList<>();
        for (int i = 1; i < array.length; i++) {
            integerQueue.offer(array[i]);
        }

        TreeNode treeNode = new TreeNode(array[0]);
        treeNodeQueue.offer(treeNode);

        while (!integerQueue.isEmpty()){
            Integer leftVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            Integer rightVal = integerQueue.isEmpty() ? null : integerQueue.poll();
            TreeNode current = treeNodeQueue.poll();
            if(leftVal != -1){
                TreeNode left = new TreeNode(leftVal);
                current.left = left;
                treeNodeQueue.offer(left);
            }
            if(rightVal !=-1){
                TreeNode right = new TreeNode(rightVal);
                current.right = right;
                treeNodeQueue.offer(right);
            }
        }
        return treeNode;
    }
}
