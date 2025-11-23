# LeetCode 1262 - Greatest Sum Divisible by Three

**Difficulty:** Medium
**Tags:** Dynamic Programming, Modulo, Array

## Problem

Given an integer array `nums`, return the **maximum possible sum** of a subsequence such that the sum is **divisible by 3**.

You may pick any subset of elements (not necessarily contiguous). The goal is to maximize the resulting sum while keeping it divisible by 3.

## Key Idea

This is a classic **DP with remainder states** problem.

We maintain a DP array:

* `dp[0]` = maximum sum with remainder **0** mod 3
* `dp[1]` = maximum sum with remainder **1** mod 3
* `dp[2]` = maximum sum with remainder **2** mod 3

Initially:

* `dp = {0, NEG, NEG}` where `NEG` is a very negative value meaning "impossible state".

For each number `num`:

1. Compute its remainder `r = num % 3`.
2. Clone current DP into `next`.
3. For each remainder state `j` in `0..2`:

   * If `dp[j]` is valid, compute new remainder `newR = (j + r) % 3`.
   * Update `next[newR]` with the best possible sum.
4. Assign `dp = next`.

Final answer = `dp[0]`.

## Algorithm

1. Initialize DP array: `{0, NEG, NEG}`.
2. For each number in `nums`, update DP transitions.
3. Return `dp[0]` (or 0 if negative, but DP ensures correctness).

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int maxSumDivThree(int[] nums) {
        int NEG = Integer.MIN_VALUE / 4;
        int[] dp = new int[]{0, NEG, NEG};

        for (int num : nums) {
            int[] next = dp.clone();
            int r = num % 3;
            for (int j = 0; j < 3; j++) {
                if (dp[j] != NEG) {
                    int newR = (j + r) % 3;
                    next[newR] = Math.max(next[newR], dp[j] + num);
                }
            }
            dp = next;
        }
        return Math.max(0, dp[0]);
    }
}
```

## Notes

* Elegant DP based on modulo states.
* Only 3 states make it extremely efficient.
* Works for large arrays since operations are constant per element.
