class MatrixExp {
    long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        C[0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0];
        C[0][1] = A[0][0]*B[0][1] + A[0][1]*B[1][1];
        C[1][0] = A[1][0]*B[0][0] + A[1][1]*B[1][0];
        C[1][1] = A[1][0]*B[0][1] + A[1][1]*B[1][1];
        return C;
    }

    long[][] power(long[][] M, long n) {
        if (n == 1) return M;
        long[][] half = power(M, n / 2);
        long[][] result = multiply(half, half);
        if (n % 2 != 0) result = multiply(result, M);
        return result;
    }

    long fibonacci(long n) {
        if (n <= 1) return n;
        long[][] F = {{1, 1}, {1, 0}};
        return power(F, n - 1)[0][0];
    }
}
