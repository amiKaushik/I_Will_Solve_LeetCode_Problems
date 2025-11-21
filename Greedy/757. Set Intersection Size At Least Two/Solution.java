class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->
            a[1]==b[1] ? b[0]-a[0] : a[1] - b[1]
        );
        int x = -1_000_000_000;
        int y = -1_000_000_000;
        int ans=0;

        for(int[] iv : intervals){
            int s=iv[0], e=iv[1];
            int count=0;
            if(x>=s && x<=e) count++;
            if(y>=s && y<=e) count++;
            if(count==2){
                continue;
            }else if(count==1){
                x=y;
                y=e;
                ans+=1;
            }else{
                x=e-1;
                y=e;
                ans+=2;
            }
        }
        return ans;
    }
}
