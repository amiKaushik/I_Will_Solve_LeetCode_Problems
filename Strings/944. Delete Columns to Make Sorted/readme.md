# LeetCode 944 — Delete Columns to Make Sorted

**Difficulty:** Easy
**Tags:** Array, String, Matrix Simulation

## Problem

You are given an array of strings `strs`, all of the same length.

Treat the strings as rows of a grid. A column is considered **sorted** if characters from top to bottom are in **non-decreasing lexicographical order**.

Your task is to **count how many columns must be deleted** so that all remaining columns are sorted.

## Key Idea

Each column can be checked **independently**.

For a fixed column `c`:

* Compare characters row by row
* If at any row `r`:

```
strs[r][c] < strs[r-1][c]
```

then the column is **not sorted** and must be deleted.

Once a column is found invalid, we stop checking further rows for that column.

## Algorithm

1. Let `rows = strs.length` and `cols = strs[0].length()`.
2. Initialize `deleteCount = 0`.
3. For each column `c` from `0` to `cols-1`:

   * For each row `r` from `1` to `rows-1`:

     * If `strs[r][c] < strs[r-1][c]`:

       * Increment `deleteCount`.
       * Break out of row loop.
4. Return `deleteCount`.

## Time & Space Complexity

| Metric | Value              |
| ------ | ------------------ |
| Time   | **O(rows × cols)** |
| Space  | **O(1)**           |

## Code

```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int rows = strs.length;
        int cols = strs[0].length();
        int deleteCount = 0;

        for (int c = 0; c < cols; c++) {
            for (int r = 1; r < rows; r++) {
                if (strs[r].charAt(c) < strs[r - 1].charAt(c)) {
                    deleteCount++;
                    break;
                }
            }
        }
        return deleteCount;
    }
}
```

## Example Walkthrough

For `strs = ["cba", "daf", "ghi"]`:

* Column 0 → `c, d, g` → sorted
* Column 1 → `b, a, h` → not sorted ❌
* Column 2 → `a, f, i` → sorted

Answer = **1**

## Notes

* Each column is evaluated independently.
* Early break optimizes unnecessary comparisons.
* Simple grid traversal problem — no advanced data structures required.
