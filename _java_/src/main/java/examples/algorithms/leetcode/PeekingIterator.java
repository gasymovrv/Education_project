package examples.algorithms.leetcode;

import java.util.Arrays;
import java.util.Iterator;

/**
 * https://leetcode.com/problems/peeking-iterator/description/
 * <p>
 * Design an iterator that supports the peek operation on an existing iterator in addition to the hasNext and the next operations.
 * <p>
 * Implement the PeekingIterator class:
 * <p>
 * PeekingIterator(Iterator<int> nums) Initializes the object with the given integer iterator iterator.
 * int next() Returns the next element in the array and moves the pointer to the next element.
 * boolean hasNext() Returns true if there are still elements in the array.
 * int peek() Returns the next element in the array without moving the pointer.
 * Note: Each language may have a different implementation of the constructor and Iterator, but they all support the int next() and boolean hasNext() functions.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["PeekingIterator", "next", "peek", "next", "next", "hasNext"]
 * [[[1, 2, 3]], [], [], [], [], []]
 * Output
 * [null, 1, 2, 2, 3, false]
 * <p>
 * Explanation
 * PeekingIterator peekingIterator = new PeekingIterator([1, 2, 3]); // [1,2,3]
 * peekingIterator.next();    // return 1, the pointer moves to the next element [1,2,3].
 * peekingIterator.peek();    // return 2, the pointer does not move [1,2,3].
 * peekingIterator.next();    // return 2, the pointer moves to the next element [1,2,3]
 * peekingIterator.next();    // return 3, the pointer moves to the next element [1,2,3]
 * peekingIterator.hasNext(); // return False
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * All the calls to next and peek are valid.
 * At most 1000 calls will be made to next, hasNext, and peek.
 * <p>
 * <p>
 * Follow up: How would you extend your design to be generic and work with all types, not just integer?
 */
class PeekingIteratorTest {
    public static void main(String[] args) {
        PeekingIterator iterator = new PeekingIterator(Arrays.stream(new int[]{1, 2, 3, 4}).iterator());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.peek());
        System.out.println(iterator.peek());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.peek());
        System.out.println(iterator.peek());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.peek());
        System.out.println(iterator.hasNext());
        System.out.println(iterator.next());
        System.out.println(iterator.hasNext());
    }
}

class PeekingIterator implements Iterator<Integer> {
    private final Iterator<Integer> iterator;
    private Integer cachedNext = null;

    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (cachedNext != null) {
            return cachedNext;
        }
        if (iterator.hasNext()) {
            cachedNext = iterator.next();
            return cachedNext;
        }
        return null;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        if (cachedNext == null) {
            return iterator.next();
        } else {
            Integer next = cachedNext;
            cachedNext = null;
            return next;
        }
    }

    @Override
    public boolean hasNext() {
        if (cachedNext != null) {
            return true;
        } else return iterator.hasNext();
    }
}
