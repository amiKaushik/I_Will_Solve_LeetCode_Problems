LeetCode #2169 — Difficulty: Easy
Tags: Math, GCD, Simulation

# Count Operations to Obtain Zero — Java Solution

## Problem

Given two integers `num1` and `num2`, you can perform operations:

* if `num1 >= num2` do `num1 -= num2`
* else do `num2 -= num1`

Count how many operations until one becomes zero.

## Hint / Insight

This is literally Euclid's **GCD subtraction method**.

Brute force does `1 subtraction` each step.

But we can do **division jumps**:

```
ops += x / y
x = x % y
```

This compresses multiple same subtractions into 1 step.

## Algorithm

1. ops = 0
2. while both num1 != 0 AND num2 != 0
3. if num1 >= num2

   * ops += num1 / num2
   * num1 = num1 % num2
     else
   * ops += num2 / num1
   * num2 = num2 % num1
4. return ops

## Complexity

| Metric | Value                                       |
| ------ | ------------------------------------------- |
| Time   | **O(log max(num1,num2))** (same as GCD-ish) |
| Space  | **O(1)**                                    |
