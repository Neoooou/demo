package com.example.algorithm;

/**
 * deep first search 深度优先遍历
 * @author neo.zr
 * @since 2024/3/20
 */

public class DFS {

    public int numIslands(char[][] grid) {
        if (grid == null) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; ++i) {
            for (int j = 0; j < grid[0].length; ++j) {
                if (grid[i][j] == '1') {
                    res += numIsland(grid, i, j);
                }
            }
        }
        return res;
    }

    public int numIsland(char[][] grid, int i, int j) {
        if (i < 0 || i > grid.length || j < 0 || j > grid[0].length) {
            return 0;
        }
        if(grid[i][j] == '0'){
            return 0;
        }
        grid[i][j] = '2';

        return 1 + numIsland(grid, i - 1, j) +
                numIsland(grid, i + 1, j) + numIsland(grid, i, j - 1) + numIsland(grid, i, j + 1);
    }
}
