package com.simon.tdd;

public class NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThresholdTDD {
    public int numOfSubarrays(int[] arr, int k, int threshold) {

        int Count = 0;
        int curSum = 0 ;
        int bar = threshold * k;

        for(int i =0 ;  i < k ;i++) {
            curSum += arr[i];
        }
        if( curSum >= bar) Count++;


        for(int i =k ;  i < arr.length ;i++) {
            int left = arr[i - k];
            curSum -= left ;
            int right = arr[i];
            curSum += right ;

            if(curSum >= bar) Count++;
        }

        return Count;

    }

    public static void main(String[] args) {
        NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThresholdTDD solution = new NumberOfSubArraysOfSizeKAndAverageGreaterThanOrEqualToThresholdTDD();
        int[] arr = {2,2,2,2,5,5,5,8};
        int k = 3;
        int threshold = 4;
        int result = solution.numOfSubarrays(arr, k, threshold);
        System.out.println("Test 1: " + result + " → " + (result == 3 ? "PASS" : "FAIL"));
    }

}
