package examples.algorithms.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 * <p>
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
 * input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets. Open brackets must be closed in the
 * correct order. Every close bracket has a corresponding open bracket of the same type.
 * <p>
 * <p>
 * Example 1: Input: s = "()" Output: true
 * <p>
 * Example 2: Input: s = "()[]{}" Output: true
 * <p>
 * Example 3: Input: s = "(]" Output: false
 * <p>
 * Example 4: Input: s = "([])" Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104 s consists of parentheses only '()[]{}'.
 */
public class ValidParentheses {

    public static void main(String[] args) {
        System.out.println(isValid("()"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("([])"));
        System.out.println(isValid("("));
    }

    public static boolean isValid(String s) {
        Deque<Character> queue = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                queue.add(c);
            }
            if (c == ')' || c == ']' || c == '}') {
                var lastOpenBracket = queue.pollLast();
                if (lastOpenBracket == null) {
                    return false;
                }
                if (c == ')' && lastOpenBracket != '(') {
                    return false;
                }
                if (c == ']' && lastOpenBracket != '[') {
                    return false;
                }
                if (c == '}' && lastOpenBracket != '{') {
                    return false;
                }
            }
        }
        return queue.isEmpty();
    }
}
