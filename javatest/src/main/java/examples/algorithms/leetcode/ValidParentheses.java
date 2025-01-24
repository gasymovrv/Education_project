package examples.algorithms.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.com/problems/valid-parentheses/description/
 *
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the
 * input string is valid.
 *
 * An input string is valid if:
 *
 * Open brackets must be closed by the same type of brackets. Open brackets must be closed in the
 * correct order. Every close bracket has a corresponding open bracket of the same type.
 *
 *
 * Example 1: Input: s = "()" Output: true
 *
 * Example 2: Input: s = "()[]{}" Output: true
 *
 * Example 3: Input: s = "(]" Output: false
 *
 * Example 4: Input: s = "([])" Output: true
 *
 *
 * Constraints:
 *
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
