# LeetCode 1351 - Count Negative Numbers in a Sorted Matrix

**Difficulty:** Easy
**Tags:** Matrix, Binary Search, Two Pointers

## Problem

You are given an `m x n` matrix `grid` where each row is sorted in **non-increasing** order.

Your task: **count the number of negative numbers in the matrix**.

## Key Idea

Because each row is sorted from **largest → smallest**, once a negative number appears in a row, **everything to the right is also negative**.

We use a **top-right pointer approach**:

* Start at `(row = 0, col = n - 1)`.
* If the current value is **negative**, then everything below it in that column is also negative → add `(m - row)` to the count, and move left.
* If the value is **non-negative**, move down.

This gives **O(m + n)** time.

## Algorithm

1. Initialize row at `0`, column at `n - 1`.
2. While `row < m` and `col >= 0`:

   * If `grid[row][col] < 0`:

     * Add `(m - row)` to count.
     * Move left (`col--`).
   * Else:

     * Move down (`row++`).
3. Return the count.

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

## Notes

* Uses monotonic property of sorted rows.
* More efficient than checking every element.
