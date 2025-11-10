class Solution {
    public int minOperations(int[] nums) {
        int ops=0;
        Deque<Integer> stack=new ArrayDeque<>();
        for(int x:nums){
            if(x==0){
                stack.clear();
                continue;
            }
            while(!stack.isEmpty() && stack.peekLast()>x){
                stack.pollLast();
            }
            if(stack.isEmpty() || stack.peekLast()<x){
                stack.addLast(x);
                ops++;
            }
        }
        return ops;
    }
}
