class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        int size = 2 * m;

        long[] init = new long[size];

        // Length 2 initialization
        for (int v = 1; v <= m; v++) {
            init[v - 1] = v - 1;      // up[v]
            init[m + v - 1] = m - v;  // down[v]
        }

        long[][] trans = new long[size][size];

        for (int u = 1; u <= m; u++) {
            for (int v = 1; v <= m; v++) {

                // down[u] -> up[v] if u < v
                if (u < v) {
                    trans[v - 1][m + u - 1] = 1;
                }

                // up[u] -> down[v] if u > v
                if (u > v) {
                    trans[m + v - 1][u - 1] = 1;
                }
            }
        }

        long[][] power = matrixPower(trans, n - 2);

        long[] finalState = multiply(power, init);

        long ans = 0;
        for (long x : finalState) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[] multiply(long[][] mat, long[] vec) {
        int n = mat.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = 0; j < n; j++) {
                sum = (sum + mat[i][j] * vec[j]) % MOD;
            }
            res[i] = sum;
        }

        return res;
    }

    private long[][] matrixPower(long[][] base, long exp) {
        int n = base.length;

        long[][] result = new long[n][n];
        for (int i = 0; i < n; i++) {
            result[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = multiply(result, base);
            }

            base = multiply(base, base);
            exp >>= 1;
        }

        return result;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + val * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }
}