class Node {
    int data;
    Node next;
    Node(int d) { data = d; }
}

public class Singly {
    Node head;

    void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null) temp = temp.next;
        temp.next = newNode;
    }

    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static void main(String[] args) {
        LinkedListDemo ll = new LinkedListDemo();
        ll.insert(10);
        ll.insert(20);
        ll.insert(30);
        ll.display();
    }
}
