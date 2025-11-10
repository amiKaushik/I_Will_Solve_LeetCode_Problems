LeetCode #3542 — Difficulty: Medium
Tags: Stack, Monotonic Stack, Greedy

# Minimum Operations to Convert All Elements to Zero — Java Solution

## Problem Summary

You are given an array `nums`.
You can perform operations — each operation you "choose some **strictly increasing** sequence" of positive integers and subtract 1 from each of them.

Goal: make the entire array all zeros using minimum operations.


## Core Insight

The number of operations = number of **new increasing segments**.

We simulate increasing plateaus.

What structure tracks monotonic segments efficiently?
→ **monotonic stack** (non-decreasing)

Whenever we encounter a `0`, no positive plateau can survive → clear stack.
Whenever next value is smaller → pop while bigger elements exist.
Whenever next value is **strictly larger than last in stack** → this starts a **new distinct segment** → increment ops.


## Why this works

Each increasing streak represents a vertical "layer" we will peel.
Stack compresses plateaus.

This exactly counts how many distinct bar-height starts exist.


## Algorithm

1. ops = 0
2. Create a monotonic stack (storing heights)
3. Iterate each x in nums

   * if x == 0 → clear stack
   * else pop while top > x
   * if stack empty OR top < x → push x and ops++
4. return ops


## Complexity

| Metric | Value                                |
| ------ | ------------------------------------ |
| Time   | **O(n)**                             |
| Space  | **O(n)** worst (strictly increasing) |
