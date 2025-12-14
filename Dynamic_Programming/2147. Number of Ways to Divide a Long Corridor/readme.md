# LeetCode 2147 — Number of Ways to Divide a Long Corridor

**Difficulty:** Hard
**Tags:** Dynamic Programming, String, Memoization

## Problem

You are given a string `corridor` consisting of:

* `'S'` → seat
* `'P'` → plant

The corridor already has dividers at the **start** (before index 0) and **end** (after index n−1).

You may optionally add dividers between positions `i-1` and `i`. The goal is to divide the corridor into **non-overlapping sections** such that:

* Each section contains **exactly two seats**
* Each section may contain **any number of plants**

Return the **number of valid ways** to divide the corridor. Two ways are different if the divider placement differs.
Return the answer modulo `10^9 + 7`. If no valid division exists, return `0`.

## Key Idea

This is a **DP with state compression** problem.

At any position in the corridor, what matters is:

* `index` → current position
* `seats` → how many seats (`'S'`) are already placed in the **current section** (0, 1, or 2)

Let:

```
count(index, seats)
```

= number of ways to divide the corridor starting from `index`, given `seats` seats already collected in the current section.

### Base Case

* If `index == n` (end of corridor):

  * valid only if `seats == 2`

### Transition Rules

* If `seats < 2`:

  * We must continue extending the current section
  * If current char is `'S'`, increment `seats`
* If `seats == 2`:

  * The section is complete
  * If current char is:

    * `'S'` → must **start a new section** (otherwise seats > 2)
    * `'P'` → either:

      * close the section here
      * or keep extending (plants don’t affect seat count)

Memoization is used to avoid recomputation.

## Algorithm

1. Use recursion with memoization on `(index, seats)`.
2. Try all valid transitions based on current character and seat count.
3. Cache results to ensure O(n) complexity.
4. Start recursion from `count(0, 0)`.

## Time & Space Complexity

| Metric | Value                                          |
| ------ | ---------------------------------------------- |
| Time   | **O(n)** — each `(index, seats)` computed once |
| Space  | **O(n)** — recursion stack + memoization       |

## Code

```java
class Solution {
    private static final int MOD = 1_000_000_007;

    private int count(int index, int seats, String corridor,
                      Map<Pair<Integer, Integer>, Integer> cache) {
        if (index == corridor.length()) {
            return seats == 2 ? 1 : 0;
        }

        Pair<Integer, Integer> key = new Pair<>(index, seats);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        int result = 0;

        if (seats == 2) {
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, 1, corridor, cache);
            } else {
                result = (count(index + 1, 0, corridor, cache)
                        + count(index + 1, 2, corridor, cache)) % MOD;
            }
        } else {
            if (corridor.charAt(index) == 'S') {
                result = count(index + 1, seats + 1, corridor, cache);
            } else {
                result = count(index + 1, seats, corridor, cache);
            }
        }

        cache.put(key, result);
        return result;
    }

    public int numberOfWays(String corridor) {
        Map<Pair<Integer, Integer>, Integer> cache = new HashMap<>();
        return count(0, 0, corridor, cache);
    }
}
```

## Notes

* Each section must end **exactly** when 2 seats are accumulated.
* Plants act as flexible separators that allow multiple divider choices.
* This DP mirrors a real interview-style state machine problem.
* A greedy or simple counting approach fails due to branching choices.
