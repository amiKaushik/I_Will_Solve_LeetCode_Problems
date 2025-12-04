# LeetCode 2211 — Count Collisions on a Road

**Difficulty:** Medium
**Tags:** Simulation, String, Greedy

## Problem

You are given a string `directions`, where each character represents the movement of a car:

* `'L'` → moving left
* `'R'` → moving right
* `'S'` → stationary

Cars collide if:

* A right-moving car meets a left-moving car
* A moving car meets a stationary car

After collision, both cars involved become **stationary ('S')**.

Return the **total number of collisions** that will occur.

## Key Idea

This looks like a simulation problem, but there is a crucial simplification:

### 1️⃣ Cars moving left at the far left edge will never collide.

Example: `LLL...` → nothing stops them.

### 2️⃣ Cars moving right at the far right edge will never collide.

Example: `...RRR` → they leave the road.

We can safely **trim off** these non-colliding segments.

### 3️⃣ In the remaining segment, **every L or R car will eventually collide**, except stationary ones.

Why?

* Any `'R'` will collide with either `'S'` or `'L'`.
* Any `'L'` will collide with preceding `'R'` or `'S'`.

So after trimming:

* Count how many characters are not `'S'` → each such car contributes exactly **1 collision**.

## Algorithm

1. Let `left` move right while encountering initial `'L'` cars.
2. Let `right` move left while encountering trailing `'R'` cars.
3. If nothing remains (`left > right`), return `0`.
4. In the remaining substring, count how many characters are not `'S'`.
5. Return that count.

## Time & Space Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

## Code

```java
class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        int left = 0, right = n - 1;

        while (left < n && directions.charAt(left) == 'L') {
            left++;
        }
        while (right >= 0 && directions.charAt(right) == 'R') {
            right--;
        }

        if (left > right) return 0;

        int collisions = 0;
        for (int i = left; i <= right; i++) {
            if (directions.charAt(i) != 'S') {
                collisions++;
            }
        }
        return collisions;
    }
}
```

## Notes

* This solution avoids simulation by using an important observation about which cars can *never* collide.
* All collisions in the remaining segment convert cars to `'S'`, so counting all non-`'S'` cars there gives the total number of collisions.
* Very efficient and clean Greedy approach.
