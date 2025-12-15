# LeetCode 2110 — Number of Smooth Descent Periods of a Stock

**Difficulty:** Medium
**Tags:** Array, Math, Dynamic Programming

## Problem

You are given an integer array `prices` where `prices[i]` is the stock price on day `i`.

A **smooth descent period** is a contiguous subarray of one or more days such that:

* For every day after the first, the price is **exactly 1 less** than the previous day.
* A single day always counts as a smooth descent period.

Return the **total number of smooth descent periods**.

## Key Idea

The array can be divided into **maximal descending segments** where:

```
prices[i-1] - prices[i] == 1
```

For a descending segment of length `L`, the number of valid smooth descent subarrays inside it is:

```
L * (L + 1) / 2
```

This formula counts:

* all single-day periods
* all longer contiguous subarrays inside the segment

So we:

* scan the array
* maintain the length of the current descending segment
* whenever the descent breaks, add the contribution of the previous segment

## Algorithm

1. Initialize `count = 1` to track the current descending segment length.
2. Traverse the array from index `1` to `n-1`:

   * If `prices[i-1] - prices[i] == 1`, increment `count`.
   * Otherwise:

     * Add `count * (count + 1) / 2` to the answer.
     * Reset `count = 1`.
3. After the loop, add the contribution of the last segment.
4. Return the answer.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public long getDescentPeriods(int[] prices) {
        long ans = 0;
        int count = 1;
        int n = prices.length;
        
        for (int i = 1; i < n; i++) {
            if (prices[i - 1] - prices[i] == 1) {
                count++;
            } else {
                ans += (1L * count * (count + 1)) / 2;
                count = 1;
            }
        }
        ans += (1L * count * (count + 1)) / 2;
        return ans;
    }
}
```

## Example Walkthrough

For `prices = [3,2,1,4]`:

* Segment `[3,2,1]` → length = 3 → contributes `3*4/2 = 6`
* Segment `[4]` → length = 1 → contributes `1`

Total = `6 + 1 = 7`

## Notes

* Using the arithmetic series formula avoids adding at every step.
* This version is mathematically clean and efficient.
* Use `long` to prevent overflow for large input sizes.
