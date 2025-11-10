LeetCode #3 - Difficulty: Medium
Tags: Sliding Window, HashSet, Two Pointers

# Longest Substring Without Repeating Characters - Java Solution

## Problem Summary

Given a string `s`, return the **length of the longest substring** that contains **no repeated characters**.

## Core Idea

Use **sliding window**.

Window grows from the right until we hit a duplicate.
When duplicate occurs → shrink window from the left until window is valid again.

We use a **HashSet** to track characters currently inside the active window.


## Algorithm

1. Use a `Set<Character>` to store characters currently in window
2. left = 0, right = 0
3. maxLen = 0
4. while right < len(s)

   * if s[right] not in set

     * insert it
     * update maxLen
     * right++ (expand)
   * else

     * remove s[left]
     * left++ (shrink)


## Complexity

| Metric | Value                              |
| ------ | ---------------------------------- |
| Time   | **O(n)**                           |
| Space  | **O(1)** (bounded 128/256 charset) |
