# LeetCode 3583 — Count Special Triplets

**Difficulty:** Medium
**Tags:** Counting, Frequency, Prefix, Hashing

## Problem

Given an integer array `nums`, a **special triplet** is a triplet of indices `(i, j, k)` such that:

```
0 ≤ i < j < k < n
nums[i] == nums[j] * 2
nums[k] == nums[j] * 2
```

Return the total number of special triplets modulo `10^9 + 7`.

## Key Idea

For each index `j` as the middle element, we need to count how many `i < j` have value `2 * nums[j]` and how many `k > j` have value `2 * nums[j]`.

If `left[x]` is the frequency of value `x` to the left of `j` and `right[x]` is the frequency of value `x` to the right of `j`, then for `target = 2 * nums[j]` the number of triplets with this `j` is:

```
left[target] * right[target]
```

Accumulate this for every `j`.

Use two frequency arrays/maps:

* `right` initialized with counts of all values
* `left` initially empty

Traverse `j` from left to right:

1. Decrement `right[nums[j]]` because `j` leaves the right side.
2. Compute `target = nums[j] * 2`. If `target` within bounds, add `left[target] * right[target]` to answer.
3. Increment `left[nums[j]]`.

## Algorithm

1. Build frequency array `right` counting every number in `nums`.
2. Initialize `left` as zeros.
3. For each index `j` from `0` to `n-1`:

   * `right[nums[j]]--`
   * `target = nums[j] * 2`
   * If `target <= MAX_VALUE` then `ans = (ans + left[target] * right[target]) % MOD`
   * `left[nums[j]]++`
4. Return `ans`.

## Time & Space Complexity

| Metric | Value                                                           |
| ------ | --------------------------------------------------------------- |
| Time   | **O(n)**                                                        |
| Space  | **O(MAX_VALUE)** where `MAX_VALUE = 2e5` (based on constraints) |

## Code

```java
class Solution {
    public int specialTriplets(int[] nums) {
        final int MAX = 200_000;
        final long MOD = 1_000_000_007L;
        int n = nums.length;
        long[] left = new long[MAX + 1];
        long[] right = new long[MAX + 1];

        for (int num : nums) {
            right[num]++;
        }
        long ans = 0L;
        for (int j = 0; j < n; j++) {
            int mid = nums[j];
            right[mid]--;
            int target = mid * 2;
            if (target <= MAX) {
                ans = (ans + (left[target] * right[target]) % MOD) % MOD;
            }
            left[mid]++;
        }
        return (int) ans;
    }
}
```

## Notes

* Using fixed-size arrays is fast and memory-efficient given the problem constraints (`nums[i] ≤ 10^5`, target can be up to `2×10^5`).
* Modular arithmetic is applied to avoid overflow.
* Works in linear time by maintaining running frequencies on both sides of the current middle index.

## Examples

* `nums = [6,3,6]` → output `1` (triplet `(0,1,2)`).
* `nums = [0,1,0,0]` → output `1`.
* `nums = [8,4,2,8,4]` → output `2`.
