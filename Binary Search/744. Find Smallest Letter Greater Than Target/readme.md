# LeetCode 744 - Find Smallest Letter Greater Than Target

**Difficulty:** Easy
**Tags:** Binary Search, Array

## Problem

You are given a **sorted** array of characters `letters` and a character `target`.

Your task: **find the smallest character in the array that is strictly greater than `target`.**

The array is considered **circular**, meaning:
If no letter is greater than `target`, return the **first** letter of the array.

## Key Idea

This is a classic **binary search for first greater element**:

* Use binary search to find the first index where `letters[mid] > target`.
* If no such index exists (i.e., search ends with `L == n`), wrap around and return `letters[0]`.

Binary search guarantees **O(log n)** time.

## Algorithm

1. Set `L = 0`, `R = n - 1`.
2. While `L <= R`:

   * Compute `mid`.
   * If `letters[mid] > target`, shrink right boundary.
   * Else, move left boundary up.
3. After the loop:

   * If `L == n`, return first element (wrap-around).
   * Else return `letters[L]` — first greater letter.

## Time & Space Complexity

| Metric | Value        |
| ------ | ------------ |
| Time   | **O(log n)** |
| Space  | **O(1)**     |

## Code

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int L = 0, R = letters.length - 1;
        while (L <= R) {
            int mid = (L + R) / 2;
            if (letters[mid] > target) {
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        if (L == letters.length) {
            return letters[0];
        } else {
            return letters[L];
        }
    }
}
```

## Notes

* Works because array is sorted.
* Binary search ensures correctness and efficiency.
* Handles circular behavior cleanly.
