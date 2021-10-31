# week4

## homework

### 538. 把二叉搜索树转换为累加树
```java
// 538. 把二叉搜索树转换为累加树
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root != null) {
            System.out.println("current root.val="+root.val);
            convertBST(root.right);
            int cache = sum;
            sum += root.val;
            root.val = sum;
            System.out.println("sum=sum + root.val=" + cache + " + " + sum + ", root.val=sum=" + sum);
            convertBST(root.left);
        }
        return root;
    }
}
/*
origin:
     4
  1    6
0  2  5  7
    3     8
greater sum tree:
     30
  36    21
36  35  26  15
    33        8
*/
/*log:
current root.val=4
current root.val=6
current root.val=7
current root.val=8
sum=sum + root.val=0 + 8, root.val=sum=8
sum=sum + root.val=8 + 15, root.val=sum=15
sum=sum + root.val=15 + 21, root.val=sum=21
current root.val=5
sum=sum + root.val=21 + 26, root.val=sum=26
sum=sum + root.val=26 + 30, root.val=sum=30
current root.val=1
current root.val=2
current root.val=3
sum=sum + root.val=30 + 33, root.val=sum=33
sum=sum + root.val=33 + 35, root.val=sum=35
sum=sum + root.val=35 + 36, root.val=sum=36
current root.val=0
sum=sum + root.val=36 + 36, root.val=sum=36
*/

```

### 355. 设计推特
```java
// 355. 设计推特
class Twitter {
    private class Node {
        // 哈希表存储关注人的 Id
        Set<Integer> followee;
        // 用链表存储 tweetId
        LinkedList<Integer> tweet;

        Node() {
            followee = new HashSet<Integer>();
            tweet = new LinkedList<Integer>();
        }
    }

    // getNewsFeed 检索的推文的上限以及 tweetId 的时间戳
    private int recentMax, time;
    // tweetId 对应发送的时间
    private Map<Integer, Integer> tweetTime;
    // 每个用户存储的信息
    private Map<Integer, Node> user;

    public Twitter() {
        time = 0;
        recentMax = 10;
        tweetTime = new HashMap<Integer, Integer>();
        user = new HashMap<Integer, Node>();
    }

    // 初始化
    public void init(int userId) {
        user.put(userId, new Node());
    }

    public void postTweet(int userId, int tweetId) {
        if (!user.containsKey(userId)) {
            init(userId);
        }
        // 达到限制，剔除链表末尾元素
        if (user.get(userId).tweet.size() == recentMax) {
            user.get(userId).tweet.remove(recentMax - 1);
        }
        user.get(userId).tweet.addFirst(tweetId);
        tweetTime.put(tweetId, ++time);
    }

    public List<Integer> getNewsFeed(int userId) {
        LinkedList<Integer> ans = new LinkedList<Integer>();
        for (int it : user.getOrDefault(userId, new Node()).tweet) {
            ans.addLast(it);
        }
        for (int followeeId : user.getOrDefault(userId, new Node()).followee) {
            if (followeeId == userId) { // 可能出现自己关注自己的情况
                continue;
            }
            LinkedList<Integer> res = new LinkedList<Integer>();
            int tweetSize = user.get(followeeId).tweet.size();
            Iterator<Integer> it = user.get(followeeId).tweet.iterator();
            int i = 0;
            int j = 0;
            int curr = -1;
            // 线性归并
            if (j < tweetSize) {
                curr = it.next();
                while (i < ans.size() && j < tweetSize) {
                    if (tweetTime.get(curr) > tweetTime.get(ans.get(i))) {
                        res.addLast(curr);
                        ++j;
                        if (it.hasNext()) {
                            curr = it.next();
                        }
                    } else {
                        res.addLast(ans.get(i));
                        ++i;
                    }
                    // 已经找到这两个链表合起来后最近的 recentMax 条推文
                    if (res.size() == recentMax) {
                        break;
                    }
                }
            }
            for (; i < ans.size() && res.size() < recentMax; ++i) {
                res.addLast(ans.get(i));
            }
            if (j < tweetSize && res.size() < recentMax) {
                res.addLast(curr);
                for (; it.hasNext() && res.size() < recentMax;) {
                    res.addLast(it.next());
                }
            }
            ans = new LinkedList<Integer>(res);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        if (!user.containsKey(followerId)) {
            init(followerId);
        }
        if (!user.containsKey(followeeId)) {
            init(followeeId);
        }
        user.get(followerId).followee.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        user.getOrDefault(followerId, new Node()).followee.remove(followeeId);
    }
}
```
### 130. 被围绕的区域
```java
// 130. 被围绕的区域
class Solution {
    int n, m;

    public void solve(char[][] board) {
        n = board.length;
        if (n == 0) {
            return;
        }
        m = board[0].length;
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m - 1);
        }
        for (int i = 1; i < m - 1; i++) {
            dfs(board, 0, i);
            dfs(board, n - 1, i);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'A';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    }
}
```

## example


## DFS, BFS

### 17. 电话号码的字母组合
### 51. N 皇后
### 200. 岛屿数量
### 433. 最小基因变化
### 329. 矩阵中的最长递增路径

## Binary Heap/Binary Search Tree

### 23. 合并K个升序链表#Binary Heap
### 239. 滑动窗口最大值#Binary Heap
### 701. 二叉搜索树中的插入操作#Binary Search Tree
### 面试题 04.06. 后继者#Binary Search Tree
## 450. 删除二叉搜索树中的节点#Binary Search Tree
