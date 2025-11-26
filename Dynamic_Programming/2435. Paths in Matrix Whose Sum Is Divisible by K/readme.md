# LeetCode 2435 — Paths in Matrix Whose Sum Is Divisible by K

**Difficulty:** Hard
**Tags:** Dynamic Programming, Grid, Modulo, 3D DP

## Problem

Given an `m x n` integer matrix `grid` and an integer `k`, you start at `(0,0)` and want to reach `(m-1,n-1)` moving only **down** or **right**.

Return the **number of paths** where the sum of the elements along the path is **divisible by `k`**. Since the number of paths can be large, return it modulo `10^9 + 7`.

## Key Idea

* Use dynamic programming that tracks remainders modulo `k` at each cell.
* Let `dp[i][j][r]` = number of ways to reach cell `(i,j)` such that the path sum modulo `k` equals `r`.
* Transition from top `(i-1,j)` and left `(i,j-1)` by adding the current cell value and updating the new remainder.
* Base case: `dp[0][0][grid[0][0] % k] = 1`.

## Algorithm (step-by-step)

1. Initialize 3D DP array `dp[m][n][k]` with zeros.
2. Set `dp[0][0][grid[0][0] % k] = 1`.
3. Iterate rows `i = 0..m-1` and cols `j = 0..n-1`:

   * For each previous remainder `rPrev = 0..k-1`:

     * If `i > 0`: add `dp[i-1][j][rPrev]` to `dp[i][j][(rPrev + val) % k]`.
     * If `j > 0`: add `dp[i][j-1][rPrev]` to `dp[i][j][(rPrev + val) % k]`.
   * Apply modulo `1_000_000_007` on updates.
4. Answer is `dp[m-1][n-1][0]` (paths whose sum % k == 0).

## Time & Space Complexity

| Metric | Value                                                                         |
| ------ | ----------------------------------------------------------------------------- |
| Time   | **O(m * n * k)**                                                              |
| Space  | **O(m * n * k)** (can be reduced by rolling array on one dimension if needed) |

## Code (Java)

```java
class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = grid[i][j];
                for (int rPrev = 0; rPrev < k; rPrev++) {
                    if (i > 0) {
                        int newR = (rPrev + val) % k;
                        dp[i][j][newR] = (dp[i][j][newR] + dp[i - 1][j][rPrev]) % MOD;
                    }
                    if (j > 0) {
                        int newR = (rPrev + val) % k;
                        dp[i][j][newR] = (dp[i][j][newR] + dp[i][j - 1][rPrev]) % MOD;
                    }
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}
```

## Notes

* Use modulo at every addition to avoid overflow.
* If memory is tight, you can optimize space by keeping only the previous row (or previous column) of DP states — reducing space to **O(n * k)** or **O(m * k)**.
* Works for standard constraints where `k` is reasonably small.
