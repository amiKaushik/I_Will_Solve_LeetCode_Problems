# Merge Two Sorted Lists — Java Solution

This repository contains a Java solution for the **Merge Two Sorted Linked Lists** problem.

## Problem

You are given the heads of two **sorted** linked lists.

Merge them into one **sorted** linked list and return its head.

## Approach

We use a **dummy node** and a pointer `curr`.

While both lists are non-null:

* compare their values
* link the smaller one to the result
* move that list pointer forward

After the loop — one list may still have remaining nodes.
Just attach the remainder to the end.

### Complexity

| Metric | Value                     |
| ------ | ------------------------- |
| Time   | **O(n + m)**              |
| Space  | **O(1)** (in-place merge) |

## Code

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        if (list1 != null) {
            curr.next = list1;
        } else {
            curr.next = list2;
        }

        return dummy.next;
    }
}
```
