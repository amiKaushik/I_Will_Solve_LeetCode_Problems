LeetCode #12 — Difficulty: Medium
Tags: Math, Greedy, Simulation

# Integer to Roman — Java Solution

## Problem

Convert an integer to its Roman numeral representation (1 <= num <= 3999).

## Algorithm (step-by-step)

1. Create an array of integer values in descending order:
   `[1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1]`
2. Create a parallel array of Roman symbols:
   `["M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"]`
3. Use a `StringBuilder` for the result.
4. For each value in `values[]`, while `num >= values[i]`, append `symbols[i]` and subtract `values[i]` from `num`.
5. Return `sb.toString()`.

This handles special subtractive cases (IV, IX, XL, XC, CD, CM) naturally because those values are included in the `values[]` array.

## Complexity

| Metric | Value                                                  |
| ------ | ------------------------------------------------------ |
| Time   | **O(1)** — bounded by constant number of Roman symbols |
| Space  | **O(1)**                                               |
