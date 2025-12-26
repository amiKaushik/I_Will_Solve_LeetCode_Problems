# LeetCode 3075 — Maximize Happiness of Selected Children

**Difficulty:** Medium
**Tags:** Greedy, Sorting, Array

## Problem

You are given an integer array `happiness` of length `n` and an integer `k`.

There are `n` children standing in a queue. In each of the `k` turns, you select one child:

* When a child is selected, their current happiness is added to the total.
* After each selection, the happiness of **all unselected children decreases by 1** (but never below 0).

Return the **maximum total happiness** achievable after selecting exactly `k` children.

## Key Idea

This is a **greedy + sorting** problem.

Observations:

* To maximize the sum, always pick the child with the **largest current happiness**.
* After each pick, the effective happiness of remaining children decreases by `1`.
* If we sort the array in ascending order and pick from the **largest values downward**, then on the `turn`-th pick (0-indexed), the effective happiness is:

```
max(0, happiness[i] - turn)
```

This greedy strategy is optimal because delaying the selection of a high happiness child only reduces its contribution.

## Algorithm

1. Sort the `happiness` array.
2. Initialize `turn = 0` and `maxHappiness = 0`.
3. Iterate from the end of the array (largest happiness) while `k > 0`:

   * Add `max(0, happiness[i] - turn)` to the result.
   * Increment `turn`.
   * Decrement `k`.
4. Return the accumulated result.

## Time & Space Complexity

| Metric | Value                                   |
| ------ | --------------------------------------- |
| Time   | **O(n log n)** due to sorting           |
| Space  | **O(1)** extra (ignoring sort overhead) |

## Code

```java
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);
        long maxHappiness = 0;
        int turn = 0;

        for (int i = n - 1; i >= 0 && k > 0; i--) {
            maxHappiness += Math.max(0, happiness[i] - turn);
            turn++;
            k--;
        }
        return maxHappiness;
    }
}
```

## Example Walkthrough

For `happiness = [1,2,3]`, `k = 2`:

* Sort → `[1,2,3]`
* Pick `3` (turn = 0) → gain `3`
* Pick `2 - 1 = 1` (turn = 1) → gain `1`

Total = **4**

## Notes

* Using `long` avoids overflow for large values.
* The `max(0, ...)` ensures happiness never becomes negative.
* Classic greedy pattern: **pick largest first, adjust by turn c
