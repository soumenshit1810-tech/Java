import java.util.*;

class NextGreater {
    static void nextGreater(int[] arr) {
        Stack<Integer> st = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= arr[i])
                st.pop();

            int res = st.isEmpty() ? -1 : st.peek();
            System.out.println(arr[i] + " -> " + res);
            st.push(arr[i]);
        }
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 2, 10};
        nextGreater(arr);
    }
}
