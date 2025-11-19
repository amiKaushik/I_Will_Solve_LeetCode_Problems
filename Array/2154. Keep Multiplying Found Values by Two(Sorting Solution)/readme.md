LeetCode #2154 — Difficulty: Easy
Tags: Array, Sorting

# Keep Multiplying Found Values by Two — Java Solution

## Problem

You are given an integer array `nums` and an integer `original`.

While `original` is found in the array, replace:

```
original = original * 2
```

Return the final value of `original` after it can no longer be found in the array.

## Approach — Sorting + Linear Scan

The simplest approach is:

1. **Sort** the array.
2. Traverse the sorted numbers.
3. Each time you encounter a number equal to `original`, multiply `original` by 2.
4. Continue scanning until the end.

Sorting ensures that if `original` appears multiple times, we process them in order.

> This is not the most optimal solution (a hash set solution runs in `O(n)`), but the sorting-based approach is clean and easy to reason about.

## Algorithm (Step-by-Step)

1. Sort `nums`.
2. For each `num` in `nums`:

   * If `num == original`, then update `original = original * 2`.
3. After processing all numbers, return `original`.

## Complexity

| Metric | Value                                      |
| ------ | ------------------------------------------ |
| Time   | **O(n log n)** — due to sorting            |
| Space  | **O(1)** — ignoring sort's internal memory |

## Code

```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Arrays.sort(nums);
        for (int num : nums) {
            if (original == num) {
                original *= 2;
            }
        }
        return original;
    }
}
```
