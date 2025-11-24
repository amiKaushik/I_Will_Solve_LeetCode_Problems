# LeetCode 1018 — Binary Prefix Divisible By 5

**Difficulty:** Easy
**Tags:** Bit Manipulation, Prefix, Math

## Problem

You are given a binary array `nums`. For each prefix of the array (i.e., from index `0` to `i`), interpret it as a binary number.

Return a list where each element is:

* `true` if the binary prefix is divisible by **5**
* `false` otherwise

## Key Idea

Since binary numbers grow very fast, directly constructing the full number would overflow.

Instead, use the mathematical fact:

* If we only care about divisibility by 5, we can keep the **number modulo 5**.

Update rule for each new bit `num`:

```
new_val = (previous_val * 2 + num) % 5
```

Or using bit shift:

```
val = ((val << 1) + num) % 5
```

This ensures the number always fits in an int while still correctly detecting divisibility.

## Algorithm

1. Initialize `val = 0`.
2. For each bit `num` in `nums`:

   * Update prefix remainder using: `val = ((val << 1) + num) % 5`.
   * Add `true` to result if `val == 0`, else `false`.
3. Return the result list.

## Time & Space Complexity

| Metric | Value                        |
| ------ | ---------------------------- |
| Time   | **O(n)**                     |
| Space  | **O(n)** for the result list |

## Code

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>(nums.length);
        int val = 0;
        for (int num : nums) {
            val = ((val << 1) + num) % 5;
            result.add(val == 0);
        }
        return result;
    }
}
```

## Notes

* Using modulo prevents overflow.
* Works efficiently even for large inputs.
