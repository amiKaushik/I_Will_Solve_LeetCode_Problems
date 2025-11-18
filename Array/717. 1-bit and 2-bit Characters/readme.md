## LeetCode 717 — 1-bit and 2-bit Characters

**Difficulty:** Easy
**Tags:** Array, Simulation

### Problem Summary

You are given an array `bits` representing characters encoded as either:

* **1-bit character:** `0`
* **2-bit character:** `10` or `11`

The array always ends with a `0`. Determine whether this last `0` represents a **1-bit character**.

### Approach: Greedy Pointer Walk

We simulate how characters are formed:

* If we encounter `1`, it must be the start of a **2-bit character**, so skip **2 positions**.
* If we encounter `0`, it is a **1-bit character**, so skip **1 position**.
* We stop before the last index (`n-1`). If our pointer finally lands **exactly** on the last index → the last character is 1-bit.

### Code

```java
class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int i = 0;
        int n = bits.length;
        
        while (i < n - 1) {
            if (bits[i] == 1) {
                i += 2; // 2-bit char
            } else {
                i += 1; // 1-bit char
            }
        }

        return i == n - 1; // ended exactly at last 0
    }
}
```

### Complexity

| Metric | Value                          |
| ------ | ------------------------------ |
| Time   | **O(n)** — single pointer scan |
| Space  | **O(1)** — constant memory     |

### Notes

* The key is understanding that **every 1 forces a skip of the next bit**.
* The last bit must be `0`, but it is only a 1-bit character if decoding lands exactly on it.
