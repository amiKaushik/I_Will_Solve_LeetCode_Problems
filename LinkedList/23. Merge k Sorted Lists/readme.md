# Merge K Sorted Lists — Java Solution

This repository contains a Java implementation for the **Merge K Sorted Linked Lists** problem.

## Problem

You are given an array of `k` sorted linked lists.

Merge all lists into **one sorted linked list** and return its head.

## Main Idea

This is the same core idea as **merge k sorted arrays**.

If you merge sequentially 1 → 1 → 1 → … (pairwise) it becomes too slow when `k` is large.

We need to always pick the **minimum current head** among all lists.

### Which DS gives us efficient smallest extraction?

→ **Priority Queue (Min Heap)**

---

## Algorithm (PriorityQueue approach)

1. create a min-heap that orders nodes by `node.val`
2. push all non-null head nodes into PQ
3. create dummy head for result
4. while PQ not empty:

   * pop smallest node
   * append to result
   * if that node has a next → push next into PQ
5. return dummy.next

---

### Complexity

| Metric | Value                                                   |
| ------ | ------------------------------------------------------- |
| Time   | **O(N log k)** (N total nodes, PQ operations are log k) |
| Space  | **O(k)** PQ stores at most one node per list            |

## Code

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        for (ListNode head : lists) {
            if (head != null) {
                pq.add(head);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode tail = dummy;

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = node;

            if (node.next != null) {
                pq.add(node.next);
            }
        }

        return dummy.next;
    }
}
```
