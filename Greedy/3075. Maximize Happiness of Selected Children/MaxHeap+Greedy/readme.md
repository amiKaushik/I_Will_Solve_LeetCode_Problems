# LeetCode 3075 — Maximize Happiness of Selected Children (Priority Queue Approach)

**Difficulty:** Medium
**Tags:** Greedy, Heap, Priority Queue, Array

## Problem

You are given an integer array `happiness` of length `n` and an integer `k`.

There are `n` children standing in a queue. In each of the `k` turns:

* You select one child and add their current happiness to the total.
* The happiness of **all unselected children decreases by 1** (but not below 0).

Return the **maximum total happiness** after selecting exactly `k` children.

## Key Idea

This approach directly simulates the greedy choice:

* At every turn, pick the child with the **current maximum happiness**.
* Since happiness decreases by 1 after each selection, we track how many turns have passed using `turn`.
* A **max-heap (priority queue)** lets us always extract the child with the highest remaining happiness efficiently.

The effective happiness added at each step is:

```
max(0, currentMax - turn)
```

## Algorithm

1. Insert all happiness values into a **max heap**.
2. Initialize `turn = 0` and `maxHappiness = 0`.
3. While `k > 0` and heap is not empty:

   * Extract the maximum value from the heap.
   * Add `max(0, value - turn)` to the result.
   * Increment `turn`.
   * Decrement `k`.
4. Return the accumulated result.

## Time & Space Complexity

| Metric | Value                                              |
| ------ | -------------------------------------------------- |
| Time   | **O(n log n)** (heap construction + k extractions) |
| Space  | **O(n)** (priority queue storage)                  |

## Code

```java
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        PriorityQueue<Integer> hp = new PriorityQueue<>(Collections.reverseOrder());
        for (int x : happiness) {
            hp.add(x);
        }
        long maxHappiness = 0;
        int turn = 0;
        while (k > 0 && !hp.isEmpty()) {
            maxHappiness += Math.max(0, hp.poll() - turn);
            turn++;
            k--;
        }
        return maxHappiness;
    }
}
```

## Notes

* This method mirrors the problem statement closely by simulating each turn.
* Sorting is slightly more memory-efficient, but both approaches are valid.
* Priority Queue approach is useful when values are generated dynamically.
