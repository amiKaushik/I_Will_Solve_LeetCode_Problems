class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        int[][][] dp = new int[m][n][k];
        dp[0][0][grid[0][0] % k] = 1;

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                int val = grid[i][j];
                for(int rPrev = 0; rPrev < k; rPrev++){
                    if(i>0){
                        int newR = (rPrev + val)%k;
                        dp[i][j][newR] = (dp[i][j][newR] + dp[i - 1][j][rPrev]) % MOD;
                    }
                    if(j>0){
                        int newR = (rPrev + val) % k;
                        dp[i][j][newR]=(dp[i][j][newR]+dp[i][j - 1][rPrev]) % MOD;   
                    }
                }
            }
        }
        return dp[m - 1][n - 1][0];
    }
}
