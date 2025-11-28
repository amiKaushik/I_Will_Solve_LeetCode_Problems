# LeetCode 2872 - Maximum Number of K-Divisible Components

**Difficulty:** Medium
**Tags:** Tree, DFS, Graph, Modulo, Greedy

## Problem

Given a tree with `n` nodes (0-indexed) described by `edges`, and an integer array `values` where `values[i]` is the value at node `i`, compute the **maximum number of connected components** you can obtain by removing edges such that the sum of node values in each resulting component is **divisible by `k`**.

Return the maximum number of k-divisible components.

## Key Idea

* The tree structure allows a bottom-up approach (DFS) to compute subtree sums.
* For each subtree, track the sum of node values modulo `k`.
* If a subtree sum modulo `k` equals `0`, then the subtree can be cut off as a valid component; increment the answer and propagate `0` upward (so parent doesn't need to include those nodes).
* Use DFS starting from any root (commonly node `0`) and count how many subtrees have sum % k == 0.

## Algorithm (step-by-step)

1. Build adjacency list for the tree from `edges`.
2. Initialize a counter to record number of k-divisible components.
3. Run DFS from root (0) with a `parent` to avoid revisiting.

   * For a node, compute `sum = (sum of child-subtree remainders) + nodeValue` modulo `k`.
   * If `sum % k == 0`, increment component counter and return `0` to parent.
   * Otherwise return `sum % k` to parent.
4. The counter after DFS finishes is the answer.

## Time & Space Complexity

| Metric | Value                                         |
| ------ | --------------------------------------------- |
| Time   | **O(n)** (each node/edge visited once)        |
| Space  | **O(n)** for adjacency list + recursion stack |

## Code (Java)

```java
// Editorial Solution
class Solution {

    public int maxKDivisibleComponents(
        int n,
        int[][] edges,
        int[] values,
        int k
    ) {
        // Step 1: Create adjacency list from edges
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            adjList[node1].add(node2);
            adjList[node2].add(node1);
        }

        // Step 2: Initialize component count
        int[] componentCount = new int[1]; // Use array to pass by reference

        // Step 3: Start DFS traversal from node 0
        dfs(0, -1, adjList, values, k, componentCount);

        // Step 4: Return the total number of components
        return componentCount[0];
    }

    private int dfs(
        int currentNode,
        int parentNode,
        List<Integer>[] adjList,
        int[] nodeValues,
        int k,
        int[] componentCount
    ) {
        // Step 1: Initialize sum for the current subtree
        int sum = 0;

        // Step 2: Traverse all neighbors
        for (int neighborNode : adjList[currentNode]) {
            if (neighborNode != parentNode) {
                // Recursive call to process the subtree rooted at the neighbor
                sum += dfs(
                    neighborNode,
                    currentNode,
                    adjList,
                    nodeValues,
                    k,
                    componentCount
                );
                sum %= k; // Ensure the sum stays within bounds
            }
        }

        // Step 3: Add the value of the current node to the sum
        sum += nodeValues[currentNode];
        sum %= k;

        // Step 4: Check if the sum is divisible by k
        if (sum == 0) {
            componentCount[0]++;
        }

        // Step 5: Return the computed sum for the current subtree
        return sum;
    }
}
```

## Notes

* Root choice doesn't affect the final count because tree partitions are independent of root orientation.
* Using modulo arithmetic prevents large integer sums.
* Each time a subtree sum becomes 0 mod k, we can treat it as a separable component (greedy local decision is optimal due to tree structure).
