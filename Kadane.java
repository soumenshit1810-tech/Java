class Kadane {
    int[] maxSubArray(int[] arr) {
        int max = arr[0], cur = arr[0];
        int start = 0, end = 0, s = 0;

        for (int i = 1; i < arr.length; i++) {
            if (cur + arr[i] < arr[i]) {
                cur = arr[i];
                s = i;
            } else {
                cur += arr[i];
            }

            if (cur > max) {
                max = cur;
                start = s;
                end = i;
            }
        }

        return new int[]{max, start, end};
    }
}
