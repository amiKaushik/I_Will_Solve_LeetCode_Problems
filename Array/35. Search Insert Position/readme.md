LeetCode #35 — Search Insert Position
Difficulty: Easy
Tags: Binary Search, Array

## Problem

Given a **sorted** array `nums` and an integer `target`, return the index where the target should be **inserted** to maintain sorted order.

If the target exists, return its index.

## Key Insight

Use **binary search**:

* If `target` is found → return its index
* Otherwise → return the position where it would be inserted, which is `L` after the loop

This gives an optimal **O(log n)** runtime.

## Why `mid = L + (R - L) / 2`?

This avoids **integer overflow** that can occur with `(L + R) / 2` when `L` and `R` are very large.

> For this specific LeetCode problem the array size is small, so overflow won’t happen — but using the safe formula is **good practice** and used in all professional binary search code.

## Algorithm

1. Initialize two pointers: `L = 0`, `R = n - 1`
2. While `L <= R`:

   * Compute `mid`
   * If `nums[mid] == target`, return `mid`
   * If `nums[mid] > target`, shrink right side
   * Else expand left side
3. When the loop ends, `L` is the correct position to insert the target.

## Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(log n)** |
| Space  | **O(1)**     |

## Code

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        
        while (L <= R) {
            int mid = L + (R - L) / 2;
            
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        
        return L;
    }
}
```
