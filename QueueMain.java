class Queue {
    int front = 0, rear = -1, size = 0;
    int[] arr = new int[5];

    void enqueue(int data) {
        if (size == arr.length) {
            System.out.println("Queue Full");
            return;
        }
        rear = (rear + 1) % arr.length;
        arr[rear] = data;
        size++;
    }

    int dequeue() {
        if (size == 0) {
            System.out.println("Queue Empty");
            return -1;
        }
        int val = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return val;
    }
}

public class QueueMain {
    public static void main(String[] args) {
        Queue q = new Queue();
        q.enqueue(10);
        q.enqueue(20);
        System.out.println(q.dequeue());
    }
}
