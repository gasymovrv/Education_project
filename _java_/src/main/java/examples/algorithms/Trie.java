package examples.algorithms;

import java.util.HashMap;
import java.util.Map;

/**
 * A Trie (pronounced "try") is a tree-like data structure used for storing strings efficiently. It's mainly used for:
 * <p>
 * - Word storage and retrieval – Quickly checking if a word exists.
 * <p>
 * - Prefix searching – Checking if a given prefix exists.
 * <p>
 * - Autocomplete features – Finding words based on a prefix.
 * <p>
 * Each node in a Trie represents a single character, and words are stored as paths from the root to a leaf node.
 * <p>
 * Example: If we insert "car" and "cat", the structure would look like:
 * <p>
 * <pre>
 *        (root)
 *        /    \
 *       c      (other letters)
 *      /
 *     a
 *    / \
 *   r   t
 * (end) (end)
 * </pre>
 * <br/>
 * Why is Trie Efficient?
 * <p>
 * - Fast lookup – Searching a word is O(n), where n is the word length.
 * <p>
 * - Memory-efficient – Only stores shared prefixes once.
 * <p>
 * - Useful for autocomplete & dictionary applications.
 */
class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            node = node.children.get(c);
        }
        node.isEndOfWord = true;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return null;
            }
            node = node.children.get(c);
        }
        return node;
    }

    private static class TrieNode {
        private boolean isEndOfWord;
        private Map<Character, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
            isEndOfWord = false;
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");

        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // false
        System.out.println(trie.startsWith("app")); // true

        trie.insert("app");
        System.out.println(trie.search("app"));     // true
    }
}
