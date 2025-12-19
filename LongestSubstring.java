import java.util.*;

class LongestSubstring {
    public static int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        int l = 0, max = 0;

        for (int r = 0; r < s.length(); r++) {
            while (set.contains(s.charAt(r)))
                set.remove(s.charAt(l++));
            set.add(s.charAt(r));
            max = Math.max(max, r - l + 1);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
