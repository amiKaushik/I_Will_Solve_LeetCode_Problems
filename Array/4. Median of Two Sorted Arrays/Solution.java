class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int i = 0, j = 0, index = 0;
        int[] sort = new int[n + m];

        while (i < n && j < m) {
            if (nums1[i] < nums2[j]) {
                sort[index++] = nums1[i++];
            } else {
                sort[index++] = nums2[j++];
            }
        }
        while (i < n) {
            sort[index++] = nums1[i++];
        }
        while (j < m) {
            sort[index++] = nums2[j++];
        }

        int len = n + m;
        int mid = len / 2;

        if (len % 2 == 0) {
            return (sort[mid - 1] + sort[mid]) / 2.0;
        } else {
            return sort[mid];
        }
    }
}
