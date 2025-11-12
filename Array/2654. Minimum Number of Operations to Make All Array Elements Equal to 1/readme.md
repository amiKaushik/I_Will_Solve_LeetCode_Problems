LeetCode #2654 — Difficulty: Medium
Tags: Math, Number Theory, GCD, Array, Greedy

# Minimum Number of Operations to Make All Array Elements Equal to 1 — Java Solution

## Problem

You are given an integer array `nums`.

In one operation, you can select two **adjacent elements** `a` and `b` and replace one of them with `gcd(a, b)`.

Return the **minimum number of operations** required to make **all elements equal to 1**, or `-1` if it's impossible.


## Key Idea

This problem relies on the properties of **GCD (Greatest Common Divisor)**.

The main insight is:

* If the array already contains one or more `1`s, then we can use them to turn all other elements into `1` directly.
* If there are **no 1s**, we need to find a **subarray whose GCD becomes 1**, because once a single `1` is formed, we can spread it through the array.


## Logical Reasoning

1. **Count existing 1s:**

   * If any element is `1`, each operation can turn one non-1 into 1.
   * So total operations = `n - count(1)`.

2. **If no 1s exist:**

   * We must find the shortest subarray whose GCD = 1.
   * Once we find such a subarray, we can make one element become `1` in `(len - 1)` operations.
   * Then, we need `(n - 1)` additional operations to spread that `1` through the entire array.

3. **If no subarray gives GCD = 1:**

   * It is impossible → return `-1`.


## Complexity

| Metric | Value                                   |
| ------ | --------------------------------------- |
| Time   | **O(n²)** — check all subarrays for GCD |
| Space  | **O(1)** — only integer variables used  |


## Code

```java
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int countOnes = 0;

        for (int num : nums) {
            if (num == 1) countOnes++;
        }

        if (countOnes >= 1) {
            return n - countOnes;
        }

        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int g = nums[i];
            for (int j = i + 1; j < n; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    minLen = Math.min(minLen, j - i + 1);
                    break;
                }
            }
        }

        if (minLen == Integer.MAX_VALUE) {
            return -1;
        }

        return (minLen - 1) + (n - 1);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
```

## Example

```java
Input: nums = [2,6,3,4]
Output: 4
Explanation: The subarray [6,3,4] has GCD = 1, so total ops = (3 - 1) + (4 - 1) = 4.
```
