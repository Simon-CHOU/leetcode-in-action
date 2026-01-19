package com.simon.hot100;

public class SearchInRotatedSortedArrayTDD {
    
public static int search(int[] nums, int target) {
        for( int i =0; i < nums.length ; i++ ) {
            if(nums[i] == target) {return i;}  // 我的理解目的就是要处理旋转后的i 到旋转前的i
        }
    return  -1;
    }
    public static void main(String[] args) {
        // nums = [4,5,6,7,0,1,2], target = 0
        int[]  nums = new int[]{4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));

    }
}
