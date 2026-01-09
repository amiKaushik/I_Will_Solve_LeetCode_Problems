# LeetCode 1161 — Maximum Level Sum of a Binary Tree

**Difficulty:** Medium
**Tags:** Tree, Breadth-First Search (BFS), Queue

## Problem

You are given the root of a binary tree.

* The root is at **level 1**.
* Its children are at **level 2**, and so on.

Return the **smallest level number** such that the **sum of all node values at that level is maximum**.

## Key Idea

This is a classic **level-order traversal (BFS)** problem.

At each level of the tree:

* Compute the sum of node values.
* Compare it with the maximum sum seen so far.
* If the current level sum is greater, update the answer.

Since BFS processes nodes **level by level**, it is the most natural and efficient approach.

## Algorithm

1. Initialize a queue and push the root node.
2. Maintain:

   * `level` → current level number
   * `maxSum` → maximum level sum found so far
   * `ans` → level with the maximum sum
3. While the queue is not empty:

   * Increment `level`.
   * For all nodes currently in the queue (one full level):

     * Pop nodes, add their values to `sumAtCurrentLevel`.
     * Push their non-null children into the queue.
   * If `sumAtCurrentLevel > maxSum`:

     * Update `maxSum` and `ans = level`.
4. Return `ans`.

## Time & Space Complexity

| Metric | Value                                     |
| ------ | ----------------------------------------- |
| Time   | **O(n)** where `n` is the number of nodes |
| Space  | **O(n)** in the worst case (queue)        |

## Example Walkthrough

For the tree:

```
        1
       / \
      7   0
     / \
    7  -8
```

* Level 1 → sum = `1`
* Level 2 → sum = `7 + 0 = 7`
* Level 3 → sum = `7 + (-8) = -1`

Maximum sum is **7** at **level 2**, so the answer is **2**.

## Notes

* BFS guarantees levels are processed in order, so the **smallest level** with maximum sum is naturally returned.
* Works correctly even when node values are negative.
* Very common interview problem combining trees + queues.
