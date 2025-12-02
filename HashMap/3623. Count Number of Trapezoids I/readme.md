# LeetCode 3623 — Count Number of Trapezoids I

**Difficulty:** Medium
**Tags:** Geometry, Combinatorics, HashMap, Counting

## Problem

You're given a list of 2D points. A **trapezoid** is formed when you pick:

* Two points with the **same y-coordinate** (forming the top edge)
* Two points with another **same y-coordinate** (forming the bottom edge)
* And the y-levels of the two edges must be **different**.

You need to count how many such trapezoids can be formed.
Return the count modulo **1,000,000,007**.

## Key Idea

A trapezoid is defined by choosing:

* A pair of points on some y-level A
* A pair of points on some other y-level B

If a level has `p` points, it contributes:

```
C(p, 2) = p * (p - 1) / 2   // number of ways to pick 2 points on that horizontal line
```

Call this value `edgeCount` for that y-level.

We now want to count all combinations:

```
edges(level1) × edges(level2)
```

Summed over all unordered pairs of distinct y-levels.

We maintain:

* `sum` → running total of edgeCounts so far
* For each new level with edgeCount `e`:

  * All previous trapezoids involving this level = `e × sum`
  * Then update `sum += e`

This ensures every pair of y-levels is counted exactly once.

## Algorithm

1. Count how many points exist per y-coordinate using a HashMap.
2. For each distinct y-level:

   * Compute `edge = p * (p - 1) / 2`.
   * Add `edge * sum` to the answer.
   * Update `sum += edge`.
3. Return the final answer modulo `1e9+7`.

## Time & Space Complexity

| Metric | Value                              |
| ------ | ---------------------------------- |
| Time   | **O(n)** to count + iterate levels |
| Space  | **O(n)** for the HashMap           |

## Code

```java
class Solution {
    public int countTrapezoids(int[][] points) {
        Map<Integer, Integer> pointNum = new HashMap<>();
        final int mod = 1000000007;
        long ans = 0;
        long sum = 0;

        for (int[] point : points) {
            pointNum.put(point[1], pointNum.getOrDefault(point[1], 0) + 1);
        }

        for (int pNum : pointNum.values()) {
            long edge = ((long) pNum * (pNum - 1)) / 2;
            ans = (ans + edge * sum) % mod;
            sum = (sum + edge) % mod;
        }

        return (int) ans;
    }
}
```

## Notes

* Order of iterating y-levels doesn't matter since the formula handles pair counting correctly.
* A y-level with fewer than 2 points contributes 0 and safely skips itself.
* Efficient because only aggregated counts matter — actual x-coordinates are irrelevant.
