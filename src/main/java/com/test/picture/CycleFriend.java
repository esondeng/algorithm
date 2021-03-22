package com.test.picture;

/**
 * 相连接的cycle个数。
 * For example, if A is a direct friend of B, and B is a direct friend of C,
 * then A is an indirect friend of C
 * Input:
 * [1,1,0],
 * [1,1,0],
 * [0,0,1]
 * Output: 2
 * Input:
 * [1,1,0],
 * [1,1,1],
 * [0,1,1]
 * Output: 1
 * 思路，深度优先， 从第一行开始，有1，而且当前行没有访问过计数加1。 深度优先算法类似于二叉树的先序遍历。
 * 核心原理是：一个数据记录是否访问，从一个节点触发一直访问到不能访问为止，一条道走到黑。
 *
 * @author dengxiaolin
 * @since 2020/11/20
 */
public class CycleFriend {

    int count;
    int[] visit;

    public static void main(String[] args) {
        CycleFriend cycleFriend = new CycleFriend();
        int[][] m = new int[][] {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        cycleFriend.findCycle(m);
        System.out.println(cycleFriend.count);
    }


    public int findCycle(int[][] m) {
        if (m == null || m.length == 0) {
            return 0;
        }

        visit = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            if (visit[i] == 0) {
                // 深度优先标记后，记录次数
                dfs(m, i);
                count++;
            }
        }

        return count;
    }

    private void dfs(int[][] m, int i) {
        // 计算当前行所有的friend计算完成之后，count++
        for (int j = 0; j < m[i].length; j++) {
            if (m[i][j] == 1 && visit[j] == 0) {
                visit[j] = 1;
                dfs(m, j);
            }
        }
    }


}
