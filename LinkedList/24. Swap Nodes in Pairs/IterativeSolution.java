class IterativeSolution {
  public ListNode swapPairs(ListNode head) {
      ListNode dummy=new ListNode(0);
      dummy.next=head;
      ListNode curr=dummy;
      while(curr.next!=null && curr.next.next!=null){
        ListNode a=curr.next;
        ListNode b=a.next;
        
        curr.next=b;
        a.next=b.next;
        b.next=a;

        curr=a;
      }
      return dummy.next;
    }
}
