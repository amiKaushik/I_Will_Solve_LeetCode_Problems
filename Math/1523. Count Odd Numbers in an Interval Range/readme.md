# LeetCode 1523 — Count Odd Numbers in an Interval Range

**Difficulty:** Easy
**Tags:** Math, Counting

## Problem

Given two integers `low` and `high`, return the **number of odd integers** between them (inclusive).

## Key Idea

Let:

```
L = high - low + 1   // total numbers in the range
```

* If `L` is even → exactly `L/2` numbers are odd.
* If `L` is odd:

  * If `low` is odd → there is **one extra odd**, so result = `L/2 + 1`.
  * Else → result = `L/2`.

This works because odds and evens alternate.

## Algorithm

1. Compute total length `L = high - low + 1`.
2. If `L` is even → return `L/2`.
3. If `L` is odd:

   * If `low` is odd → return `L/2 + 1`.
   * Else → return `L/2`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(1)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int countOdds(int low, int high) {
        int L = high - low + 1;
        if (L % 2 == 0) {
            return L / 2;
        }
        if (low % 2 == 1) {
            return L / 2 + 1;
        } else {
            return L / 2;
        }
    }
}
```

## Notes

* Constant-time solution with simple parity reasoning.
* A commonly known alternative formula: `(high + 1) / 2 - low / 2`, but the above reasoning is cleaner conceptually.
