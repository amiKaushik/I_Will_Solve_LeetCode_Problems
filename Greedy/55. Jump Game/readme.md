**LeetCode #55 — Difficulty: Medium**
**Tags:** Greedy, Array, Dynamic Programming

# Jump Game — Java Solution

## Problem

You are given an integer array `nums` where each element represents the **maximum jump length** from that position.

Return `true` if you can reach the **last index**, otherwise `false`.


## Intuition

This is a **greedy reachability** problem.

We track how far we can go (`maxReach`) as we iterate.
If we ever reach an index beyond `maxReach`, that means we got “stuck” and cannot proceed — so we return `false`.

If at any point `maxReach >= last index`, we can immediately return `true`.


## Algorithm (Logic-based)

1. Initialize `maxReach = 0`.
2. Loop through indices `i = 0 → n-1`:

   * If `i > maxReach`, we cannot reach this index → **return false**.
   * Update `maxReach = max(maxReach, i + nums[i])`.
   * If `maxReach >= n - 1`, we can already reach the end → **return true**.
3. If the loop finishes, return `true`.


## Code

```java
class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (i > maxReach) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }
        return true;
    }
}
```


## Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Example

```
nums = [2,3,1,1,4]

i=0 → maxReach=2  
i=1 → maxReach=max(2,1+3)=4 → already ≥ last index → return true
```

**We can reach the end.**
