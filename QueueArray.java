public class QueueArray {
    int front = 0, rear = -1, size = 0;
    int[] q = new int[5];

    void enqueue(int x) {
        if (size == 5) {
            System.out.println("Queue Full");
            return;
        }
        q[++rear] = x;
        size++;
    }

    void dequeue() {
        if (size == 0) {
            System.out.println("Queue Empty");
            return;
        }
        System.out.println("Removed: " + q[front++]);
        size--;
    }

    public static void main(String[] args) {
        QueueArray q = new QueueArray();
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.dequeue();
    }
}
