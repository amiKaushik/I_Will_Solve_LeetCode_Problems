# Add Two Numbers — Java Solution

This repository contains a Java implementation for the **Add Two Numbers** problem.

## Problem

Given two non‐empty linked lists representing two non‐negative integers, the digits are stored in **reverse order**, and each of their nodes contains a single digit.

Add the two numbers and return the sum as a linked list.

### Example

Input:

```
l1 = [2,4,3]
l2 = [5,6,4]
```

Output:

```
[7,0,8]
```

Because 342 + 465 = 807.

## Approach

We iterate through both lists simultaneously.

* Extract current digit from each list
* Add them together with carry
* Create new node with (sum % 10)
* Update carry = sum / 10
* Continue until both lists + carry are fully processed

### Complexity

| Metric | Value                                       |
| ------ | ------------------------------------------- |
| Time   | **O(n)** where n = max length of both lists |
| Space  | **O(n)** result list                        |

## Code

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null ? l1.val : 0);
            int y = (l2 != null ? l2.val : 0);
            int sum = x + y + carry;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }
}
```
