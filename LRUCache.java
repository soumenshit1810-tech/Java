import java.util.*;

class LRUCache {
    class Node {
        int key, value;
        Node prev, next;
        Node(int k, int v) { key = k; value = v; }
    }

    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    Node head = new Node(0,0), tail = new Node(0,0);

    LRUCache(int cap) {
        capacity = cap;
        head.next = tail;
        tail.prev = head;
    }

    void add(Node node) {
        Node temp = head.next;
        head.next = node;
        node.prev = head;
        node.next = temp;
        temp.prev = node;
    }

    void remove(Node node) {
        Node p = node.prev;
        Node n = node.next;
        p.next = n;
        n.prev = p;
    }

    int get(int key) {
        if (!map.containsKey(key)) return -1;
        Node node = map.get(key);
        remove(node);
        add(node);
        return node.value;
    }

    void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            add(node);
            return;
        }

        if (map.size() == capacity) {
            map.remove(tail.prev.key);
            remove(tail.prev);
        }

        Node node = new Node(key, value);
        add(node);
        map.put(key, node);
    }
}
