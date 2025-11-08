class RecursiveSolution {
    public ListNode swapPairs(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode a=head;
        ListNode b=head.next;
        a.next=swapPairs(b.next);
        b.next=a;

        return b;
    }
}
