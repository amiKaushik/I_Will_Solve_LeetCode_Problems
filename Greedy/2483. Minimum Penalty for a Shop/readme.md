# LeetCode 2483 — Minimum Penalty for a Shop

**Difficulty:** Medium
**Tags:** Prefix Sum, Greedy, String

## Problem

You are given a string `customers` where:

* `'Y'` means customers arrive at that hour
* `'N'` means no customers arrive

If the shop closes at hour `j`:

* For every hour `< j` with `'N'` → penalty +1 (shop open but no customers)
* For every hour `>= j` with `'Y'` → penalty +1 (shop closed but customers come)

Return the **earliest hour** `j` that results in the **minimum total penalty**.

## Key Idea

This is a **prefix-based greedy optimization** problem.

Instead of recomputing penalty for every possible closing time, observe:

* Initially (closing at time 0), penalty = total number of `'Y'`
* Moving closing time forward by 1 hour:

  * If current char is `'Y'`, penalty **decreases by 1** (shop stays open for a customer)
  * If current char is `'N'`, penalty **increases by 1** (shop open but no customer)

So we can track a **running penalty delta** and record the earliest time where penalty is minimized.

## Algorithm

1. Initialize:

   * `currPenalty = 0`
   * `minPenalty = 0`
   * `bestTime = 0`
2. Traverse the string from left to right:

   * If `customers[i] == 'Y'`, do `currPenalty--`
   * Else (`'N'`), do `currPenalty++`
   * If `currPenalty < minPenalty`:

     * Update `minPenalty = currPenalty`
     * Update `bestTime = i + 1`
3. Return `bestTime`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int bestClosingTime(String customers) {
        int minPenalty = 0;
        int currPenalty = 0;
        int time = 0;

        for (int i = 0; i < customers.length(); i++) {
            char ch = customers.charAt(i);
            if (ch == 'Y') {
                currPenalty--;
            } else {
                currPenalty++;
            }

            if (minPenalty > currPenalty) {
                minPenalty = currPenalty;
                time = i + 1;
            }
        }
        return time;
    }
}
```

## Example Walkthrough

For `customers = "YYNY"`:

* Start at time 0 → penalty baseline
* Track changes:

  * `'Y'` → penalty -1
  * `'Y'` → penalty -2
  * `'N'` → penalty -1
  * `'Y'` → penalty -2

Minimum penalty occurs at **time = 2** (earliest).

## Notes

* This solution is equivalent to finding the **minimum prefix sum**.
* Returning the earliest index ensures correctness when multiple answers exist.
* Very common interview pattern: *minimize cost by prefix traversal*.
