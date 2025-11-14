# LeetCode 704 — Binary Search

**Difficulty:** Easy
**Tags:** Binary Search, Array

# Binary Search — Java Solution

## Problem

Given a **sorted** integer array `nums` and an integer `target`, return the **index** of `target` if it exists. Otherwise, return **-1**.

You must use an algorithm with **O(log n)** time complexity.


## Key Idea

This is the classic **binary search** algorithm.

We maintain two pointers:

* `L` → start of search range
* `R` → end of search range

At each step:

1. Compute middle index `mid = L + (R - L) / 2` (avoids overflow).
2. If `nums[mid] == target` → found.
3. If `nums[mid] > target` → discard right half (`R = mid - 1`).
4. If `nums[mid] < target` → discard left half (`L = mid + 1`).

Repeat until `L > R`.


## Algorithm

1. Set `L = 0`, `R = n - 1`.
2. While `L <= R`:

   * Compute `mid`.
   * Compare value at `mid` with `target`.
3. If found → return `mid`.
4. If loop ends → return `-1`.

## Time & Space Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(log n)** |
| Space  | **O(1)**     |


## Code

```java
class Solution {
    public int search(int[] nums, int target) {
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
        return -1;
    }
}
```

## Notes

* Works only because the array is **sorted**.
* Efficient and commonly used in search-based problems.
