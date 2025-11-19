# LeetCode #2154 — Difficulty: Easy

**Tags:** Array, Hashing, Simulation

# Keep Multiplying Found Values by Two — Java Solution (HashSet Version)

## Problem

You are given an integer array `nums` and an integer `original`.

While `original` exists in the array, keep doubling it:

* If `original` is in `nums` → `original *= 2`
* Stop when it no longer appears in the array.

Return the final value of `original`.

## Key Idea

Use a **HashSet** to achieve fast O(1) lookup.

* Insert all values of `nums` into a HashSet.
* While the set contains `original`, double it.
* Return the final value.

## Algorithm

1. Create a HashSet and store all numbers.
2. Loop: While the set contains `original`, set `original *= 2`.
3. Return `original`.

## Complexity

| Metric | Value                                 |
| ------ | ------------------------------------- |
| Time   | **O(n)** — building the set + lookups |
| Space  | **O(n)** — HashSet storage            |

## Code

```java
class Solution {
    public int findFinalValue(int[] nums, int original) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            set.add(num);
        }
        while(set.contains(original)){
            original *= 2;
        }
        return original;
    }
}
```
