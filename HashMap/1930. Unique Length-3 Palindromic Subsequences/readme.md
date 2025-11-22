# LeetCode 1930 — Unique Length-3 Palindromic Subsequences

**Difficulty:** Medium
**Tags:** String, Counting, Hashing

## Problem

Given a string `s`, return the number of **unique** palindromes of length **three** that appear as a **subsequence**.

A palindrome of length 3 has the pattern `a b a`.

## Key Idea

* A valid palindrome of length 3 must have the **same outer characters** and **any middle character**.
* For each index treated as the middle character, check:

  * if the same character exists on the **left**, and
  * if the same character exists on the **right**.
* Track seen combinations `(outer, mid)` in a 26×26 boolean grid.

## Algorithm

1. Count all characters initially in `countR`.
2. Maintain `seenL` → characters seen on the left.
3. For each index `j` (as the middle):

   * Reduce right count for that character.
   * For each `outer` (0–25):

     * If `outer` exists on left **and** still exists on right → mark `seen[outer][mid] = true`.
   * Mark current character as seen on the left.
4. Count all `true` entries in `seen`.

## Time & Space Complexity

| Metric | Value                            |
| ------ | -------------------------------- |
| Time   | **O(n)**                         |
| Space  | **O(1)** (constant 26×26 arrays) |

## Code

```java
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
```

## Notes

* Ensures **unique** palindromic subsequences via the `seen` grid.
* Efficient and uses only constant auxiliary space.
