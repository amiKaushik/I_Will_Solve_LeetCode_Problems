LeetCode #24 — Medium
Tags: Linked List, Recursion, Dummy Node

# Swap Nodes in Pairs — Java Solution

This repository contains Java solutions for **Swap Nodes in Pairs**.

## Problem

Given a linked list, swap every two adjacent nodes and return its head.

You **must** swap the nodes, not the values inside them.


## Approach 1 — Iterative (Dummy Node + Pointer)

Use a dummy node before head so pair rewiring is easier.

Each step swap `a` and `b`:

```
curr -> a -> b -> next
```

After swap becomes:

```
curr -> b -> a -> next
```

Then move curr forward to the end of the swapped pair.

### Complexity

| Metric | Value    |
| ------ | -------- |
| Time   | **O(n)** |
| Space  | **O(1)** |

### Code

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy;

        while (curr.next != null && curr.next.next != null) {
            ListNode a = curr.next;
            ListNode b = a.next;

            curr.next = b;
            a.next = b.next;
            b.next = a;

            curr = a;
        }
        return dummy.next;
    }
}
```

## Approach 2 — Recursive

Base cases:

* fewer than 2 nodes → no swap

Otherwise:

* swap current 2
* recursively solve remainder

### Complexity

| Metric | Value                    |
| ------ | ------------------------ |
| Time   | **O(n)**                 |
| Space  | **O(n)** recursion stack |

### Code

```java
class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode a = head;
        ListNode b = head.next;

        a.next = swapPairs(b.next);
        b.next = a;

        return b;
    }
}
```
