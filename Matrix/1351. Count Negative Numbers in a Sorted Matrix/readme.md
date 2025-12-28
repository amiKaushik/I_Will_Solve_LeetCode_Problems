# LeetCode 1351 — Count Negative Numbers in a Sorted Matrix

**Difficulty:** Easy
**Tags:** Matrix, Two Pointers, Greedy

## Problem

You are given an `m × n` matrix `grid` where:

* Each row is sorted in **non-increasing** order.
* Each column is also sorted in **non-increasing** order.

Return the **total number of negative numbers** in the matrix.

## Key Idea

Because the matrix is sorted both row-wise and column-wise:

* If a value at position `(row, col)` is negative, then **all values below it in the same column are also negative**.
* If a value is non-negative, then all values to its **left in the same row are also non-negative**.

This allows a **two-pointer traversal** starting from the **top-right corner**.

## Algorithm

1. Start from the top-right cell `(row = 0, col = n - 1)`.
2. While `row < m` and `col >= 0`:

   * If `grid[row][col] < 0`:

     * All cells from `(row, col)` downwards are negative.
     * Add `(m - row)` to the count.
     * Move left: `col--`.
   * Else:

     * Move down: `row++`.
3. Return the final count.

## Time & Space Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(m + n)** |
| Space  | **O(1)**     |

## Code

```java
class Solution {
    public int countNegatives(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (grid[row][col] < 0) {
                count += (m - row);
                col--;
            } else {
                row++;
            }
        }
        return count;
    }
}
```

## Example Walkthrough

For:

```
[ 4,  3,  2, -1]
[ 3,  2,  1, -1]
[ 1,  1, -1, -2]
[-1, -1, -2, -3]
```

Traversal from top-right:

* `(0,3) = -1` → all elements below are negative → add `4`
* move left to `(0,2) = 2` → non-negative → move down
* `(1,2) = 1` → non-negative → move down
* `(2,2) = -1` → add `2`
* move left to `(2,1) = 1` → move down
* `(3,1) = -1` → add `1`
* move left to `(3,0) = -1` → add `1`

Total negatives = **8**.

## Notes

* Much faster than brute-force `O(m × n)` scanning.
* Works only because both rows and columns are sorted.
* Classic matrix traversal interview problem.
