# LeetCode 3381 - Maximum Subarray Sum With Length Divisible by K

**Difficulty:** Medium
**Tags:** Prefix Sum, Modulo, Array, Optimization

## Problem

Given an integer array `nums` and an integer `k`, return the **maximum subarray sum** among all subarrays whose **length is divisible by `k`**.

You may assume `nums` contains at least one element and `k >= 1`.

## Key Idea

A subarray from index `i` to `j` (1-based length `L = j - i + 1`) has length divisible by `k` iff `(j - i + 1) % k == 0`.

Using prefix sums `P[t] = sum(nums[0..t-1])`, the sum of subarray `(i..j)` equals `P[j+1] - P[i]`.

Length condition `(j - i + 1) % k == 0` is equivalent to `(j+1) % k == i % k`. So if two prefix indices have the same remainder modulo `k`, the subarray between them has length divisible by `k`.

Therefore, for each prefix index `a` (1-based), compute remainder `r = a % k` and:

* Maintain `minPref[r]` = minimum prefix sum observed at indices whose index % k == r.
* Candidate maximum subarray sum ending at `a` with valid length = `prefix[a] - minPref[r]`.
* Update `minPref[r]` if current prefix is smaller.

This yields an O(n) scan using O(k) extra space.

## Algorithm (step-by-step)

1. Let `pref` be running prefix sum (initially 0).  Use `a` to denote 1-based prefix index.
2. Initialize `minPref[0..k-1]` = +INF, but `minPref[0] = 0` (empty prefix at index 0).
3. For `a` from `1` to `n`:

   * `pref += nums[a-1]`.
   * `r = a % k`.
   * If `minPref[r]` is set, candidate = `pref - minPref[r]`; update answer if larger.
   * `minPref[r] = min(minPref[r], pref)`.
4. Return the best answer found.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(k)** |

## Code

```java
class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        final long INF = Long.MAX_VALUE / 4;
        long[] minPref = new long[k];
        for (int i = 0; i < k; i++) {
            minPref[i] = INF;
        }
        minPref[0] = 0L;
        long pref = 0L;
        long ans = Long.MIN_VALUE;

        for (int a = 1; a <= n; a++) {
            pref += nums[a - 1];
            int rem = a % k;
            if (minPref[rem] != INF) {
                long candidate = pref - minPref[rem];
                if (candidate > ans) {
                    ans = candidate;
                }
            }
            if (pref < minPref[rem]) {
                minPref[rem] = pref;
            }
        }
        return ans;
    }
}
```

## Notes

* Using 1-based prefix indices makes the remainder-check straightforward: two prefixes with the same `index % k` imply the subarray length between them is divisible by `k`.
* `minPref` stores the smallest prefix sum seen so far for each remainder — this maximizes `pref - minPref[r]`.
* Handles negative numbers naturally because we allow `minPref` to be any prior prefix sum.
* Be careful with `INF` and overflow; we used `Long.MAX_VALUE/4` as a safe sentinel.
