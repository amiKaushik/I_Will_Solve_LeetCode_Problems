class Solution {
    public int countPalindromicSubsequence(String s) {
        int n=s.length();
        int[] countR=new int[26];
        boolean[] seenL = new boolean[26];
        boolean[][] seen = new boolean[26][26];

        for(int i=0;i<n;i++){
            countR[s.charAt(i) - 'a']++;
        }
        for(int j=0;j<n;j++){
            int mid=s.charAt(j) - 'a';
            countR[mid]--;
            for(int outer=0;outer<26;outer++){
                if(seenL[outer] && countR[outer]>0){
                    seen[outer][mid]=true;
                }
            }
            seenL[mid]=true;
        }
        int ans=0;
        for(int outer=0;outer<26;outer++){
            for(int mid=0;mid<26;mid++){
                if(seen[outer][mid]) ans++;
            }
        }
        return ans;
    }
}
