# LeetCode #2536 — Increment Submatrices by One

**Difficulty:** Medium
**Tags:** Difference Array, 2D Range Update, Prefix Sum


# Increment Submatrices by One — Java Solution

## Problem

You are given an integer `n` and a list of queries. Each query represents a submatrix defined by:

```
[r1, c1, r2, c2]
```

For each query, every cell inside the submatrix `(r1 ≤ r ≤ r2, c1 ≤ c ≤ c2)` must be incremented by **1**.

Return the final `n x n` matrix after applying all increments.


## Key Idea

A naive approach (incrementing all cells in every query) takes **O(q * n²)** — too slow.

Instead, we use a **2D difference array (row‑wise)** to perform range increments in **O(1)** per row.

### Observation

For each query:

* On every affected row `r ∈ [r1, r2]`, we increment columns `c1..c2`.
* Using a 1D diff array per row:

  * `diff[r][c1] += 1`
  * `diff[r][c2 + 1] -= 1` (if within bounds)

Later, prefix-sum each row to reconstruct final values.

This reduces complexity to **O(n² + q·n)**.


## Algorithm

1. Create `diff[n][n+1]` initialized to `0`.
2. For each query `[r1, c1, r2, c2]`:

   * For each row `r` from `r1` to `r2`:

     * `diff[r][c1] += 1`
     * If `c2 + 1 < n`: `diff[r][c2+1] -= 1`
3. Build final matrix:

   * For each row, compute prefix sums.
4. Return the resulting matrix.

## Complexity

| Metric | Value           |
| ------ | --------------- |
| Time   | **O(n² + q·n)** |
| Space  | **O(n²)**       |
