import java.util.*;

public class HashMapExample {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();

        map.put("India", 91);
        map.put("USA", 1);

        System.out.println("Keys: " + map.keySet());
        System.out.println("Value of India: " + map.get("India"));
    }
}
