class Solution {
    public int[][] rangeAddQueries(int n, int[][] queries) {
        int[][] diff=new int[n][n+1];
        for(int[] q:queries){
            int r1=q[0],c1=q[1],r2=q[2],c2=q[3];

            for(int r=r1;r<=r2;r++){
                diff[r][c1] +=1;
                if(c2+1<n){
                    diff[r][c2+1] -= 1;
                }
            }
        }

        int[][] mat=new int[n][n];
        for(int r=0;r<n;r++){
            int running=0;
            for(int c=0;c<n;c++){
                running += diff[r][c];
                mat[r][c]=running;
            }
        }
        return mat;
    }
}
