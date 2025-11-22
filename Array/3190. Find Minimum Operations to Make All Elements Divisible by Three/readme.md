# LeetCode 3190 — Find Minimum Operations to Make All Elements Divisible by Three

**Difficulty:** Easy
**Tags:** Array, Math, Modulo

## Problem

Given an integer array `nums`, you must perform the **minimum number of operations** so that **every element becomes divisible by 3**.

In one operation, you may:

* **Increase** a number by 1, or
* **Decrease** a number by 1.

Return the **minimum total operations** required.

## Key Idea

Every number `x` has a remainder when divided by 3:

* If `x % 3 == 0` → already divisible → **0 operations**.
* If `x % 3 == 1` → either:

  * subtract 1 → 1 op, or
  * add 2 → 2 ops → choose **1**.
* If `x % 3 == 2` → either:

  * subtract 2 → 2 ops, or
  * add 1 → 1 op → choose **1**.

So operations needed for each `x` = `min(r, 3 - r)` where `r = x % 3`.

## Algorithm

1. Initialize `ops = 0`.
2. For each value `x` in `nums`:

   * Compute `r = x % 3`.
   * Add `min(r, 3 - r)` to total.
3. Return total `ops`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int minimumOperations(int[] nums) {
        int ops=0;
        for(int x:nums){
            int r=x%3;
            ops+=Math.min(r,3-r);
        }
        return ops;
    }
}
```

## Notes

* Uses simple math; no extra memory needed.
* Each element contributes independently to the final answer.
