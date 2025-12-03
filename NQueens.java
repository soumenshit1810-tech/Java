class NQueens {
    boolean isSafe(int[][] board, int r, int c, int n) {
        for (int i = 0; i < r; i++)
            if (board[i][c] == 1) return false;

        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1) return false;

        for (int i = r, j = c; i >= 0 && j < n; i--, j++)
            if (board[i][j] == 1) return false;

        return true;
    }

    boolean solve(int[][] board, int r, int n) {
        if (r == n) return true;

        for (int c = 0; c < n; c++) {
            if (isSafe(board, r, c, n)) {
                board[r][c] = 1;
                if (solve(board, r + 1, n)) return true;
                board[r][c] = 0;
            }
        }

        return false;
    }
}
