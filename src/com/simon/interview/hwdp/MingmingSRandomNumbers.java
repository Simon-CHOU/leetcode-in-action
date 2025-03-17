package com.simon.interview.hwdp;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 明明的随机数
 * 对于明明生成的 n 个 1 到 500 之间的随机整数，你需要帮助他完成以下任务：
 * ∙删去重复的数字，即相同的数字只保留一个，把其余相同的数去掉；
 * ∙然后再把这些数从小到大排序，按照排好的顺序输出。
 * 你只需要输出最终的排序结果。
 */
public class MingmingSRandomNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();
            Set<Integer> hash =new TreeSet<>();
            for (int i = 0; i < n; i++) {
                int b = in.nextInt();
                hash.add(b);
            }
            // 从小到大排序 ，TreeSet无需额外操作
            for(Integer ii : hash) {
                System.out.println(ii);
            }
        }
    }
}
/**
 * TreeSet 是 Java 中的一个集合类，它实现了 SortedSet 接口，默认会按照元素的自然顺序（从小到大）进行排序。因此，当你使用 TreeSet 时，元素会自动按照从小到大的顺序存储，无需额外操作。
 */
