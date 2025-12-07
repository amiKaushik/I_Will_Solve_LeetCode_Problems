# LeetCode 3578 — Count Partitions With Max–Min Difference at Most K

**Difficulty:** Medium
**Tags:** Sliding Window, DP, Prefix Sum, TreeMap, Two Pointers

## Problem

You are given an integer array `nums` and an integer `k`.
You must count how many ways you can partition the array into one or more **non-empty contiguous segments**, such that **for every segment**:

```
max(segment) − min(segment) ≤ k
```

Return the number of valid partitions modulo `1e9+7`.

## Key Idea

This problem combines:

* A **sliding window** that maintains valid segments with `max - min ≤ k`
* A **DP formulation** where:

  * `dp[i]` = number of ways to partition prefix `nums[0..i-1]`
  * `prefix[i] = dp[0] + dp[1] + ... + dp[i]`

We maintain a window `[j..i]` such that:

```
max(nums[j..i]) - min(nums[j..i]) ≤ k
```

If window becomes invalid, we increment `j` while maintaining counts using a `TreeMap` (which tracks min and max in the window).

Then, to form a valid last segment ending at `i`, the previous cut can be anywhere from index `j-1` to `i-1`.
So:

```
dp[i+1] = prefix[i] - prefix[j-1]
```

(with modulo correction)

This ensures all partitions counted end with a *valid* segment.

## Algorithm

1. Initialize:

   * `dp[0] = 1` (empty prefix has 1 way)
   * `prefix[0] = 1`
   * Sliding window start pointer `j = 0`
   * TreeMap `cnt` to track frequencies of values in window
2. For each index `i` from `0` to `n-1`:

   * Add `nums[i]` to TreeMap
   * While window invalid (`max - min > k`):

     * Remove `nums[j]` from TreeMap
     * Increment `j`
   * Compute:

     ```
     dp[i+1] = prefix[i] - prefix[j-1]
     ```

     (use +mod to avoid negatives)
   * Update:

     ```
     prefix[i+1] = prefix[i] + dp[i+1]
     ```
3. Answer is `dp[n]`.

## Time & Space Complexity

| Metric | Value                                           |
| ------ | ----------------------------------------------- |
| Time   | **O(n log n)** (TreeMap operations per element) |
| Space  | **O(n)** for dp + prefix                        |

## Code

```java
class Solution {
    public int countPartitions(int[] nums, int k) {
        int n = nums.length;
        long mod = (long) 1e9 + 7;
        long[] dp = new long[n + 1];
        long[] prefix = new long[n + 1];
        TreeMap<Integer, Integer> cnt = new TreeMap<>();

        dp[0] = 1;
        prefix[0] = 1;

        for (int i = 0, j = 0; i < n; i++) {
            cnt.put(nums[i], cnt.getOrDefault(nums[i], 0) + 1);

            while (j <= i && cnt.lastKey() - cnt.firstKey() > k) {
                cnt.put(nums[j], cnt.get(nums[j]) - 1);
                if (cnt.get(nums[j]) == 0) cnt.remove(nums[j]);
                j++;
            }

            dp[i + 1] = (prefix[i] - (j > 0 ? prefix[j - 1] : 0) + mod) % mod;
            prefix[i + 1] = (prefix[i] + dp[i + 1]) % mod;
        }

        return (int) dp[n];
    }
}
```

## Notes

* TreeMap allows efficient tracking of **current window min & max**.
* Removing invalid prefixes ensures segments always satisfy the constraint.
* Prefix-sum DP trick reduces a potentially O(n²) DP to O(n log n).
* This is a textbook example of combining **sliding window + DP** effectively.
