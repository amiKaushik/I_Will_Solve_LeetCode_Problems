class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int L=0, R=letters.length-1;
        while(L<=R){
            int mid=(L+R)/2;
            if(letters[mid]>target){
                R=mid-1;
            }else{
                L=mid+1;
            }
        }
        if(L==letters.length){
            return letters[0];
        }else{
            return letters[L];
        }
    }
}
