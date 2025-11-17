# LeetCode 1437 — Check If All 1's Are at Least Length K Places Away

**Difficulty:** Easy
**Tags:** Array, Distance Checking

## Problem

You are given a binary array `nums` and an integer `k`.

Return **true** if every pair of `1`s in the array is separated by **at least `k` zeros** between them.

Otherwise, return **false**.

## Key Insight

We only need to track **the index of the previous 1**.

When we find a new `1` at index `i`:

* If this is the first `1`, simply store its index.
* Otherwise, check the distance:

  * Distance between two `1`s is: `i - last - 1`
  * If this distance is **less than k**, return `false`.

If the loop finishes without violation → return `true`.

## Algorithm

1. Set `last = -1` (no 1 seen yet).
2. Iterate over array:

   * If `nums[i] == 1`:

     * If `last != -1` and `i - last - 1 < k` → return `false`.
     * Update `last = i`.
3. If no violations, return `true`.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public boolean kLengthApart(int[] nums, int k) {
        int last = -1;  // store index of previous 1
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                if (last != -1 && i - last - 1 < k) {
                    return false;   // distance between 1s is less than k
                }
                last = i;  // update last seen '1'
            }
        }
        
        return true;
    }
}
```

## Example

### Input

```
nums = [1,0,0,0,1,0,0,1], k = 2
```

### Output

```
true
```

## Notes

* We do not need extra arrays or pointers.
* Only one integer variable is required.
* Efficient for large arrays (up to 100,000 length).
