LeetCode #474 — Difficulty: Medium
Tags: Dynamic Programming, 0/1 Knapsack, String Counting

# Ones and Zeroes — Java Solution

## Problem

You are given an array of binary strings `strs` and two integers `m` and `n`.

Each string can be used **at most once**.

Find the maximum number of strings that can be formed using at most `m` zeros and `n` ones.


## Key Idea

This is a **2D 0/1 Knapsack** problem.

* Each string consumes some count of zeros and ones.
* Capacity: `m` zeros, `n` ones.
* Value: +1 per string chosen.

We build a 2D DP table `dp[i][j]` = max number of strings using at most `i` zeros and `j` ones.

## Algorithm

1. Initialize `dp[m+1][n+1]` with all zeros.
2. For each string `s` in `strs`:

   * count its `zeros` and `ones`
3. Iterate **backwards** through dp:

   ```java
   for (i = m; i >= zeros; i--)
       for (j = n; j >= ones; j--)
           dp[i][j] = max(dp[i][j], dp[i - zeros][j - ones] + 1);
   ```
4. Return `dp[m][n]`.

Reverse iteration ensures each string is used **once**, mimicking the 0/1 property.

## Complexity

| Metric | Value                    |
| ------ | ------------------------ |
| Time   | **O(len(strs) * m * n)** |
| Space  | **O(m * n)**             |
