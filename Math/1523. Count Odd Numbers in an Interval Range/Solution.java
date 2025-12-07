class Solution {
    public int countOdds(int low, int high) {
        int L=high-low+1;
        if(L%2 == 0){
            return L/2;
        }
        if(low%2==1){
            return L/2 + 1;
        }else{
            return L/2;
        }
    }
}
