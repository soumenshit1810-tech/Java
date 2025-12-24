class ReverseArrayExtra {
    public static void main(String[] args) {
        int[] arr = {5, 6, 7, 8};
        int[] rev = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            rev[i] = arr[arr.length - 1 - i];
        }

        for (int x : rev)
            System.out.print(x + " ");
    }
}
