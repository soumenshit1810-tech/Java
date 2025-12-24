class ReverseArrayRecursion {
    static void reverse(int[] arr, int start, int end) {
        if (start >= end) return;

        int temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;

        reverse(arr, start + 1, end - 1);
    }

    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40};
        reverse(arr, 0, arr.length - 1);
        for (int x : arr)
            System.out.print(x + " ");
    }
}
