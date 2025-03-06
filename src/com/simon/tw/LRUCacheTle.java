package com.simon.tw;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class LRUCacheTle { // 超时  查找和移动节点的复杂度是O(n) 而不是 O(1)
    Deque<int[]> a = new ArrayDeque<>();
    int limit;

    public LRUCacheTle(int capacity) {
        this.limit = capacity;
    }

    public int get(int key) {
        if(contains( key) ){
           //放到顶
            int[] tmp = remove(key);
            a.push(tmp);
            // return key; // 这里错啦，应该返回的是value，缓存存的是键值对。
            return tmp[1];
        }else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if(contains(key) ){
            //放到顶
            remove(key);
            a.push(new int[]{key, value});
        }else {
            a.push(new int[]{key, value});
        }
        if(a.size()>limit) {
            a.pollLast();
        }
    }

    // 包装方法，处理 int[]{} 作为键值对，保存在
    private boolean contains( int x){
        for(int[] arr : a ){
            if(x == arr[0]) return true;
        }
        return false;
    }
    private int[] remove(int key) {
        Iterator<int[]> iterator = a.iterator();
        while(iterator.hasNext()) {
            int[] cur = iterator.next();
            if(cur[0] == key) {
                iterator.remove();
                return cur;
            }
        }
        return new int[]{0,0};
    }


    public static void main(String[] args) {
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // 缓存是 {1=1}
//        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//        System.out.println(lRUCache.get(1));    // 返回 1
//        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
//        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
//        System.out.println(lRUCache.get(3));    // 返回 3
//        System.out.println(lRUCache.get(4));    // 返回 4

//        ["LRUCache","put","get"]
//[[1],[2,1],[2]]
//        exp
//                [null,null,1]
        LRUCacheTle lRUCache = new LRUCacheTle(1);
        lRUCache.put(2, 1);
        System.out.println(lRUCache.get(2)); // 返回1
        //
    }
}
