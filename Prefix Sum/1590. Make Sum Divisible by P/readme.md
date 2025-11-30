# LeetCode 1590 — Make Sum Divisible by P

**Difficulty:** Medium
**Tags:** Prefix Sum, HashMap, Modulo, Sliding Window Logic

## Problem

You are given an array `nums` and an integer `p`.
You want the **sum of the entire array** to be divisible by `p`. You are allowed to **remove exactly one subarray** (possibly empty) to achieve this.

Return the **minimum length** of such a subarray, or `-1` if impossible.

## Key Idea

Let:

```
S = total sum of nums
T = S % p
```

If `T == 0`, array is already divisible → answer = `0`.

Otherwise, we need to remove a subarray whose sum % p = T.

Using prefix sums:

```
prefix[i] = sum(nums[0..i])
```

A subarray `(l+1 .. r)` has sum divisible by `p` difference equal to `T` iff:

```
prefix[r] % p  -  prefix[l] % p  ≡ T (mod p)
```

Rearranging:

```
prefix[l] % p ≡ prefix[r] % p - T (mod p)
```

So for each `r`, we look for a previous index `l` whose remainder matches the required value.

We store prefix remainders in a HashMap:
`map[remainder] = latest index where this remainder occurred`.

## Algorithm

1. Compute `total % p` → call it `target`.
2. If `target == 0`, return `0`.
3. Initialize map with `{0 → -1}` (prefix before array).
4. Maintain running prefix sum modulo `p`.
5. For index `i`:

   * compute `curr = prefix % p`
   * compute `need = (curr - target + p) % p`
   * if `need` exists in map, update minimum subarray length
   * store/update `map[curr] = i`
6. If no valid subarray found, return `-1`.

## Time & Space Complexity

| Metric | Value                                        |
| ------ | -------------------------------------------- |
| Time   | **O(n)**                                     |
| Space  | **O(n)** (HashMap storing prefix remainders) |

## Code

```java
class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        long total = 0L;
        for (int num : nums) total += num;
        int target = (int)(total % p);
        if (target == 0) return 0;

        Map<Integer, Integer> position = new HashMap<>();
        position.put(0, -1);
        int ans = n;
        long currSum = 0L;

        for (int i = 0; i < n; i++) {
            currSum += nums[i];
            int curr = (int)(currSum % p);
            int need = curr - target;
            if (need < 0) need += p;

            Integer prevIndex = position.get(need);
            if (prevIndex != null) {
                ans = Math.min(ans, i - prevIndex);
            }
            position.put(curr, i);
        }
        return ans == n ? -1 : ans;
    }
}
```

## Notes

* Uses modulo arithmetic to convert a subarray-sum problem into prefix-remainder matching.
* The HashMap ensures each remainder is found in O(1) time.
* Ensures the removed subarray is minimal while satisfying divisibility condition.
