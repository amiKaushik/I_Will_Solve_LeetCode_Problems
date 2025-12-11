class Solution {
    private static final long MOD = 1_000_000_007L;
    public int countPermutations(int[] complexity) {
        int n=complexity.length;
        long minVal=Long.MAX_VALUE;
        int cntMin=0;
        for(int v:complexity){
            if(v<minVal){
                minVal=v;
                cntMin=1;
            }else if(v==minVal){
                cntMin++;
            }
        }
        if(complexity[0] != minVal || cntMin>1){
            return 0;
        }
        long ans=1L;
        for(int i=2;i<=n-1;i++){
            ans=(ans*i)%MOD;
        }
        return (int)ans;
    }
}
