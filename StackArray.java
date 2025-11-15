public class StackArray {
    int top = -1;
    int[] stack = new int[5];

    void push(int x) {
        if (top == 4) {
            System.out.println("Stack Overflow");
            return;
        }
        stack[++top] = x;
    }

    void pop() {
        if (top == -1) {
            System.out.println("Stack Underflow");
            return;
        }
        System.out.println("Popped: " + stack[top--]);
    }

    void display() {
        for (int i = 0; i <= top; i++)
            System.out.print(stack[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        StackArray st = new StackArray();
        st.push(10);
        st.push(20);
        st.push(30);
        st.display();
        st.pop();
    }
}


