# LeetCode 35016 — Maximum Number of Operations to Move Ones to the End

**Difficulty:** Medium
**Tags:** Greedy, Counting, String

---

## Problem Summary

Given a binary string `s`, you want to compute the **maximum** number of operations required to move all `'1'` characters to the **end** of the string.

A valid operation consists of selecting an index `i` such that:

* `s[i] == '1'`
* `s[i+1] == '0'`

Then you swap these characters.

Your task is to determine the **maximum total number of operations** you can perform.

---

## Key Intuition

To maximize operations, we want **each '1' to be blocked as many times as possible**.

* If we moved the rightmost `1`s first, they would move freely—**minimum** operations.
* If we move **leftmost** `1`s first, they get repeatedly blocked by later `1`s → **maximum** operations.

This means:

> We should traverse the string **left → right**, accumulating how many `'1'`s have appeared so far.

When we encounter a **segment of zeros**, every previous `'1'` contributes **+1 operation**.

Why?

* Each earlier `1` tries to move right but is blocked by the start of this zero segment.

---

## Algorithm

1. `countOne = 0` — how many `'1'`s seen so far.
2. `ans = 0` — total maximum operations.
3. Traverse string from left to right.
4. If current char is `'1'`, increment `countOne`.
5. If current char is `'0'`, skip over the entire block of consecutive zeros and add:
   `ans += countOne`
6. Continue until end of string.
7. Return `ans`.

This works because each zero block causes **every earlier 1** to be blocked exactly once.

---

## Time & Space Complexity

| Metric | Value                               |
| ------ | ----------------------------------- |
| Time   | **O(n)** — single pass              |
| Space  | **O(1)** — constant extra variables |

---

## Code

```java
public class Solution {
    public int maxOperations(String s) {
        int countOne = 0;
        int ans = 0;
        int i = 0;
        
        while (i < s.length()) {
            if (s.charAt(i) == '0') {
                while (i + 1 < s.length() && s.charAt(i + 1) == '0') {
                    i++;
                }
                ans += countOne;
            } else {
                countOne++;
            }
            i++;
        }
        return ans;
    }
}
```

---

## Explanation Example

For input:

```
110010
```

Steps:

* First zero block after `11` → add 2
* Next zero block after `1100` → add 2
* Last zero block after `110010` → add 3

Total = **7 operations**.

---

## Notes

* Greedy approach ensures **maximum** blocking.
* Counts operations without physically modifying the string.
* Works efficiently for very large inputs.
