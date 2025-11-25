# LeetCode 1015 — Smallest Integer Divisible by K

**Difficulty:** Medium
**Tags:** Math, Number Theory, Modulo

## Problem

Given a positive integer `K`, return the **length** of the smallest positive integer consisting only of the digit `'1'` (i.e., `1, 11, 111, ...`) that is divisible by `K`.

If no such number exists, return **-1**.

A number made only of `'1'` digits is called a **repunit**.

## Key Idea

A repunit of length `N` can be represented iteratively:

```
R_N = (R_{N-1} * 10 + 1)
```

To avoid overflow, we only store `R_N % K`.

Observation:

* If `K` is divisible by **2** or **5**, the answer is automatically **-1**, because a number containing only 1's cannot end with 0 or 5.
* Otherwise, by the pigeonhole principle, the answer will be found within `K` iterations.

The loop computes prefix mod values until a remainder becomes `0`.

## Algorithm

1. Initialize `rem = 0`.
2. Loop `N` from `1` to `K`:

   * Update: `rem = (rem * 10 + 1) % K`.
   * If `rem == 0`, return `N`.
3. If no remainder becomes zero, return `-1`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(K)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int smallestRepunitDivByK(int K) {
        int rem = 0;
        for (int N = 1; N <= K; N++) {
            rem = (rem * 10 + 1) % K;
            if (rem == 0) {
                return N;
            }
        }
        return -1;
    }
}
```

## Notes

* If `K % 2 == 0` or `K % 5 == 0`, return `-1` immediately (optimisation).
* Remainders cycle within range `[0, K-1]`, so searching beyond `K` steps is unnecessary.
