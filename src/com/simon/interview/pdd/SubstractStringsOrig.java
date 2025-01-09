package com.simon.interview.pdd;

public class SubstractStringsOrig {
    public static void main(String[] args) {
        String re = subtractStrings("100", "1");
        System.out.println(re);// 101-9 答案完全错误，面试过程中漏洞百出
        //940 749 749
    }
    // 0 - 1 -9    0
    // "10" null  -1    99
    // 100  0
    //  s1  s2  lend  sb
    //  0  0    -1    9
    //  0  null

    //110 - 11
    //  s1  s2  lend  sb
    //  0    1  -1  9
    //  1-1  1  -1    9
    //  1-1  null

    // 100 charAt()
    public static String subtractStrings(String s1, String s2) {

        StringBuilder res = new StringBuilder();
        boolean finished = false;
        for (int i = s1.length() - 1; i >= 0; i--) {
            int lend = 0;// 减法借1位 下一次循环-1
            if (!finished) {
                for (int j = s2.length() - 1; j >= 0; j--) {
                    int i1 = s1.charAt(i) - '0';  // char c1 =  Integer.valueOf(c1);
                    // pay back
                    i1 += lend;
                    lend = 0;
                    int i2 = s2.charAt(j) - '0';
                    if (i1 >= i2) {
                        res.append(String.valueOf(i1 - i2));
                    } else {//
                        res.append(String.valueOf(10 + i1 - i2));
                        lend = -1;
                    }
                    if (j == 0) {
                        finished = true;
                    }
                }
            }

            // s1
            int ii = s1.charAt(i) - '0';
            if (lend != 0) {
                ii = ii + lend;
                lend = 0;// has been payed
            }

            res.append(String.valueOf(ii));
        }

        return res.reverse().toString();

    }
}
//  StringBuilder sbReverse = new StringBuilder();
//  for(int i = 0; i< res.length(); i++){
//      sbReverse.append(res.charAt(res.length() - 1 - i));
//  }
//  Strig.r
//  // 长的在左
//  /
//  for(int i = s1.length(); i >=0 ; i--) {
//      100
//      1

//      0 -1  =  10-1  1 //借位
//      10  * 10^1 当前的位数// =截个屏


//  }
//  // 转值

//  // 退位
