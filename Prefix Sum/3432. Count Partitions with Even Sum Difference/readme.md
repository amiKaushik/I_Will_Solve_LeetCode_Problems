# LeetCode 3432 — Count Partitions with Even Sum Difference

**Difficulty:** Easy
**Tags:** Prefix, Math, Parity, Array

## Problem

You are given an integer array `nums` of length `n`.
A **partition** is an index `i` such that:

* left subarray = `nums[0..i]`
* right subarray = `nums[i+1..n-1]`
* both subarrays are **non-empty**

We must count how many partitions satisfy:

```
(leftSum − rightSum) is even
```

## Key Idea

Let:

```
total = sum(nums)
left = prefix sum at position i
right = total − left
```

We want:

```
(left − right) % 2 == 0
```

This simplifies:

```
(left − (total − left)) % 2 == 0
(2*left − total) % 2 == 0
```

Since `2*left` is always even:

```
(−total) % 2 == 0    →    total % 2 == 0
```

### Therefore,

If **total sum is even**, **every** valid partition works → answer = `n − 1`.
If **total sum is odd**, **none** works → answer = `0`.

This leads to a very simple solution.

## Algorithm

1. Compute the sum of all elements.
2. If sum is even → return `n − 1`.
3. Else → return `0`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int total = 0;
        for (int num : nums) {
            total += num;
        }
        if (total % 2 == 0) {
            return n - 1;
        } else {
            return 0;
        }
    }
}
```

## Notes

* Great example of **parity simplification** in prefix problems.
* Prefix sums were not even needed once the parity condition was reduced.
* Result depends **only** on whether the full sum is even or odd.
