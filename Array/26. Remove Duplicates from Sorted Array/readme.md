# Remove Duplicates from Sorted Array — Java Solution

This repository contains a Java implementation for the **Remove Duplicates from Sorted Array** problem.

## Problem

Given a **sorted** integer array `nums`, remove the duplicates **in-place** such that each unique element appears only once.
Return the new length `k`.

### Constraints

* Do not allocate extra space for another array
* Must modify the input array in place

## Approach

This solution uses a 2-pointer technique:

* `r` → last unique element index (slow pointer)
* `f` → scanning pointer (fast pointer)

For each element, if the next item is different → move slow pointer and write updated value.

### Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        int r = 0, f = 1;
        while(f < nums.length){
            if(nums[r] != nums[f]){
                r++;
                nums[r] = nums[f];
            } else {
                f++;
            }
        }
        return r + 1;
    }
}
```

## Example

Input:

```
nums = [0,0,1,1,1,2,2,3,3,4]
```

Output:

```
5  (because the array becomes [0,1,2,3,4,...])
```
