class Solution {
    public int bestClosingTime(String customers) {
        int minPenalty=0;
        int currPenalty=0;
        int time=0;
        for(int i=0;i<customers.length();i++){
            char ch=customers.charAt(i);
            if(ch=='Y'){
                currPenalty--;
            }else{
                currPenalty++;
            }

            if(minPenalty>currPenalty){
                minPenalty=currPenalty;
                time=i+1;
            }
        }
        return time;
    }
}
