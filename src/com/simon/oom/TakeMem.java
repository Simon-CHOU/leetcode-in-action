package com.simon.oom;

public class TakeMem {
    public static void main(String[] args) {
        StringBuilder db = new StringBuilder();
        for (long i = 0; i <= Long.MAX_VALUE - 1; i++) {
            StringBuilder db1 = new StringBuilder();
            db1.append("大");
            for (long k = 0; k <= Long.MAX_VALUE - 1; k++) {
                db.append(db1);
            }
        }
        System.out.println(db);
        //Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
        // Heap oom
    }
}
//Requested array size exceeds VM limit
// 如何获取整块的内存
//JVM实际内存占用超过Xmx的原因,设置Xmx的技巧
//https://www.cnblogs.com/wsx2019/p/15794910.html
