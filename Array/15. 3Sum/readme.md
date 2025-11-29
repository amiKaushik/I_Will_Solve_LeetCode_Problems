# LeetCode 15 — 3Sum

**Difficulty:** Medium
**Tags:** Two Pointers, Sorting, Array

## Problem

Given an integer array `nums`, return **all unique triplets** `[a, b, c]` such that:

```
a + b + c = 0
```

and `i != j != k`.
Triplets must be returned **without duplicates**.

## Key Idea

* Sort the array first — this allows two-pointer scanning.
* For each index `i`, treat `nums[i]` as the first element.
* Use **two pointers** (`L` and `R`) to find pairs whose sum equals `-nums[i]`.
* Skip duplicates for:

  * the main index `i`
  * the left and right pointers

Sorting ensures duplicates are easy to detect and avoid.

## Algorithm

1. Sort the array.
2. Iterate `i` from `0` to `n-3`:

   * Skip if `nums[i] == nums[i-1]` (duplicate first element).
   * Set `target = -nums[i]`.
   * Run two-pointer search between `L = i+1` and `R = n-1`.
   * If sum matches:

     * Add the triplet.
     * Skip duplicates on both sides.
   * If sum < target → move `L++`.
   * If sum > target → move `R--`.
3. Return the list of all unique triplets.

## Time & Space Complexity

| Metric | Value                                  |
| ------ | -------------------------------------- |
| Time   | **O(n²)**                              |
| Space  | **O(1)** extra (excluding result list) |

## Code

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 3) return result;
        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int target = -nums[i];
            int L = i + 1, R = n - 1;

            while (L < R) {
                int sum = nums[L] + nums[R];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    int leftVal = nums[L];
                    int rightVal = nums[R];
                    while (L < R && nums[L] == leftVal) L++;
                    while (L < R && nums[R] == rightVal) R--;
                } else if (sum < target) {
                    L++;
                } else {
                    R--;
                }
            }
        }
        return result;
    }
}
```

## Notes

* Sorting is essential to skip duplicate combinations.
* Two-pointer approach reduces complexity from O(n³) → **O(n²)**.
* Works efficiently for large arrays.
