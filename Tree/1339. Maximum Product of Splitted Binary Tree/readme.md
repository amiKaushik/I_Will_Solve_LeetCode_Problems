# LeetCode 1339 — Maximum Product of Splitted Binary Tree

**Difficulty:** Medium
**Tags:** Tree, DFS, Postorder Traversal

## Problem

You are given the root of a binary tree. You can split the tree into **two subtrees** by removing **exactly one edge**.

Let:

* `sum1` = sum of values in the first subtree
* `sum2` = sum of values in the second subtree

The score of the split is:

```
sum1 × sum2
```

Your task is to **maximize this product** and return the result modulo `10^9 + 7`.

⚠️ Important: The product must be maximized **before** applying modulo.

## Key Idea

If the **total sum of the tree** is known, then:

* Removing an edge at a node creates a subtree with sum `subSum`
* The other part has sum `totalSum − subSum`

So for every subtree, we can consider:

```
product = subSum × (totalSum − subSum)
```

To evaluate all possible splits:

1. First compute the total sum of the tree.
2. Then do a **postorder DFS** to compute each subtree sum.
3. At every node, treat its subtree as one split candidate and update the maximum product.

## Algorithm

1. Perform a DFS to compute `totalSum` of the tree.
2. Perform another DFS:

   * Compute subtree sums bottom-up.
   * For each subtree, compute the product:

     ```
     subSum × (totalSum − subSum)
     ```
   * Update the global maximum.
3. Return `maxProduct % MOD`.

## Time & Space Complexity

| Metric | Value                                        |
| ------ | -------------------------------------------- |
| Time   | **O(n)** where `n` is number of nodes        |
| Space  | **O(h)** recursion stack (`h` = tree height) |

## Code

```java
class Solution {
    private static final long MOD = 1_000_000_007L;
    private long totalSum = 0;
    private long maxProduct = 0;

    public int maxProduct(TreeNode root) {
        // Step 1: compute total sum of the tree
        totalSum = getTotalSum(root);

        // Step 2: compute max product by trying all splits
        computeSubtreeSum(root);

        return (int) (maxProduct % MOD);
    }

    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    private long computeSubtreeSum(TreeNode node) {
        if (node == null) return 0;

        long left = computeSubtreeSum(node.left);
        long right = computeSubtreeSum(node.right);

        long subSum = left + right + node.val;

        // consider splitting here
        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}
```

## Example Walkthrough

Example 1:

```
        1
       / \
      2   3
     / \   \
    4   5   6
```

* Total sum = `21`
* Possible subtree sums: `4, 5, 11, 6, 9, ...`
* Best split: `11 × 10 = 110`

Output = **110**

## Notes

* Postorder traversal is essential because subtree sums depend on children.
* Using `long` avoids overflow during multiplication.
* This is a classic **"tree DP with global aggregation"** pattern.
