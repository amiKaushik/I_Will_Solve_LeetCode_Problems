# LeetCode 2141 — Maximum Running Time of N Computers

**Difficulty:** Hard
**Tags:** Binary Search, Greedy, Simulation, Math

## Problem

You have `n` computers and an array `batteries`, where each battery can power **only one** computer at a time for `batteries[i]` minutes.

You may swap batteries between computers at any time.

Return the **maximum number of minutes** all `n` computers can run **simultaneously**.

## Key Idea

This is a **binary search on answer** problem.

If we guess a running time `T` minutes, we check whether we can keep all `n` computers alive for `T` minutes:

* Each battery contributes `min(battery power, T)` to the total usable energy.
* If the total usable energy ≥ `n * T`, then `T` is feasible.

We binary search the value of `T` between `0` and `sum(batteries) / n`.

## Feasibility Check

For a candidate time `T`:

```
totalEnergy = Σ min(battery[i], T)
```

If `totalEnergy >= n * T`, then `T` is possible.

## Algorithm

1. Compute total battery power.
2. Binary search time `T` from `0` to `sum / n`.
3. For each mid `T`:

   * Compute total usable battery time.
   * If `>= n*T`, mark feasible and push search right.
   * Else move left.
4. Final `ans` is the largest feasible `T`.

## Time & Space Complexity

| Metric | Value                                             |
| ------ | ------------------------------------------------- |
| Time   | **O(m log(sum/n))**, where `m = batteries.length` |
| Space  | **O(1)**                                          |

## Code

```java
class Solution {
    public long maxRunTime(int n, int[] batteries) {
        long sumPower = 0;
        for (int power : batteries) {
            sumPower += power;
        }

        long left = 0L, right = sumPower / n, ans = 0L;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            long total = 0L;

            for (int power : batteries) {
                total += Math.min((long) power, mid);
                if (total >= (long) n * mid) break;
            }

            if (total >= (long) n * mid) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }
}
```

## Notes

* The feasibility check is greedy and optimal because swapping batteries removes ordering constraints.
* The upper bound `sum / n` is the absolute maximum time if battery distribution was perfect.
* Avoid overflow using `long` when computing `n * mid`.
