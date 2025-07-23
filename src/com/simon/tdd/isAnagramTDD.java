package com.simon.tdd;

public class isAnagramTDD {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) {return false;} // 20250719
        // HashMap<String, Integer> hmap = new HashMap<>();
        int[] iArr = new int[26];
        for(int i = 0; i< s.length(); i++) {
            iArr[s.charAt(i) - 'a']++;
            iArr[t.charAt(i) - 'a']--;
        }
        int count =0;
        for(int i = 0; i< 26; i++) {
            count+=iArr[i];
        }
        return count == 0;
    }
    public static void main(String[] args) {
        isAnagramTDD iat = new isAnagramTDD();
        System.out.println(iat.isAnagram("rat", "car"));
    }
}
