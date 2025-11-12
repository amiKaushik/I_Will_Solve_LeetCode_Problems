LeetCode #455 — Difficulty: Easy
Tags: Greedy, Sorting, Two Pointers

# Assign Cookies — Java Solution

## Problem

You are given two integer arrays:

* `g` — the **greed factors** of children.
* `s` — the **sizes** of cookies.

A child `i` is content if they receive a cookie `j` such that `s[j] >= g[i]`. Each child can receive at most one cookie.

Return the **maximum number of content children**.

## Key Idea

This problem can be solved using a **Greedy approach**.

The goal is to give each child the **smallest possible cookie** that satisfies their greed, so that larger cookies can be reserved for greedier children.

If we sort both arrays, we can use two pointers to efficiently match children with cookies.

## Algorithm

1. Sort both `g` (children) and `s` (cookies) arrays.
2. Initialize two pointers `i` (for children) and `j` (for cookies).
3. Traverse the cookies list:

   * If the current cookie satisfies the current child (`s[j] >= g[i]`), assign it and move to the next child (`i++`).
   * Move to the next cookie (`j++`) in either case.
4. Once we run out of cookies or children, return the total number of satisfied children (`i`).

## Complexity

| Metric | Value                                |
| ------ | ------------------------------------ |
| Time   | **O(n log n)** — sorting both arrays |
| Space  | **O(1)** — constant extra space      |

## Code

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }
}
```

## Example

```java
findContentChildren([1, 2, 3], [1, 1]) // returns 1
findContentChildren([1, 2], [1, 2, 3]) // returns 2
```

## Notes

* Sorting allows optimal assignment of cookies.
* Each cookie is used once and only if it can satisfy a child.
* Greedy ensures maximum number of content children.
