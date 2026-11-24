
import java.math.BigInteger;

class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {

            BigInteger product = BigInteger.ONE;
            BigInteger gcd = BigInteger.ZERO;
            BigInteger lcm = BigInteger.ONE;

            for (int j = i; j < n; j++) {

                BigInteger x = BigInteger.valueOf(nums[j]);

                // Update product
                product = product.multiply(x);

                // Update GCD
                gcd = gcd.gcd(x);

                // Update LCM
                lcm = lcm.divide(lcm.gcd(x)).multiply(x);

                // Check product equivalence
                if (product.equals(gcd.multiply(lcm))) {
                    ans = Math.max(ans, j - i + 1);
                }
            }
        }

        return ans;
    }
}
