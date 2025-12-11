# LeetCode 3577 — Count the Number of Computer Unlocking Permutations

**Difficulty:** Medium
**Tags:** Permutations, Combinatorics, Greedy

## Problem

You are given an array `complexity` of length `n`. There are `n` computers labeled `0..n-1`. Computer `0` is already unlocked. For `i > 0`, you may unlock computer `i` using any previously unlocked computer `j` that satisfies:

* `j < i` and
* `complexity[j] < complexity[i]`.

A permutation of `[0,1,...,n-1]` represents an order in which computers are unlocked (the permutation lists labels in unlock order). Return the **number of valid permutations** consistent with the unlocking rule, modulo `10^9 + 7`.

## Key Idea

* If the value at index `0` is not the **unique minimum** of the array, there is no valid global unlocking order that starts from computer `0` alone — return `0`.
* If `complexity[0]` is the unique minimum, then every permutation of the remaining `n-1` computers produces a valid unlock order. Therefore the total number of valid permutations equals `(n-1)!` modulo `10^9+7`.

The provided implementation checks these conditions and computes the factorial `(n-1)!` in a modular-safe way.

## Algorithm (step-by-step)

1. Find the minimum value `minVal` in `complexity` and count how many times it appears (`cntMin`).
2. If `complexity[0] != minVal` or `cntMin > 1`, return `0`.
3. Otherwise compute `ans = ∏_{i=2}^{n-1} i` modulo `1e9+7` (this equals `(n-1)!`).
4. Return `ans`.

## Time & Space Complexity

| Metric | Value                                                                    |
| ------ | ------------------------------------------------------------------------ |
| Time   | **O(n)** — single pass to find min/count plus O(n) to compute factorial. |
| Space  | **O(1)** — constant extra space.                                         |

## Code

```java
class Solution {
    private static final long MOD = 1_000_000_007L;
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        long minVal = Long.MAX_VALUE;
        int cntMin = 0;
        for (int v : complexity) {
            if (v < minVal) {
                minVal = v;
                cntMin = 1;
            } else if (v == minVal) {
                cntMin++;
            }
        }
        if (complexity[0] != minVal || cntMin > 1) {
            return 0;
        }
        long ans = 1L;
        for (int i = 2; i <= n - 1; i++) {
            ans = (ans * i) % MOD;
        }
        return (int) ans;
    }
}
```

## Notes

* The solution relies on the combinatorial observation that if computer `0` holds the unique minimum complexity, every ordering of the other computers is feasible because each later computer can always find some earlier computer with strictly smaller complexity to unlock it.
* If the minimum complexity appears more than once, or the unique minimum is not at index `0`, no valid unlocking permutation starting from only computer `0` is possible under the given rules.
* Use `long` and modulus during factorial computation to avoid overflow.
