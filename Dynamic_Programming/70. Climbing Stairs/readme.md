LeetCode #70 — Difficulty: Easy
Tags: Dynamic Programming, Fibonacci

# Climbing Stairs — Java Solution

## Problem

You are climbing a staircase with `n` steps. Each time you can climb **1** or **2** steps.

Return the **number of distinct ways** to reach the top.

## Intuition

Think about reaching each step:

* To reach step `i`, you can only come from step `i-1` (taking one step) or from step `i-2` (taking two steps).
* So, total ways to reach step `i` = ways to reach `i-1` + ways to reach `i-2`.

This is exactly the **Fibonacci pattern** — each term is the sum of the two previous ones.

## Logical Algorithm

* For the first step, there is **only one way** (just step once).
* For the second step, there are **two ways** (1+1 or a single 2-step).
* Every higher step builds on the count of the two previous steps.
* We can track only the last two results instead of storing everything — this optimizes space to O(1).

In essence:
**ways(n) = ways(n-1) + ways(n-2)**, where `ways(1)=1`, `ways(2)=2`.

## Complexity

| Metric | Value                                |
| ------ | ------------------------------------ |
| Time   | **O(n)** — linear growth with steps  |
| Space  | **O(1)** — only two memory variables |
