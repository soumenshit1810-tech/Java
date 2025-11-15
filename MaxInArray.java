public class MaxInArray {
    public static void main(String[] args) {
        int[] arr = {4, 1, 9, 2, 7};
        int max = arr[0];

        for (int num : arr) {
            if (num > max) max = num;
        }

        System.out.println("Maximum: " + max);
    }
}
