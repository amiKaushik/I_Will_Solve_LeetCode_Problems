LeetCode #7 — Difficulty: Medium
Tags: Math, Overflow, Modulo, Simulation

# Reverse Integer — Java Solution

## Problem Summary

Given an integer `x`, reverse its digits.

Examples:

* `123` → `321`
* `-123` → `-321`
* `120` → `21`

BUT — if the reversed integer **overflows 32-bit signed integer range**:
`[-2^31 , 2^31 - 1]`

→ return **0**.

## Why this is tricky

We must avoid overflow **before** building the new number.

No string conversion, no long tricks.
We reverse digit-by-digit using math.


## Key Idea / Hint

Remove the last digit each loop:

```
digit = x % 10
```

Build reversed number:

```
rev = rev * 10 + digit
```

### Overflow guard

Before we do `rev * 10 + digit`, check boundaries:

```
if rev > MAX/10 → overflow soon
if rev == MAX/10 and digit > 7 → overflow
```

(similar checks for negative)


## Walkthrough Example

x = 123

| step | x   | digit | rev |
| ---- | --- | ----- | --- |
| 1    | 123 | 3     | 3   |
| 2    | 12  | 2     | 32  |
| 3    | 1   | 1     | 321 |

## Complexity

| Metric | Value                        |
| ------ | ---------------------------- |
| Time   | **O(log₁₀(x))** digits count |
| Space  | **O(1)**                     |
