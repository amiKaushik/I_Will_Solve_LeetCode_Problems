class Solution {
    private static final long MOD = 1_000_000_007L;
    private long totalSum = 0;
    private long maxProduct = 0;

    public int maxProduct(TreeNode root) {
        // Step 1: compute total sum of the tree
        totalSum = getTotalSum(root);
        // Step 2: compute max product by trying all splits
        computeSubtreeSum(root);
        return (int)(maxProduct % MOD);
    }
  
    private long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    private long computeSubtreeSum(TreeNode node) {
        if (node == null) return 0;
        long left = computeSubtreeSum(node.left);
        long right = computeSubtreeSum(node.right);

        long subSum = left + right + node.val;

        // consider splitting here
        long product = subSum * (totalSum - subSum);
        maxProduct = Math.max(maxProduct, product);

        return subSum;
    }
}
