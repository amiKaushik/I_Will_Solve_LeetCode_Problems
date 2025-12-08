class Solution {
    public int countTriples(int n) {
        int ans=0;
        for(int c=1;c<=n;c++){
            int a=1,b=c-1,c2=c*c;
            while(a<b){
                int s = a*a + b*b;
                if(s==c2){
                    ans+=2;
                    a++;
                    b--;
                }else if(s<c2){
                    a++;
                }else{
                    b--;
                }
            }
        }
        return ans;
    }
}
