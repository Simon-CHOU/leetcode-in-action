package com.simon.upskill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * <a href="https://www.youtube.com/watch?v=BeJu9bMPLGU">IntelliJ IDEA Pro Tips: Debugging Java Streams</a>
 */
public class StreamDebuggerExample {
    /**
     * 该Java函数用于将整数分解成质因数，生成一个整数流返回。它通过循环找到并添加所有因数到列表，同时逐步缩小原数值，最后将列表转换为整数流输出。
     * @param value
     * @return
     */
    private static IntStream factorize(int value) {
        List<Integer> factors = new ArrayList<>();
        for (int i = 2; i <= value; i++) {
            while (value % i == 0) {
                factors.add(i);
                value /= i;
            }
        }
        return factors.stream().mapToInt(Integer::intValue);
    }

    public static void main(String[] args) {
        int[] result = IntStream.of(10, 87, 97, 43, 121, 20
                ).flatMap(StreamDebuggerExample::factorize)
                .distinct()
                .sorted()
                .toArray();
        System.out.println(Arrays.toString(result));
    }
}
