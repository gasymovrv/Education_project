package examples.algorithms.leetcode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseSinglyLinkedList {
    public static void main(String[] args) {
        var head = new ListNode(0);
        ListNode temp = head;
        for (int i = 1; i <= 10; i++) {
            temp.next = new ListNode(i);
            temp = temp.next;
        }
        System.out.println(head);

        var reversed = reverseListAsCopy(head);

        System.out.println(head);
        System.out.println(reversed);
    }

    /**
     * It's a loop that reverses the linked list by changing the next pointers of each node
     * @param head source list head (will be changed in-place, and afterwards it will be the tail of the reversed list)
     * @return reversed list
     */
    public static ListNode reverseList(ListNode head) {
        // We start with three pointers:
        // 1. prev: This will keep track of the previous node we processed (starts as null).
        // 2. next: This will keep track of the next node to process.
        // 3. curr: This is the current node we are processing (starts as the head of the list).
        ListNode prev = null;
        ListNode next;
        ListNode curr = head;

        // We loop through the linked list until we reach the end (when curr is null).
        while (curr != null) {
            // Store the next node in the list. We need to do this because we will change the current node's next pointer.
            next = curr.next;

            // Reverse the link. Instead of pointing to the next node, the current node now points to the previous node.
            curr.next = prev;

            // Move the previous pointer to the current node (since the current node is now processed).
            prev = curr;

            // Move the current pointer to the next node (to continue processing the rest of the list).
            curr = next;
        }

        // When we've processed all nodes, the prev pointer will be at the new head of the reversed list.
        // Head becomes the tail of the reversed list.
        return prev;
    }

    /**
     * It's a recursive function that takes in a head node and returns a reversed list without changing source list
     * @param head source list head
     * @return reversed list as new list
     */
    public static ListNode reverseListAsCopy(ListNode head) {
        return reverseHelper(head, null);
    }

    private static ListNode reverseHelper(ListNode current, ListNode reversed) {
        if (current == null) {
            return reversed;
        }
        // Create a new node with the current value and point it to the reversed list
        ListNode newNode = new ListNode(current.val);
        newNode.next = reversed;
        return reverseHelper(current.next, newNode);
    }
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var node = this;
        while (node != null) {
            sb.append(node.val)
                    .append(", ");
            node = node.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
