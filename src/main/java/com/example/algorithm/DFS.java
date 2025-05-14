package com.example.algorithm;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.CollectionUtils;

/**
 * deep first search 深度优先遍历
 * @author neo.zr
 * @since 2024/3/20
 */

public class DFS {

    public static void main(String[] args) {
        String data = "{\"success\":true,\"tickets\":[{\"visualID\":\"81045112503070754121749173524\",\"plu\":\"180120211007\",\"dateSold\":\"2025-03-07T22:27:00-05:00\",\"expirationDate\":\"2025-03-24T23:59:00-04:00\",\"status\":\"Cancelled\",\"cancellable\":false},{\"visualID\":\"81045112503070754121799611735\",\"plu\":\"180120211007\",\"dateSold\":\"2025-03-07T22:27:00-05:00\",\"expirationDate\":\"2025-03-24T23:59:00-04:00\",\"status\":\"Cancelled\",\"cancellable\":false}]}";

        JSONObject jsonObject = JSON.parseObject(data);

        if (!"true".equalsIgnoreCase(jsonObject.getString("success"))) {
            return;
        }
        JSONArray tickets = jsonObject.getJSONArray("tickets");
        if(CollectionUtils.isEmpty(tickets)){
            return;
        }
        System.out.println(tickets.stream().allMatch(e -> "Cancelled".equals(((JSONObject)e).getString("status"))));
    }

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
