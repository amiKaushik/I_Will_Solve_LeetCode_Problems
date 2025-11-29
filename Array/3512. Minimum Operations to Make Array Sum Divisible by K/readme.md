# LeetCode 3512 — Minimum Operations to Make Array Sum Divisible by K

**Difficulty:** Easy
**Tags:** Math, Modulo, Array

## Problem

You are given an integer array `nums` and an integer `k`.
You may perform operations (not explicitly defined because the only thing that matters is changing the sum). The goal is to make the **sum of the array divisible by `k`**.

Return the **minimum number of operations** required.

## Key Idea

No matter how operations are applied, the only thing that affects the result is the **total sum modulo `k`**.

Let:

```
sum = Σ nums[i]
```

If `sum % k = 0`, no operations are needed.
Else, the minimum number of operations required is simply the value of:

```
sum % k
```

because that's the exact difference needed to reach the next multiple of `k`.

## Algorithm

1. Compute the total sum of the array.
2. Return `sum % k`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int minOperations(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        return sum % k;
    }
}
```

## Notes

* Extremely straightforward modulo-based solution.
* The sum alone determines the number of operations — individual values don't matter.
