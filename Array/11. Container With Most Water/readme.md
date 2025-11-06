# Container With Most Water — Java Solution

This repository contains a Java implementation for the **Container With Most Water** problem.

## Problem

Given an integer array `height` where each element represents the height of a vertical line at that index:

Pick **2 lines** such that together with the x-axis they form a container that holds the **most water**.

Return the **maximum area**.

## Approach

Use **two pointers**:

* Left pointer starts at `0`
* Right pointer starts at `n - 1`

At every step:

* compute area = (right - left) * min(height[left], height[right])
* move the pointer with the **smaller** height (because only that can potentially get a taller wall)

### Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int maxArea(int[] height) {
        int L = 0;
        int R = height.length - 1;
        int maxW = 0;

        while (L < R) {
            int H = Math.min(height[L], height[R]);
            int width = R - L;
            maxW = Math.max(maxW, H * width);

            if (height[L] < height[R]) {
                L++;
            } else {
                R--;
            }
        }
        return maxW;
    }
}
```
