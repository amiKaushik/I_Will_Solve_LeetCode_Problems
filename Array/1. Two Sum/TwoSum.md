# Two Sum --- Java Solution

This repository contains a Java implementation of the classic **Two
Sum** problem.

## Problem

Given an array of integers `nums` and an integer `target`, return
**indices** of the two numbers such that they add up to `target`.

You may assume that each input would have **exactly one solution**, and
you may not use the same element twice.

## Approach

This solution uses a `HashMap` to store previously visited numbers and
their indices.

For each number in the input array:

-   compute the complement = `target - nums[i]`
-   check if complement exists in the map
-   if yes → return the pair of indices
-   otherwise store the current number and its index in the map

Time & space complexity:

  Metric   Value
  -------- -----------------------
  Time     **O(n)** --- one pass
  Space    **O(n)** --- hash map

## Code

``` java
public int[] twoSum(int[] nums, int target) {
    HashMap<Integer,Integer> hsmp = new HashMap<>();
    int[] indices = new int[2];
    for(int i = 0; i < nums.length; i++){
        int complement = target - nums[i];
        if(hsmp.containsKey(complement)){
            indices[0] = hsmp.get(complement);
            indices[1] = i;
            return indices;
        }
        hsmp.put(nums[i], i);
    }
    return indices;
}
```

## Example

``` java
twoSum([2, 7, 11, 15], 9) // returns [0, 1]
```

## Notes

-   This is the optimal single-pass hash map solution.
-   Return order is `[indexOfComplement, currentIndex]`.
