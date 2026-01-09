# LeetCode 865 — Smallest Subtree with all the Deepest Nodes

**Difficulty:** Medium
**Tags:** Tree, DFS, Postorder, Divide and Conquer

## Problem

You are given the root of a binary tree.

* The **depth** of a node is the number of edges from the root to that node.
* A node is called **deepest** if its depth is the maximum among all nodes.

Return the **smallest subtree** that contains **all the deepest nodes**.

The subtree must include:

* The chosen node
* All of its descendants

## Key Idea

For every node, we want to know:

* The **maximum depth** of its subtree
* The **node** that represents the smallest subtree containing all deepest nodes in that subtree

This can be solved in **one DFS traversal** using a **postorder strategy**.

### Core Observation

At any node:

* If left and right subtrees have the **same depth**, then the current node is the **lowest common ancestor** of the deepest nodes → return current node.
* If one side is deeper, then the answer must lie **entirely in that deeper subtree**.

So each recursive call returns a pair:

```
(nodeContainingDeepest, depth)
```

## Algorithm

1. Perform a DFS from the root.
2. For each node, recursively compute results for left and right children.
3. Compare depths:

   * If equal → return `(current node, depth + 1)`
   * If left deeper → return `(left.node, left.depth + 1)`
   * If right deeper → return `(right.node, right.depth + 1)`
4. The final returned node is the answer.

## Time & Space Complexity

| Metric | Value                                        |
| ------ | -------------------------------------------- |
| Time   | **O(n)**                                     |
| Space  | **O(h)** recursion stack (`h` = tree height) |

## Code

```java
class Solution {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(null, 0);
        }

        Result left = dfs(node.left);
        Result right = dfs(node.right);

        if (left.depth == right.depth) {
            return new Result(node, left.depth + 1);
        } else if (left.depth > right.depth) {
            return new Result(left.node, left.depth + 1);
        } else {
            return new Result(right.node, right.depth + 1);
        }
    }

    private static class Result {
        TreeNode node;
        int depth;

        Result(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
```

## Example Walkthrough

Example:

```
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4
```

* Deepest nodes: `7` and `4`
* Their lowest common ancestor is `2`
* Subtree rooted at `2` is the **smallest subtree** containing all deepest nodes

Output: **[2,7,4]**

## Notes

* This is a classic **tree divide-and-conquer** problem.
* Postorder traversal is essential since depth depends on children.
* Elegant one-pass solution without extra data structures.
* Commonly asked in interviews to test tr
