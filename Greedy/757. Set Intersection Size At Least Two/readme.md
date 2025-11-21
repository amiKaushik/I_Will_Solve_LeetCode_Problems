# LeetCode 757 — Set Intersection Size At Least Two
Difficulty: Hard

**Tags:** Greedy, Sorting, Intervals

## Problem

You are given a list of intervals `intervals`, where each interval is of the form `[start, end]`.

Your task:

> Choose a set of integers such that **each interval contains at least two integers** from this set.

Return the **minimum size** of such a set.

## Key Idea — Greedy + Special Sorting

To minimize the set size, pick numbers **as late as possible** (toward interval end).

### Sorting Strategy

Sort intervals by:

1. Increasing end value `e`
2. If tie → decreasing start `s` (to handle nested intervals safely)

This ensures you always consider the tightest intervals first.

## Greedy Logic

Track the **last two chosen numbers**:

* `x` = second last chosen
* `y` = last chosen

For each interval:

1. Count how many of `{x, y}` lie inside the interval.
2. Cases:

   * **2 numbers inside** → do nothing
   * **1 number inside** → add the interval's end `e`
   * **0 numbers inside** → add `e-1` and `e`

Choosing from the end ensures maximum overlap with future intervals.

## Complexity

| Metric | Value                              |
| ------ | ---------------------------------- |
| Time   | **O(n log n)** — sorting dominates |
| Space  | **O(1)** — only a few variables    |
