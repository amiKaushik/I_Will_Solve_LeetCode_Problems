class Solution1 {
    public int maxArea(int[] height) {
        int L=0;
        int R=height.length-1;
        int maxW=0;

        while(L<R){
            int H=Math.min(height[L],height[R]);
            int width=R-L;
            maxW=Math.max(maxW,H*width);
            if(height[L]<height[R]){
                L++;
            }else{
                R--;
            }
        }
        return maxW;
    }
}
