# LeetCode 1925 — Count Square Sum Triples

**Difficulty:** Easy
**Tags:** Math, Two Pointers, Pythagorean Triples

## Problem

You are given an integer `n`. A **square triple** is a triple of positive integers `(a, b, c)` such that:

```
a² + b² = c²
```

with all `1 ≤ a, b, c ≤ n`.

Return the **number of such triples**.

Note: The triples `(a, b, c)` and `(b, a, c)` are considered **different**.

## Key Idea

For each possible value of `c` (the hypotenuse), we want to count how many pairs `(a, b)` satisfy:

```
a² + b² = c²
```

with `1 ≤ a < b < c`.

We treat this like a **two-pointer** search:

* Fix `c`
* Use `a = 1`, `b = c - 1`
* Compute `a² + b²` and compare with `c²`:

  * If equal → we found one pair; increment answer by **2** (because `(a,b,c)` and `(b,a,c)` are distinct)
  * If sum < c² → increase `a`
  * If sum > c² → decrease `b`

This avoids checking all `(a, b)` pairs individually, reducing time complexity.

## Algorithm

1. Initialize answer = 0.
2. For `c` from 1 to `n`:

   * Set two pointers: `a = 1`, `b = c - 1`.
   * While `a < b`:

     * Compute `s = a² + b²`.
     * If `s == c²`:

       * Add 2 to answer.
       * Move both pointers inward.
     * Else if `s < c²` → increase `a`.
     * Else → decrease `b`.
3. Return the final answer.

## Time & Space Complexity

| Metric | Value     |
| ------ | --------- |
| Time   | **O(n²)** |
| Space  | **O(1)**  |

## Code

```java
class Solution {
    public int countTriples(int n) {
        int ans = 0;
        for (int c = 1; c <= n; c++) {
            int a = 1, b = c - 1, c2 = c * c;
            while (a < b) {
                int s = a * a + b * b;
                if (s == c2) {
                    ans += 2;
                    a++;
                    b--;
                } else if (s < c2) {
                    a++;
                } else {
                    b--;
                }
            }
        }
        return ans;
    }
}
```

## Notes

* Using two pointers avoids a triple nested loop.
* Triples `(a, b, c)` and `(b, a, c)` are both counted — hence the `+2`.
* Classic Pythagorean triple enumeration with constraints.
